package br.com.unicamp.projetofinal;

import java.util.ArrayList;

public class Garen extends Campeao{

    public Garen(Mesa mesa, Jogador jogador){
        super("Garen",5,5,5, mesa, jogador);
    }
    @Override
    public void checarLevelUp() {
        if(getVezesQueAtacou() == 2 && getNaoUpou()){
            this.aumentarVida(1);
            this.aumentarAtaque(1);
            this.setTraco(Traco.ELUSIVO);
            this.upou();

        }
    }

    @Override
    public void verificarCondicao() {
        this.checarLevelUp();
    }

}
