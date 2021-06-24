package br.com.unicamp.projetofinal;

import java.util.ArrayList;

public class Vanguarda extends Seguidor{

    private boolean acabou_de_ser_colocada = true;

    public Vanguarda(Mesa mesa, Jogador jogador, GerenciadorEfeitos ge) {
        super("Vanguarda", 4, 3, 3, mesa, jogador, ge);
    }

    @Override
    public void verificarCondicao() {
        if (acabou_de_ser_colocada){
            acabou_de_ser_colocada = false;

            ArrayList<Seguidor> cartas_na_mesa = this.getMesa().getCartasMesa(this.getJogador());

            for (Seguidor carta: cartas_na_mesa){
                this.ge.aumentarAtaqueVida(carta, 1,1);
            }

        }
    }
}
