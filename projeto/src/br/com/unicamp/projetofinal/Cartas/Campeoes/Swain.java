package br.com.unicamp.projetofinal.Cartas.Campeoes;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Campeao;

/* 5|3|6
 * 
 * LEVEL UP -> 12 DE DANO DADO FORA DE COMBATE
 * 
 * SE O NEXUS ALIADO EH ATACADO -> DA 3 DE DANO AO NEXUS INIMIGO 
 * funcionando
 * */

public class Swain extends Campeao {
    int dano_fora_de_combate = 0;

    public Swain(Mesa mesa, Jogador jogador) {
        super("Swain", 5, 3, 6, mesa, jogador);
    }

    @Override
    public void checarLevelUp() {
        if(dano_fora_de_combate>=12){
            GerenciadorEfeitos.aumentarAtaqueVida(this,1,1);
        }
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        if(this.getJogador().golpeAoNexusAtivo()){
            GerenciadorEfeitos.atacarNexus(this.getAdversario(),3);
            this.getJogador().setGolpeAoNexus(false);
            this.dano_fora_de_combate+=3;
            checarLevelUp();
        }
    }
}
