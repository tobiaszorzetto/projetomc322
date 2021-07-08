package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

import java.util.ArrayList;

/*
*  8 | 7 | 7
*
*  Ao ser colocada na mesa -> um seguidor aliado ateatorio que esta na mesa ataca o nexus inimigo
*
* */

public class Tiana extends Seguidor {

    public Tiana (Mesa mesa, Jogador jogador) {
        super("Tiana", 8, 7, 7, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {

    }

    private void fazerEfeito(){
        ArrayList<Seguidor> cartas_na_mesa = this.getMesa().getCartasMesa(this.getJogador());
        GerenciadorEfeitos.cartaAleatoriaAtacarNexus(this, cartas_na_mesa);
    }

    @Override
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
        if (posicao_alocacao<0){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(this.getMesa().getCartasMesa(jogador).get(posicao_alocacao-1) != null){
            throw new PosicaoMesaOcupadaException();
        }
        this.fazerEfeito();
        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
    }
}
