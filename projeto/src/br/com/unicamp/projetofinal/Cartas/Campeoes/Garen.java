package br.com.unicamp.projetofinal.Cartas.Campeoes;

import br.com.unicamp.projetofinal.Cartas.Campeao;
import br.com.unicamp.projetofinal.*;

public class Garen extends Campeao {

    public Garen(Mesa mesa, Jogador jogador){
        super("Garen",5,5,5, mesa, jogador);
    }
    @Override
    public void checarLevelUp() {
        if(getVezesQueAtacou() == 2 && getNaoUpou()){
            GerenciadorEfeitos.aumentarAtaqueVida(this, 1, 1);
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
