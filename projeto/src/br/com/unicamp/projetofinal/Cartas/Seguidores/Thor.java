package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

/*
*
*  4 | 7 | 10
*
* */

public class Thor extends Seguidor{

	public Thor (Mesa mesa, Jogador jogador) {
		super("Thor", 4, 7, 10, mesa, jogador);
	}
}
