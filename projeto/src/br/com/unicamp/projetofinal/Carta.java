package br.com.unicamp.projetofinal;

public abstract class Carta {
	private String nome;
	private int custo_mana;
	private Mesa mesa;
	private Jogador jogador;
	
	public Carta (String nome, int custo_mana, Mesa mesa, Jogador jogador) {
		this.nome = nome;
		this.custo_mana = custo_mana;
		this.mesa = mesa;
		this.jogador = jogador;
	}
	
	//Getters e Setters
	
	protected Mesa getMesa() {
		return this.mesa;
	}
	
	public Jogador getJogador() {
		return this.jogador;
	}
	
	public int getMana() {
		return this.custo_mana;
	}

	public abstract void atuarNaMesa(Jogador jogador, int posicao_alocacao);

	public String getNome(){
		return this.nome;
	}
}
