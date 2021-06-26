package br.com.unicamp.projetofinal;

import java.util.ArrayList;

public class Garen extends Campeao{

    public Garen(Mesa mesa, Jogador jogador, GerenciadorEfeitos ge){
        super("Garen",5,5,5, mesa, jogador, ge);
    }
    @Override
    public void checarLevelUp() {
        if(getVezesQueAtacou() == 2 && getNaoUpou()){
            this.ge.aumentarAtaqueVida(this, 1, 1);
            this.setTraco(Traco.ELUSIVO);
            this.upou();

        }
    }

    @Override
    public void verificarCondicao() {
        if(this.getMesa().getParteDaRodada() == 3){
            this.setVidaOriginal();
        }
        this.checarLevelUp();
    }

}
