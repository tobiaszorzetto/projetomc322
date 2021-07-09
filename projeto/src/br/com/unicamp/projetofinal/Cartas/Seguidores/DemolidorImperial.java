package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

/*
*  2 | 2 | 2
*
*  Ao colocar na mesa -> da 1 de dano em um aliado e 2 de danos ao nexus inimigo
*
* */

public class DemolidorImperial extends Seguidor {
    public DemolidorImperial(Mesa mesa, Jogador jogador) {
        super("Demolidor Imperial", 2, 2, 3, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

    }
    @Override
    public void realizarEfeitoAntesDeColocado() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.darDanoEmAliadoParaAtacarNexus(this, 1, 2);
    }

}
