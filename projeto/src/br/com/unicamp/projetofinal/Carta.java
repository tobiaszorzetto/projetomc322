package br.com.unicamp.projetofinal;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Traco;

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

	public boolean isSobrepujar() {
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

	public abstract void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException;

	public String getNome(){
		return this.nome;
	}

	public Jogador getAdversario(){
		return this.getMesa().getAdversario(this.getJogador());
	}

	public void diminuirCustoMana(int quant){
		for(int i = 0; i< quant; i++)
			this.custo_mana--;
	}

	public void verificarSobrepujar(Seguidor carta_adversario){
		if(this.isSobrepujar()){
			GerenciadorEfeitos.atacarNexus(carta_adversario.getJogador(), - carta_adversario.getVidaAtual());
		}
	};

	public abstract void jogarCarta() throws ManaInsuficienteException, PosicaoMesaOcupadaException;

}
