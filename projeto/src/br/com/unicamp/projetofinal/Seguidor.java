package br.com.unicamp.projetofinal;

import java.util.*;

public abstract class Seguidor extends Carta{
	private int ataque;
	private int vida_atual;
	private int vida_original;
	private boolean vai_atacar;
	
	public Seguidor (String nome, int custo_mana, int ataque, int vida, Mesa mesa, Jogador jogador) {
		super(nome, custo_mana, mesa, jogador);
		this.ataque = ataque;
		this.vida_original = vida;
		this.vida_atual = vida;
	}
	// Getters e Setters
	public int getAtaque() {
		return this.ataque;
	}
	
	public int getVidaOriginal() { return this.vida_original; }

	public int getVidaAtual() { return this.vida_atual; }
	
	public abstract void verificarCondicao();

	public void aumentarVida(int quantidade){
		vida_atual += quantidade;
	}

	public void diminuirVida(int quantidade){
		vida_atual -= quantidade;
		if (vida_atual <= 0){
			matarSeguidor();
		}
	}

	public void matarSeguidor(){
		this.getMesa().getCartasMesa(this.getJogador()).remove(this);
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
	}

	public void atacar(){
		int endereco = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);

		Jogador adversario = this.getMesa().getAdversario(this.getJogador());

		ArrayList<Seguidor> cartas_adversario = this.getMesa().getCartasMesa(adversario);

		if (cartas_adversario.size() - 1 < endereco ){//se a ultima posicao das cartas do adversario for menor que o index da carta atacante, atacar o jogador
			adversario.diminuirVida(ataque);
		} else{//atacar a carta na posicao do adversario
			cartas_adversario.get(endereco).diminuirVida(ataque);
		}
	}
}
