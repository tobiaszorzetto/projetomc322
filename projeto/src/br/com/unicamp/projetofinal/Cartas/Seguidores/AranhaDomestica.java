package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

/*
*  2 | 2 | 2
*
*  Ao entrar no jogo evoca uma cria aracnidea
*
*
* */

public class AranhaDomestica extends Seguidor {

    public AranhaDomestica(Mesa mesa, Jogador jogador) {
        super("Aranha Doméstica", 2, 2, 2, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException {

    }

    @Override
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
        if (posicao_alocacao<0){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(this.getMesa().getCartasMesa(jogador).get(posicao_alocacao-1) != null){
            throw new PosicaoMesaOcupadaException();
        }
        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
        GerenciadorEfeitos.evocarSeguidor(new CriaAracnidea(this.getMesa(),this.getJogador()));
    }

}
