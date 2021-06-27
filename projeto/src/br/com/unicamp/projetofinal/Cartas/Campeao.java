package br.com.unicamp.projetofinal.Cartas;

import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

public abstract class Campeao extends Seguidor {

	private boolean nao_upou = true;

	public Campeao (String nome, int custo_mana, int ataque, int vida, Mesa mesa, Jogador jogador) {
		super(nome, custo_mana, ataque, vida, mesa, jogador);
	}
	
	abstract public void checarLevelUp();

	protected boolean getNaoUpou(){
		return nao_upou;
	}

	protected  void upou(){
		this.nao_upou = false;
	}

}
