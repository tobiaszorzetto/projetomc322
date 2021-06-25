package br.com.unicamp.projetofinal;

public class Poro extends Seguidor{
    public Poro(Mesa mesa, Jogador jogador, GerenciadorEfeitos ge) {
        super("Poro", 1, 2, 1, mesa, jogador, ge);
    }

    @Override
    public void verificarCondicao() {

    }
}
