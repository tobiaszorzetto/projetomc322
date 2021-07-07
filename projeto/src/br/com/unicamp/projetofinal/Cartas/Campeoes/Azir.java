package br.com.unicamp.projetofinal.Cartas.Campeoes;

import br.com.unicamp.projetofinal.Cartas.Campeao;
import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Cartas.Seguidores.SoldadoDeAreia;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;

public class Azir extends Campeao {
    int unidades_evocadas = 0;

    public Azir(String nome, int custo_mana, int ataque, int vida, Mesa mesa, Jogador jogador) {
        super("Azir", 3, 1, vida, mesa, jogador);
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
        if (this.getMesa().getParteDaRodada() == 1){
            for(Seguidor carta: this.getMesa().getCartasMesa(this.getJogador())){
                if (carta.getVaiAtacar()){
                    this.unidades_evocadas++;
                    GerenciadorEfeitos.evocarSeguidorAtacante(new SoldadoDeAreia(this.getMesa(),this.getJogador()));
                }
            }
        }
    }
}
