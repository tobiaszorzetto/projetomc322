package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;

/*
* Custo de mana: 5
* Da um de dano a todas as cartas na mesa do inimigo e cura o nexus aliado em 3
*
*
* */

public class LamentoFulminante extends Feitico {

    public LamentoFulminante(Mesa mesa, Jogador jogador){
        super("Lamento Fulminante", 5, mesa, jogador);
    }

    @Override
    protected void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.AtacarTodasAsCartasInimigo(this, 1);
        GerenciadorEfeitos.curarNexus(this.getJogador(), 3);
    }
}
