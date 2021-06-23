package br.com.unicamp.projetofinal;

import java.util.Scanner;

public class Curandeira extends Seguidor{
	//cura aumenta a vida de uma carta aliada quando entra em jogo
	
	private boolean primeira_rodada;
	
	public Curandeira(Mesa mesa, Jogador jogador) {
		super("curandeira", 4,2,4, mesa, jogador);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void verificarCondicao() {
		if (this.primeira_rodada) {
			this.primeira_rodada = false;
			Scanner scan = new Scanner(System.in);
	        System.out.println("Passe o index do jogador que vc quer curar");
	        int index = scan.nextInt();
	        scan.close();
	        if(this.getMesa().getCartasMesa(this.getJogador()).size()>index)//p checar se n ta fora do index
	        	this.getMesa().getCartasMesa(this.getJogador()).get(index).aumentarVida(3);


					
		}
	}
		
}

