package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;

public class VoltaAoLar extends Feitico {
    public VoltaAoLar(Mesa mesa, Jogador jogador) {
        super("Volta Ao Lar", 4, mesa, jogador);
    }

    @Override
    protected void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.retornarCartaMao(this.getJogador());
        GerenciadorEfeitos.retornarCartaMao(this.getAdversario());
    }
}
