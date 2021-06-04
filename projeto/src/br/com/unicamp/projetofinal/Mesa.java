package br.com.unicamp.projetofinal;

import java.util.ArrayList;

public class Mesa {
	//vamos ter duas listas uma pra cada grupo de cartas na mesa de cada jogador
	
	// vamos ter uma lista para cada tipo de carta jogado no round
	
	private ArrayList<Seguidor> cartas_jogo_0 = new ArrayList<Seguidor>();
	private ArrayList<Seguidor> cartas_jogo_1 = new ArrayList<Seguidor>();
	
	public Mesa() {
		
	}
	
	// Getters e Setters 
	
	public ArrayList<Seguidor> getCartasJogo0(){
		return this.cartas_jogo_0;
	}
	
	public ArrayList<Seguidor> getCartasJogo1(){
		return this.cartas_jogo_1;
	}
}
