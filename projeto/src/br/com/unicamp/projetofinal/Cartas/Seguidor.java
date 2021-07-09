package br.com.unicamp.projetofinal.Cartas;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Enums.Traco;

import java.util.*;

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

	public void setVaiAtacar(boolean variavel){
		this.vai_atacar = variavel;
		if (variavel && this.getTraco() == Traco.ATAQUEDUPLO)
			this.get_vezes_que_vai_atacar = PrintFactory.pedirInput("vai atacar 1 ou duas vezes?");
	}

	// GETTERS SETTERS E BOOLS
		//Getters
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

		//Setter

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

	public boolean isElusivo() {
		return this.getTraco() == Traco.ELUSIVO;
	}

	public void aumentarVezesAtacou(){
		this.vezes_que_atacou++;
	}

	// VIDA
	public void aumentarVida(int quantidade){
		vida_atual += quantidade;
	}

	public boolean diminuirVida(int quantidade) throws PosicaoMesaOcupadaException, ManaInsuficienteException{
		if(this.getTraco()== Traco.BARREIRA){
			this.setTraco(Traco.BARREIRA);
			return false;
		}
		vida_atual -= quantidade;
		if (vida_atual <= 0){
			matarSeguidor();
			return true;
		}
		return false;
	}

	public void matarSeguidor() throws ManaInsuficienteException, PosicaoMesaOcupadaException{
		this.setMorreu();
		int posicao = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);
		this.getMesa().getCartasMesa(this.getJogador()).set(posicao, null);//remove da lista e coloca null no lugar
	}

	protected void setMorreu(){
		this.morreu = true;
	};

	// Verificacao de Tracos

	public void verificarFuria(){
		if (this.getTraco() == Traco.FURIA){ //se o seguidor tiver traco de furia
			GerenciadorEfeitos.aumentarAtaqueVida(this,1,1);
		}
	}

	public boolean verificarElusivo( Seguidor carta_adversario) {
		return this.isElusivo() && !carta_adversario.isElusivo();
	}


	// FUNCOES GERAIS

	public void aumentarAtaque(int quantidade){
		ataque += quantidade;
	}

	public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
		if (posicao_alocacao<0){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(this.getMesa().getCartasMesa(jogador).get(posicao_alocacao) != null){
			throw new PosicaoMesaOcupadaException();
		}
		this.realizarEfeitoAntesDeColocado();
		this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
	}

	protected void realizarEfeitoAntesDeColocado() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

	}

	public void atacarNexus(Jogador adversario, int quant){
		adversario.diminuirVida(quant);
	}


	public boolean deveAtacarNexus(Seguidor carta_adversario) {
		if (carta_adversario == null || carta_adversario.naoVaiDefender()) {
			return true;
		}
		return verificarElusivo(carta_adversario);
	}

	public void realizarCombate(Seguidor carta_adversario) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		this.diminuirVida(carta_adversario.getAtaque());//diminui a vida desse seguidor
		boolean adversario_morreu = carta_adversario.diminuirVida(ataque);//diminui a vida do adversario e verifica se ele morreu
		if (adversario_morreu){
			verificarFuria(); //chama-se apenas quando sabemos que matou o inimigo
			verificarSobrepujar(carta_adversario);
		}
	}


	public void atacar() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		this.vezes_que_atacou++;
		int endereco = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);

		Jogador adversario = this.getAdversario();
		ArrayList<Seguidor> cartas_adversario = this.getMesa().getCartasMesa(adversario);
		Seguidor carta_adversario = cartas_adversario.get(endereco);

		if(deveAtacarNexus( carta_adversario )) {
			this.atacarNexus(adversario, this.ataque);
		}
		else{//atacar a carta na posicao do adversario
			this.realizarCombate(carta_adversario);
		}
	}

	public void jogarCarta() throws ManaInsuficienteException{
		Jogador jogador = this.getJogador();
		Deck mao = jogador.getMao();
		Mesa mesa = this.getMesa();
		int posicao_alocacao = jogador.escolherPosicao();
		try{
			jogador.setMana(jogador.getMana() - this.getMana());
			this.atuarNaMesa(jogador, posicao_alocacao);
			mao.removerCarta(this);
		}
		catch (PosicaoMesaOcupadaException | ArrayIndexOutOfBoundsException e){
			jogador.setMana(jogador.getMana() + this.getMana());
			System.out.println("Posicao invalida");
			jogarCarta();
		}
	}

}
