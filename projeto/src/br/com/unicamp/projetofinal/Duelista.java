package br.com.unicamp.projetofinal;

public class Duelista extends Seguidor{


    public Duelista(Mesa mesa, Jogador jogador) {
        super("Duelista", 3, 3, 2, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {

    }
}
