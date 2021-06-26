package Cartas.Seguidores;

import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;
import Cartas.Seguidor;

import java.util.ArrayList;

public class Vanguarda extends Seguidor {

    private boolean acabou_de_ser_colocada = true;

    public Vanguarda(Mesa mesa, Jogador jogador) {
        super("Vanguarda", 4, 3, 3, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {
        if (acabou_de_ser_colocada){
            acabou_de_ser_colocada = false;

            ArrayList<Seguidor> cartas_na_mesa = this.getMesa().getCartasMesa(this.getJogador());

            for (Seguidor carta: cartas_na_mesa){
                GerenciadorEfeitos.aumentarAtaqueVida(carta, 1,1);
            }

        }
    }
}
