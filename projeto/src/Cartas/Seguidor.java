package Cartas;

import br.com.unicamp.projetofinal.Carta;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;
import Enums.Traco;

import java.util.*;

public abstract class Seguidor extends Carta {
	private int vezes_que_atacou = 0;
	private int getVezes_que_vai_atacar;
	private int ataque;
	private int vida_atual;
	private int vida_original;
	private boolean vai_atacar = false;
	private boolean vai_defender = false;
	private boolean matou_alguem = false;
	private int inimigor_mortos = 0;
	private boolean pode_atacar;
	private Traco traco = Traco.NENHUM;
	private boolean morreu = false;
	
	public Seguidor (String nome, int custo_mana, int ataque, int vida, Mesa mesa, Jogador jogador) {
		super(nome, custo_mana, mesa, jogador);
		this.ataque = ataque;
		this.vida_original = vida;
		this.vida_atual = vida;
	}
	// Getters e Setters
	public boolean getPodeAtacar(){
		return pode_atacar;
	}

	public boolean isMorreu() {
		return morreu;
	}

	public void setPodeAtacar(boolean a){
		this.pode_atacar = a;
	}

	public void setVaiDefender(boolean vai_defender) {
		this.vai_defender = vai_defender;
	}

	public boolean isVaiDefender() {
		return vai_defender;
	}

	public void setVaiAtacar(boolean variavel){
		this.vai_atacar = variavel;
		if (variavel && this.traco == Traco.ATAQUEDUPLO)
			this.getVezes_que_vai_atacar = GerenciadorEfeitos.pedirInput("vai atacar 1 ou duas vezes?");
	}

	public int getGetVezesQueVaiAtacar() {
		return getVezes_que_vai_atacar;
	}

	public boolean isElusivo() {
		return this.traco == Traco.ELUSIVO;
	}

	public boolean isDepujante() {
		return this.traco == Traco.DEPUJANTE;
	}

	public boolean getVaiAtacar(){
		return this.vai_atacar;
	}

	public int getAtaque() {
		return this.ataque;
	}

	public int getVezesQueAtacou(){
		return vezes_que_atacou;
	}

	public void setVidaOriginal(){
		this.vida_atual = this.vida_original;
	}

	public void setTraco(Traco traco){
		this.traco = traco;
	}

	public int getVidaOriginal() { return this.vida_original; }

	public int getVidaAtual() { return this.vida_atual; }

	public boolean getMatouAlguem(){
		return matou_alguem;
	}

	public void setMatouAlguem(boolean a){
		this.matou_alguem = a;
	}
	
	public abstract void verificarCondicao();

	public void aumentarVida(int quantidade){
		vida_atual += quantidade;
	}



	public boolean diminuirVida(int quantidade){
		vida_atual -= quantidade;
		if (vida_atual <= 0){
			matarSeguidor();
			return true;
		}
		return false;
	}

	public void matarSeguidor(){
		morreu = true;
		int posicao = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);
		this.getMesa().getCartasMesa(this.getJogador()).remove(this);//remove da lista
		this.getMesa().getCartasMesa(this.getJogador()).add(posicao, null);//adiciona null no lugar

		//pensar num jeito aqui
	}

	public void aumentarAtaque(int quantidade){
		ataque += quantidade;
	}
	//

	public void diminuirAtaque(int quantidade){
		ataque -= quantidade;
	}

	public void atuarNaMesa(Jogador jogador, int posicao_alocacao){
		this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
	}

	public void atacarNexus(Jogador adversario){
		adversario.diminuirVida(ataque);
	}
	public void atacarNexus(Jogador adversario, int quant){
		adversario.diminuirVida(quant);
	}

	public Jogador getAdversario(){
		return this.getMesa().getAdversario(this.getJogador());
	}

	public Traco getTraco(){
		return this.traco;
	}

	public void verificarFuria(){
		if (this.traco == Traco.FURIA){ //se o seguidor tiver traco de furia
			ataque += ataque;
			vida_atual += vida_atual;
		}
	}

	public boolean verificarElusivo( Seguidor carta_adversario) {
		return this.isElusivo() && !this.isElusivo();
	}

	public void verificarDepuhante(Seguidor carta_adversario){
		if(this.isDepujante()){
			this.atacarNexus(carta_adversario.getJogador(), - carta_adversario.getVidaAtual());
		}
	}


	public boolean deveAtacarNexus(Seguidor carta_adversario) {
		if (carta_adversario == null || !carta_adversario.isVaiDefender()) {
			return true;
		}
		return verificarElusivo(carta_adversario);
	}

	public void realizarCombate(Seguidor carta_adversario){
		this.diminuirVida(carta_adversario.getAtaque());//diminui a vida desse seguidor
		boolean adversario_morreu = carta_adversario.diminuirVida(ataque);//diminui a vida do adversario e verifica se ele morreu
		if (adversario_morreu){
			inimigor_mortos += 1;
			verificarFuria(); //chama-se apenas quando sabemos que matou o inimigo
			verificarDepuhante(carta_adversario);
		}
	}


	public void atacar(){
		this.vezes_que_atacou++;
		int endereco = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);

		Jogador adversario = this.getAdversario();
		ArrayList<Seguidor> cartas_adversario = this.getMesa().getCartasMesa(adversario);
		Seguidor carta_adversario = cartas_adversario.get(endereco);

		if(deveAtacarNexus( carta_adversario )) {
			this.atacarNexus(adversario);
		}
		else{//atacar a carta na posicao do adversario
			this.realizarCombate(carta_adversario);
		}
	}
}
