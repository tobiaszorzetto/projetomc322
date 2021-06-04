package br.com.unicamp.projetofinal;

public abstract class Campeao extends Seguidor{

	public Campeao (String nome, int custo_mana, int ataque, int vida, Mesa mesa, int jogador) {
		super(nome, custo_mana, ataque, vida, mesa, jogador);
	}
}
