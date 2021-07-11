package br.com.unicamp.projetofinal.Cartas;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Enums.Traco;

import java.util.*;

/*
*  custo de mana | ataque | vida
*
*  Pode ter efeitos
*
*  Pode ter tracos
*
*  Cada classe que extende seguidor tem suas proprias caracteristicas
* */

public abstract class Seguidor extends Carta {
	private final int ataque_original;
	private int vezes_que_atacou = 0;
	private int get_vezes_que_vai_atacar;
	private int ataque;
	protected int vida_atual;
	private final int vida_original;
	private boolean vai_atacar = false;
	private boolean vai_defender = false;
	private boolean matou_alguem = false;
	private boolean morreu = false;
	
	public Seguidor (String nome, int custo_mana, int ataque, int vida, Mesa mesa, Jogador jogador) {
		super(nome, custo_mana, mesa, jogador);
		this.ataque_original = ataque;
		this.ataque = ataque;
		this.vida_original = vida;
		this.vida_atual = vida;
	}
	// Getters e Setters

	public boolean naoMorreu() {
		return !morreu;
	}

	public void setVaiDefender(boolean vai_defender) {
		this.vai_defender = vai_defender;
	}

	public boolean naoVaiDefender() {
		return !vai_defender;
	}


	// GETTERS SETTERS E BOOLS -----------------------------------------------------------------------------------------
		//Getters.......................................................................................................
	public boolean getVaiAtacar(){
		return this.vai_atacar;
	}

	public int getAtaque() {
		return this.ataque;
	}

	public int getVezesQueAtacou(){
		return vezes_que_atacou;
	}

	public int getGetVezesQueVaiAtacar() {
		return get_vezes_que_vai_atacar;
	}

	public int getVidaOriginal() { return this.vida_original; }

	public int getVidaAtual() { return this.vida_atual; }

	public boolean getMatouAlguem(){
		return matou_alguem;
	}

		//Setter .......................................................................................................

	public void setAtaque(int valor){
		this.ataque = valor;
	}
	
	public void setAtaqueOriginal(){
		this.ataque = this.ataque_original;
	}
	
	public void setVidaOriginal(){
		this.vida_atual = this.vida_original;
	}

	public void setMatouAlguem(boolean a){
		this.matou_alguem = a;
	}
	
	public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException{};
	public void setVaiAtacar(boolean variavel){
		this.vai_atacar = variavel;
		if (variavel && this.getTraco() == Traco.ATAQUEDUPLO)
			this.get_vezes_que_vai_atacar = PrintFactory.pedirInputInt("vai atacar 1 ou duas vezes?");
	}

	public boolean isElusivo() {
		return this.getTraco() == Traco.ELUSIVO;
	}

	public void aumentarVezesAtacou(){
		this.vezes_que_atacou++;
	}

	// VIDA ------------------------------------------------------------------------------------------------------------
	public void aumentarVida(int quantidade){
		vida_atual += quantidade;
	}

	public boolean diminuirVida(int quantidade) throws PosicaoMesaOcupadaException, ManaInsuficienteException{
		if(this.getTraco()== Traco.BARREIRA){
			//se tiver traco de barreira nao diminui vida
			this.setTraco(Traco.NENHUM);//perde o traco de barreira
			return false;//retorna que nao morreu ja direto
		}
		vida_atual -= quantidade;

		if (vida_atual <= 0){
			matarSeguidor();
			return true;//retorna que morreu
		}

		return false;//retorna que nao morreu
	}

