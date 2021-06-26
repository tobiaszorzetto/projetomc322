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

    }

    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) {
        ArrayList<Seguidor> cartas_na_mesa = this.getMesa().getCartasMesa(this.getJogador());

        for (Seguidor carta: cartas_na_mesa){
            if(carta!=null)
                GerenciadorEfeitos.aumentarAtaqueVida(carta, 1,1);
        }

        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
        this.getMesa().verificarCondicoes();
    }
}
