package br.com.unicamp.projetofinal;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Carta> deck;
	private int quant_max;
	
	public Deck() {
		this.deck = new ArrayList<Carta>();
		this.quant_max = 40;
	}
	
	//getters e setters
	
	
	//
	
	public void adicionarCarta(Carta carta) {
		this.deck.add(carta);
	}
	
	public void removerCarta(Carta carta) {
		this.deck.remove(carta);
	}
	
	
	
	
}
