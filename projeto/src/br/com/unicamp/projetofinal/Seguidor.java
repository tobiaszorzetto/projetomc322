package br.com.unicamp.projetofinal;

public abstract class Seguidor extends Carta{
	private int ataque;
	private int vida;
	
	public Seguidor (String nome, int custo_mana, int ataque, int vida, Mesa mesa, int jogador) {
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
	}

	public void aumentarAtaque(int quantidade){
		ataque += quantidade;
	}
	//

	public void diminuirAtaque(int quantidade){
		ataque -= quantidade;
	}
}
