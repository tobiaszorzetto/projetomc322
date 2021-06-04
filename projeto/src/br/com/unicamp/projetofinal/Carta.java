package br.com.unicamp.projetofinal;

public abstract class Carta {
	private String nome;
	private int custo_mana;
	private Mesa mesa;
	private int jogador;
	
	public Carta (String nome, int custo_mana, Mesa mesa, int numero_jogador) {
		this.nome = nome;
		this.custo_mana = custo_mana;
		this.mesa = mesa;
		this.jogador = numero_jogador;
	}
}
