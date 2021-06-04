package br.com.unicamp.projetofinal;

public abstract class Campeao extends Seguidor{
	private int ataque;
	private int vida;
	
	public Campeao (String nome, int custo_mana, int ataque, int vida, Mesa mesa) {
		super(nome, custo_mana, ataque,vida, mesa);
	}
	
	

}
