package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

/*
*  4 | 2 |4
*
* melhor carta do jogo!
* */

public class Gnomo extends Seguidor {

	public Gnomo(Mesa mesa, Jogador jogador) {
		super("gnomo", 4, 2, 4, mesa, jogador);
	}

}
