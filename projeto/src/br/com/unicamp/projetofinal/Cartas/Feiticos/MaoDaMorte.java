package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;


/*
*
* Custo mana: 3
* Efeito: Causa 2 de dano a uma carta a escolha do jogador e da dano de 1 no nexus adversario
*
*
* */

public class MaoDaMorte extends Feitico {
    public MaoDaMorte(Mesa mesa, Jogador jogador) {
        super("Mao Da Morte", 3, mesa, jogador);
    }

    @Override
    protected void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.escolherCartaParaDarDano(this, this.getAdversario(), 2);
        GerenciadorEfeitos.atacarNexus(this.getAdversario(), 1);
    }

}
