package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;

/*
*
* 1 | 1 | 1
*
*
*
*
*
*/


public class SoldadoDeAreia extends Seguidor{

    private int rodada_criado;

    public SoldadoDeAreia(Mesa mesa, Jogador jogador){
        super("Soldado de Areia", 1, 1, 1, mesa, jogador);
    }
    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException{
        if (this.getMesa().getRodada() > this.rodada_criado){
            this.matarSeguidor();
        }
    }

    @Override
    protected void realizarEfeitoAntesDeColocado() {
        this.rodada_criado = this.getMesa().getRodada();
        GerenciadorEfeitos.atacarNexus(this.getAdversario(), 1);
    }

}
