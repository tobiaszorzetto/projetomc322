package br.com.unicamp.projetofinal;

public abstract class Carta {
	private String nome;
	private int custo_mana;
	private Mesa mesa;
	
	public Carta (String nome, int custo_mana, Mesa mesa) {
		this.nome = nome;
		this.custo_mana = custo_mana;
		this.mesa = mesa;
	}
}
