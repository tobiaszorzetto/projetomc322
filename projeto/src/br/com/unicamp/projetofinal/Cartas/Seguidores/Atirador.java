package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;

/*
*  3 | 3 | 1
*
*  Ao ser colocado no jogo, da 1 de dano a um seguidor oponente que esta na mesa
*
* */

public class Atirador extends Seguidor {
    public Atirador(Mesa mesa, Jogador jogador) {
        super("Atirador", 3, 3, 1, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {

    }

    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException{
        GerenciadorEfeitos.escolherCartaParaDarDano(this,this.getAdversario(), 1);
        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
    }
}
