package br.com.unicamp.projetofinal;

public class Thor extends Campeao{

	public Thor (String nome, int custo_mana, int ataque, int vida, Mesa mesa) {
		super(nome, custo_mana, ataque, vida, mesa);
	}

	@Override
	public void verificarCondicao() {
		feiticos_usados = this.getMesa.getFeiticos_usados();
		for (Feitico feitico : feiticos_usados)	{
			if (feitico.getTipo().equals("eletrico")) {
				this.aumentar_vida(2);
				this.aumentar_ataque(2);
			}
		}
	}
	
	
}