	public void matarSeguidor() throws ManaInsuficienteException, PosicaoMesaOcupadaException{
		this.setMorreu();//coloca o seguidor como morto
		int posicao = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);
		this.getMesa().getCartasMesa(this.getJogador()).set(posicao, null);//troca ele por null na mesa
	}

	protected void setMorreu(){
		this.morreu = true;
	};

	// Verificacao de Tracos -------------------------------------------------------------------------------------------

	public void verificarFuria(){
		if (this.getTraco() == Traco.FURIA){
			GerenciadorEfeitos.aumentarAtaqueVida(this,1,1);
		}
	}

	public boolean verificarElusivo( Seguidor carta_adversario) {
		return this.isElusivo() && !carta_adversario.isElusivo();
	}

	// ATAQUE ----------------------------------------------------------------------------------------------------------

	public void atacar() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		this.vezes_que_atacou++;

		int endereco = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);
		Jogador adversario = this.getAdversario();
		ArrayList<Seguidor> cartas_adversario = this.getMesa().getCartasMesa(adversario);
		Seguidor carta_adversario = cartas_adversario.get(endereco); // eh carta que esta na frente desse seguidor (pode ser nenhuma)

		if(deveAtacarNexus( carta_adversario )) {//se deve atacar o nexus
			this.atacarNexus(adversario, this.ataque);
		}
		else{ // se tem condicoes de combate
			this.realizarCombate(carta_adversario);
		}
	}

	public void aumentarAtaque(int quantidade){
		ataque += quantidade;
	}

	public void atacarNexus(Jogador adversario, int quant){
		adversario.diminuirVida(quant);
	}

	public boolean deveAtacarNexus(Seguidor carta_adversario) {
		if (carta_adversario == null || carta_adversario.naoVaiDefender()) {
			return true;// se nao tiver carta bloquando na frente retorna que deve atacar o nexus
		}
		return verificarElusivo(carta_adversario); // se tem carta bloqueando, so retorna true se essa carta for elusiva e a outra n for
	}

	// AO SER COLOCADO NA MESA -----------------------------------------------------------------------------------------

	public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
		//define o que vai acontecer com o seguidor quando colocado na mesa
		if (posicao_alocacao<0){//verifica a posicao de alocacao recebida
			throw new ArrayIndexOutOfBoundsException();
		}
		if(this.getMesa().getCartasMesa(jogador).get(posicao_alocacao) != null){//verifica se ja tem carta na posicao recebida
			throw new PosicaoMesaOcupadaException();
		}
		this.realizarEfeitoAntesDeColocado();
		//com todas as restricoes verificadas, coloca a carta na mesa
		this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
	}

	protected void realizarEfeitoAntesDeColocado() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		//implementada nos seguidores que necessitarem
	}

	// GERAIS ----------------------------------------------------------------------------------------------------------

	public void realizarCombate(Seguidor carta_adversario) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		// essa carta e a adversaria diminuem vida
		this.diminuirVida(carta_adversario.getAtaque());
		boolean adversario_morreu = carta_adversario.diminuirVida(ataque);//verifica se ele morreu
		if (adversario_morreu){
			verificarFuria();
			verificarSobrepujar(carta_adversario);
		}
	}

	public void jogarCarta() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		if (this.getMesa().temEspacoMesa(this.getJogador())){// so acontece se tem espaco
			Jogador jogador = this.getJogador();
			Deck mao = jogador.getMao();
			int posicao_alocacao = jogador.escolherPosicao();
			try{
				jogador.setMana(jogador.getMana() - this.getMana());//diminui a mana do jogador de acordo com o custo da carta
				this.atuarNaMesa(jogador, posicao_alocacao);
				mao.removerCarta(this);
			}
			catch (PosicaoMesaOcupadaException | ArrayIndexOutOfBoundsException e){//caso posicao invalida
				jogador.setMana(jogador.getMana() + this.getMana());//retorna a mana ao jogador
				System.out.println("Posicao invalida");
				jogarCarta();
			}
		} else{
			System.out.println("Não há espaco na mesa para a carta ser jogada");
		}

	}

    public void resetar(){
		//volta tudo do seguidor para o original
		this.setAtaqueOriginal();
		this.setVidaOriginal();
		this.setVaiAtacar(false);
		this.setVaiDefender(false);
	}
}
