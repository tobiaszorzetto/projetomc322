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
	
	public void adicionarCarta(Carta carta) {
		if(this.deck.size()<quant_max)
			this.deck.add(carta);
	}
	
	public void removerCarta(Carta carta) {
		this.deck.remove(carta);
	}

	public int getSize(){
		return deck.size();
	}

	public Carta getCarta(int indice_carta){
		return deck.get(indice_carta);
	}

	public ArrayList<Carta> getDeck(){
		return this.deck;
	}
	
	
	
}
