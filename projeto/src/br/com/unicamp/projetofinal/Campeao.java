package br.com.unicamp.projetofinal;

public abstract class Campeao extends Carta{
	private int ataque;
	private int vida;
	
	public Campeao (String nome, int custo_mana, int ataque, int vida, Mesa mesa) {
		super(nome, custo_mana, mesa);
		this.ataque = ataque;
		this.vida = vida;
	}
	
	public abstract void verificarCondicao();

	public void aumentarVida(int quantidade){
		vida += quantidade;
	}

	public void diminuirVida(int quantidade){
		vida -= quantidade;
	}

	public void aumentarAtaque(int quantidade){
		ataque += quantidade;
	}

	public void diminuirAtaque(int quantidade){
		ataque -= quantidade;
	}

}
