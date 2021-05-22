package br.com.unicamp.projetofinal;

public class Jogador {
	private String nome;
	private int vida;
	private Marcador marcador;
	
	public Jogador(String nome) {
		this.nome = nome;
		this.vida = 20;
	}
	
	//getters e setters
	
	public void set_marcador(Marcador marcador) {
		this.marcador = marcador;
	}
	
}
