package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

/*
*  3 | 3 | 3
*
*  Ao ser colocado na mesa -> cura um aliado ou o nexus em 3
*
* */

public class TaverneiroGentil extends Seguidor {
    public TaverneiroGentil(Mesa mesa, Jogador jogador) {
        super("Taverneiro Gentil", 3, 3, 3, mesa, jogador);
    }


    @Override
    public void realizarEfeitoAntesDeColocado(){
        GerenciadorEfeitos.curarAliadoOuNexus(this, 3);
    }

}
