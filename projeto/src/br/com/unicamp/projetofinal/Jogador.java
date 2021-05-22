package br.com.unicamp.projetofinal;

public class Jogador {
	private String nome;
	private int vida;
	private Marcador marcador;
	private int mana;
	
	public Jogador(String nome) {
		this.nome = nome;
		this.vida = 20;
		this.mana = 1; // ainda n sabemos quanto
	}
	
	//getters e setters
	
	public void set_marcador(Marcador marcador) {
		this.marcador = marcador;
	}
	
	// muda vida
	
	private void aumentar_vida(int pontos) {
		this.vida +=  pontos;
	}
	
	private void diminuir_vida(int pontos) {
		this.vida -=  pontos;
	}

	//
}

