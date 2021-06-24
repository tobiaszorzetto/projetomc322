package br.com.unicamp.projetofinal;

public class Vanguarda extends Seguidor{

    public Vanguarda(Mesa mesa, Jogador jogador) {
        super("Vanguarda", 4, 3, 3, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {

    }
}
