package br.com.unicamp.projetofinal;

public abstract class Campeao extends Seguidor{

	private boolean nao_upou = true;

	public Campeao (String nome, int custo_mana, int ataque, int vida, Mesa mesa, Jogador jogador, GerenciadorEfeitos ge) {
		super(nome, custo_mana, ataque, vida, mesa, jogador, ge);
	}
	
	abstract public void checarLevelUp();

	protected boolean getNaoUpou(){
		return nao_upou;
	}

	protected  void upou(){
		this.nao_upou = false;
	}

}
