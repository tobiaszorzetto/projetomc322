package br.com.unicamp.projetofinal.Cartas.Campeoes;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Campeao;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

import java.util.ArrayList;

public class AurelionSol extends Campeao {

    public AurelionSol(Mesa mesa, Jogador jogador){
        super("Aurelion Sol", 10, 10, 10, mesa, jogador);
    }

    @Override
    public void checarLevelUp() {
        if (this.getMesa().getParteDaRodada() == 3 && calcularPoderAliados() >= 25){
            this.aumentarAtaque(1);
            this.aumentarVida(1);
        }
    }

    public int calcularPoderAliados(){
        ArrayList<Seguidor> cartas_mesa_aliadas = this.getMesa().getCartasMesa(this.getJogador());
        int poder = 0;
        for (Seguidor seguidor : cartas_mesa_aliadas){
            if (seguidor != null){
                poder += seguidor.getAtaque();
            }
        }
        return poder;
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        checarLevelUp();
        this.getJogador().sortearDoDeck();
    }
}
