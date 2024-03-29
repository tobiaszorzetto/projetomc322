package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;

/*
*
* Custo mana: 7
* Efeito: Escolhe uma carta inimiga para matar
*
* */


public class Vinganca extends Feitico {

    public Vinganca(Mesa mesa, Jogador jogador) {
        super("Vinganca", 7, mesa, jogador);
    }

    @Override
    protected void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.matarCartaInimiga(this);
    }
}
