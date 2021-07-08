package br.com.unicamp.projetofinal.Cartas.Campeoes;

import br.com.unicamp.projetofinal.Cartas.Campeao;
import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Cartas.Seguidores.SoldadoDeAreia;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;

/*
*   3 | 1 | 5
*
*   Para upar: invocou 13+ cartas
*
*
*   Para cada aliado que atacar invoque uma carta soldado de areia
*
*
* */

public class Azir extends Campeao {
    int unidades_evocadas = 0;
    int quant = 0;

    public Azir(Mesa mesa, Jogador jogador) {
        super("Azir", 3, 1, 5, mesa, jogador);
    }

    @Override
    public void checarLevelUp() {
        if(unidades_evocadas>=13){
            GerenciadorEfeitos.aumentarAtaqueVida(this,1,1);
            this.unidades_evocadas-=13;
        }
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        this.checarLevelUp();
        if (this.getMesa().getParteDaRodada() == 1){
            for(Seguidor carta: this.getMesa().getCartasMesa(this.getJogador())){
                if (carta!= null && carta.getVaiAtacar() && carta != this){
                    this.quant++;
                    this.unidades_evocadas++;
                }
            }
            for( int i= 0; i<quant;i++)  GerenciadorEfeitos.evocarSeguidorAtacante(new SoldadoDeAreia(this.getMesa(),this.getJogador()));
            this.quant = 0;
        }

    }
}
