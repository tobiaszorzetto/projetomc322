package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;

/*
*  4
*
*  Da 2 de dano em TODAS as cartas da mesa
*
* */

public class Avalanche extends Feitico {

    public Avalanche(Mesa mesa, Jogador jogador){
        super("Avalanche", 4, mesa, jogador);
    }

    @Override
    public void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.AtacarTodasAsCartas(this.getMesa(), 2);
    }
}
