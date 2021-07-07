package br.com.unicamp.projetofinal.Cartas.Campeoes;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Campeao;

public class Darius extends Campeao {
    int ultima_rodada_que_viu = 0;
    public Darius(Mesa mesa, Jogador jogador) {
        super("Darius", 6, 6, 5, mesa, jogador);
    }

    @Override
    public void checarLevelUp() {
        if(this.ultima_rodada_que_viu< this.getMesa().getRodada() && this.getJogador().getVida()<=10){
            GerenciadorEfeitos.aumentarAtaqueVida(this,1,1);
            ultima_rodada_que_viu = this.getMesa().getRodada();
        }
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        this.checarLevelUp();
    }
}
