package Cartas.Seguidores;

import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;
import Cartas.Seguidor;

import java.util.ArrayList;

public class Tiana extends Seguidor {

    private boolean acabou_de_ser_colocada = true;

    public Tiana (Mesa mesa, Jogador jogador) {
        super("Tiana", 8, 7, 7, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {
        if (acabou_de_ser_colocada){
            acabou_de_ser_colocada = false;

            ArrayList<Seguidor> cartas_na_mesa = this.getMesa().getCartasMesa(this.getJogador());
            GerenciadorEfeitos.cartaAleatoriaAtacarNexus(this, cartas_na_mesa);
        }
    }
}
