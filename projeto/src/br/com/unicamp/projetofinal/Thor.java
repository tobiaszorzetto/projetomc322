package br.com.unicamp.projetofinal;

public class Thor extends Campeao{

	public Thor (Mesa mesa, int jogador) {
		super("Thor", 4, 7, 10, mesa, jogador);
	}

	@Override
	public void verificarCondicao() {
		feiticos_usados = this.getMesa.getFeiticos_usados();
		for (Feitico feitico : feiticos_usados)	{
			if (feitico.getTipo().equals("eletrico")) {
				this.aumentarVida(2);
				this.aumentarAtaque(2);
			}
		}
	}
	
	
}
