package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;

/*
*Custo de mana: 4
* Ao ser utilizada, retorna uma carta escolhida para sua mao e uma carta escolhida do adversario para a mao dele
*
* */

public class VoltaAoLar extends Feitico {
    public VoltaAoLar(Mesa mesa, Jogador jogador) {
        super("Volta Ao Lar", 4, mesa, jogador);
    }

    @Override
    protected void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

        if(GerenciadorEfeitos.retornarCartaMao(this.getJogador()))
            GerenciadorEfeitos.retornarCartaMao(this.getAdversario());
    }
}
