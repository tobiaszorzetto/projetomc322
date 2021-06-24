package br.com.unicamp.projetofinal;

import java.util.ArrayList;
import java.util.Scanner;

public class Curandeira extends Seguidor{
	//cura aumenta a vida de uma carta aliada quando entra em jogo
	
	private boolean primeira_rodada;
	
	public Curandeira(Mesa mesa, Jogador jogador, GerenciadorEfeitos ge) {
		super("curandeira", 4,2,4, mesa, jogador, ge);
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

			ArrayList<Seguidor> cartas_na_mesa = this.getMesa().getCartasMesa(this.getJogador());
	        if(cartas_na_mesa.size()>index)//p checar se n ta fora do index
	        	this.ge.curar(cartas_na_mesa.get(index));

		}
	}
		
}

