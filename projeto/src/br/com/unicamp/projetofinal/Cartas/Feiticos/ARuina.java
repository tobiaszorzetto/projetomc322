package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;

/*
*  9
*
*  Mata TODAS as cartas na mesa
*
* */

public class ARuina extends Feitico {
    public ARuina(Mesa mesa, Jogador jogador) {
        super("A Ruina", 9, mesa, jogador);
    }

    @Override
    protected void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.matarTodasAsCartasNaMesa(this);
    }
}
