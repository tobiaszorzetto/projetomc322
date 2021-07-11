package br.com.unicamp.projetofinal.Cartas.Campeoes;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Campeao;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

import java.util.ArrayList;

/*
*
* 10 | 10 | 10
*
* LevelUp: No fim da rodada, se o ataque dos aliados somados for > 25, soma adiciona 3|3
* Condicao: sorteia uma carta do deck a mais no inicio da rodada
*
* */

public class AurelionSol extends Campeao {

    public AurelionSol(Mesa mesa, Jogador jogador){
        super("Aurelion Sol", 10, 10, 10, mesa, jogador);
    }

    @Override
    public void checarLevelUp() {
        if (this.getMesa().getParteDaRodada() == 3 && calcularPoderAliados() >= 25){
            this.aumentarAtaque(3);
            this.aumentarVida(3);
        }
    }

    public int calcularPoderAliados(){
        ArrayList<Seguidor> cartas_mesa_aliadas = this.getMesa().getCartasMesa(this.getJogador());
        int poder = 0;
        for (Seguidor seguidor : cartas_mesa_aliadas){
            if (seguidor != null && seguidor!=this){
                poder += seguidor.getAtaque();
            }
        }
        return poder;
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        checarLevelUp();
        if (this.getMesa().getParteDaRodada() == 4){
            this.getJogador().sortearDoDeck();
        }
    }
}
