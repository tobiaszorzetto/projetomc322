package Cartas.Seguidores;

import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;
import Cartas.Seguidor;

public class Duelista extends Seguidor {


    public Duelista(Mesa mesa, Jogador jogador) {
        super("Duelista", 3, 3, 2, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {
        if(this.getMatouAlguem()){
            this.setMatouAlguem(false);
            GerenciadorEfeitos.colocarCartaNaMao(this, new Poro(this.getMesa(), this.getJogador()));
        }
    }

}
