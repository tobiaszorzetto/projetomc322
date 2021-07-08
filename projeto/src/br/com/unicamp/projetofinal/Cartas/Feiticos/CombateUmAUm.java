package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;

/*
*  5
*
*  Ao usar escolhe um aliado e um inimigo para combaterem diretamente
*
* */

public class CombateUmAUm extends Feitico {
    public CombateUmAUm(Mesa mesa,Jogador jogador) {
        super("Combate Um A Um", 5, mesa, jogador);
    }

    public void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.escolherCartasCombate(this);
    }
}
