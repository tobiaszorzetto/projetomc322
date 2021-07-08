package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

/*
*  3 | 3 | 3
*
*  Ao ser colocado na mesa -> cura um aliado ou o nexus em 3
*
* */

public class TaverneiroGentil extends Seguidor {
    public TaverneiroGentil(Mesa mesa, Jogador jogador) {
        super("Taverneiro Gentil", 3, 3, 3, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

    }

    @Override
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
        if (posicao_alocacao<=0){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(this.getMesa().getCartasMesa(jogador).get(posicao_alocacao-1) != null){
            throw new PosicaoMesaOcupadaException();
        }
        GerenciadorEfeitos.curarAliadoOuNexus(this, 3);
        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
    }
}
