package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.Enums.Traco;

/*
*
* Custo de mana: 2
* Efeito: Causa 2 de dano a um alvo escolhido
*
* */


public class DisparoMistico extends Feitico{

    public DisparoMistico(Mesa mesa, Jogador jogador) {
        super("Disparo Mistico", 2, mesa, jogador);

    }

    public void realizarEfeito() throws PosicaoMesaOcupadaException, ManaInsuficienteException {
        GerenciadorEfeitos.escolherCoisaParaDarDano(this, 2);
    }


}
