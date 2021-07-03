package br.com.unicamp.projetofinal;

import br.com.unicamp.projetofinal.Cartas.Seguidor;

public abstract class Carta {
	private String nome;
	private int custo_mana;
	private Mesa mesa;
	private Jogador jogador;
	private Traco traco= Traco.NENHUM;
	
	public Carta (String nome, int custo_mana, Mesa mesa, Jogador jogador) {
		this.nome = nome;
		this.custo_mana = custo_mana;
		this.mesa = mesa;
		this.jogador = jogador;
	}
	
	//Getters e Setters

	public boolean isDepujante() {
		return this.traco == Traco.SOBREPUJAR;
	}

	public Traco getTraco() {
		return traco;
	}

	public void setTraco(Traco traco) {
		this.traco = traco;
	}

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

	public Jogador getAdversario(){
		return this.getMesa().getAdversario(this.getJogador());
	}
	public void verificarDepujante(Seguidor carta_adversario){
		if(this.isDepujante()){
			GerenciadorEfeitos.atacarNexus(carta_adversario.getJogador(), - carta_adversario.getVidaAtual());
		}
	};

	public abstract void jogarCarta();

    public enum Marcador {
        ATACANTE,DEFENSOR;
    }

	public enum TipoDeck {
		PERSONALIZADO, PADRAO;
	}

	public enum Traco {
		NENHUM, ELUSIVO, ATAQUEDUPLO, FURIA, SOBREPUJAR, BARREIRA;
	}
}
