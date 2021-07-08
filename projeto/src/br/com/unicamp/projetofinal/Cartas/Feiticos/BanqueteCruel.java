package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.Cartas.Seguidores.CriaAracnidea;

/*
*  2
*
*  Drena 1 de vida de um aliado da sua escolha
*
*  Evoca uma Cria Aracnidea na primeira posicao possivel
*
* */


public class BanqueteCruel extends Feitico {
    public BanqueteCruel(Mesa mesa, Jogador jogador) {
        super("Banquete Cruel", 2, mesa, jogador);
    }

    @Override
    protected void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.escolherCartaParaDarDano(this, this.getJogador(),1);
        GerenciadorEfeitos.evocarSeguidor(new CriaAracnidea(this.getMesa(), this.getJogador()));
    }
}
