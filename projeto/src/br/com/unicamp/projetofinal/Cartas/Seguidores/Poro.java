package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

/*
*
*  1 | 2 | 1
*
* */

public class Poro extends Seguidor {
    public Poro(Mesa mesa, Jogador jogador) {
        super("Poro", 1, 2, 1, mesa, jogador);
    }

}
