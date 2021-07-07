package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;

public class LamentoFulminante extends Feitico {

    public LamentoFulminante(Mesa mesa, Jogador jogador){
        super("Lamento Fulminante", 5, mesa, jogador);
    }

    @Override
    protected void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.AtacarTodasAsCartasInimigo(this);
        GerenciadorEfeitos.curarNexus(this.getJogador(), 3);
    }
}
