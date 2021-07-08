package br.com.unicamp.projetofinal.Cartas.Campeoes;
import br.com.unicamp.projetofinal.*;

import br.com.unicamp.projetofinal.Cartas.Campeao;
import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Traco;

/* 5|5|5
 * 
 * LEVEL UP -> 12 DE DANO DADO FORA DE COMBATE
 * 
 * 
 * 
 * 
 * */

public class Gangplank extends Campeao {

    int vezes_atacou_nexus = 0;

    public Gangplank(Mesa mesa, Jogador jogador) {
        super("Gangplank", 5, 5, 5, mesa, jogador);
        this.setTraco(Traco.SOBREPUJAR);
    }

    @Override
    public void checarLevelUp() {
        if(vezes_atacou_nexus>=5){
            vezes_atacou_nexus-=5;
            GerenciadorEfeitos.aumentarAtaqueVida(this,1,1);
        }

    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        this.checarLevelUp();
    }

    @Override
    public void verificarSobrepujar(Seguidor carta_adversario){
        GerenciadorEfeitos.atacarNexus(carta_adversario.getJogador(), - carta_adversario.getVidaAtual());
        vezes_atacou_nexus++;
    }
}
