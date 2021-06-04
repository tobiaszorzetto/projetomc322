package br.com.unicamp.projetofinal;

public class Curandeira extends Seguidor{
	//cura aumenta a vida de uma carta aliada quando entra em jogo
	
	private boolean primeira_rodada;
	
	public Curandeira(String nome, int custo_mana, int ataque, int vida, Mesa mesa) {
		super(nome, custo_mana, ataque, vida, mesa);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void verificarCondicao() {
		if (this.primeira_rodada) {
			this.primeira_rodada = false;
				if (this.getJogador() == 0){
					
			}
		}
		
	}

}
