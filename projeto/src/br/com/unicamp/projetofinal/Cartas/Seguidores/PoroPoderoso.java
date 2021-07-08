package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Traco;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

/*
*  3 | 3 | 3
*
*  Traco -> Sobrepujar
* */

public class PoroPoderoso extends Seguidor {
    public PoroPoderoso( Mesa mesa, Jogador jogador) {
        super("Poro Poderoso", 3, 3, 3, mesa, jogador);
        this.setTraco(Traco.SOBREPUJAR);
    }

    @Override
    public void verificarCondicao() {

    }
}
