package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Campeoes.Swain;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

/*
*  8 | 5 | 8
*
*  Ao ser colocado na mesa coloca uma carta Swain na mao do jogador
*
*  No inicio de rodada, da 3 de dano ao nexus adversario
* */

public class OLeviata extends Seguidor {
    public OLeviata(Mesa mesa, Jogador jogador) {
        super("O leviata", 8, 5, 8, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        if(this.getMesa().getParteDaRodada() == 4){
            GerenciadorEfeitos.atacarNexus(this.getAdversario(), 3);
        }
    }

    @Override
    protected void realizarEfeitoAntesDeColocado() {
        GerenciadorEfeitos.colocarCartaNaMao(this, new Swain(this.getMesa(), this.getJogador()));
    }


}
