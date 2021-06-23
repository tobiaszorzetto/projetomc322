package br.com.unicamp.projetofinal;

public abstract class Campeao extends Seguidor{

	public Campeao (String nome, int custo_mana, int ataque, int vida, Mesa mesa, Jogador jogador) {
		super(nome, custo_mana, ataque, vida, mesa, jogador);
	}
	
	abstract public void checarLevelUp();
}
