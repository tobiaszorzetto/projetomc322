package br.com.unicamp.projetofinal;

import java.util.*;

public abstract class Seguidor extends Carta{
	private int vezes_que_atacou = 0;
	private int ataque;
	private int vida_atual;
	private int vida_original;
	private boolean vai_atacar;
	private boolean matou_alguem = false;
	private boolean pode_atacar;
	private Traco traco = Traco.NENHUM;
	
	public Seguidor (String nome, int custo_mana, int ataque, int vida, Mesa mesa, Jogador jogador, GerenciadorEfeitos ge) {
		super(nome, custo_mana, mesa, jogador, ge);
		this.ataque = ataque;
		this.vida_original = vida;
		this.vida_atual = vida;
	}
	// Getters e Setters
	public boolean getPodeAtacar(){
		return pode_atacar;
	}

	public void setPodeAtacar(boolean a){
		this.pode_atacar = a;
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
		this.getMesa().getCartasMesa(this.getJogador()).remove(this);
		//pensar num jeito aqui
		this.getMesa().verificarCondicoes();
	}

	public void aumentarAtaque(int quantidade){
		ataque += quantidade;
	}
	//

	public void diminuirAtaque(int quantidade){
		ataque -= quantidade;
	}

	public void atuarNaMesa(Jogador jogador){
		this.getMesa().colocarCartaMesa(jogador, this);
		this.getMesa().verificarCondicoes();
	}

	public void atacarNexus(Jogador adversario){
		adversario.diminuirVida(ataque);
	}

	public Jogador getAdversario(){
		return this.getMesa().getAdversario(this.getJogador());
	}

	public void atacar(){
		this.vezes_que_atacou++;
		int endereco = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);

		Jogador adversario = this.getAdversario();

		ArrayList<Seguidor> cartas_adversario = this.getMesa().getCartasMesa(adversario);

		if (cartas_adversario.size() - 1 < endereco ){//se a ultima posicao das cartas do adversario for menor que o index da carta atacante, atacar o jogador
			this.atacarNexus(adversario);
		} else{//atacar a carta na posicao do adversario
			matou_alguem = cartas_adversario.get(endereco).diminuirVida(ataque);

		}
	}
}
