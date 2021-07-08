package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

import java.util.ArrayList;

/*
* 4 | 3 | 3
*
* ao ser colocada -> da 1|1 p todos os aliados na mesa
*
* */

public class Vanguarda extends Seguidor {


    public Vanguarda(Mesa mesa, Jogador jogador) {
        super("Vanguarda", 4, 3, 3, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {

    }

    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        ArrayList<Seguidor> cartas_na_mesa = this.getMesa().getCartasMesa(this.getJogador());

        for (Seguidor carta: cartas_na_mesa){
            if(carta!=null)
                GerenciadorEfeitos.aumentarAtaqueVida(carta, 1,1);
        }

        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);

    }
}
