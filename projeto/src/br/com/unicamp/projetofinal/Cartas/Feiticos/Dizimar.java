package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;

/*
*  5
*
*  4 de dano ao nexus adversario
*
*
* */

public class Dizimar extends Feitico {
    public Dizimar(Mesa mesa, Jogador jogador) {
        super("Dizimar", 5, mesa, jogador);
    }

    @Override
    protected void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.atacarNexus(this.getAdversario(), 4);
    }
}
