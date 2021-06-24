package br.com.unicamp.projetofinal;

public class PoroDefensor extends Seguidor{

    public PoroDefensor(Mesa mesa, Jogador jogador) {
        super("Poro Defensor", 1, 1, 2, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {

    }
}
