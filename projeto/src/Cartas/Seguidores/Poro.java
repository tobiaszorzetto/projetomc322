package Cartas.Seguidores;

import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;
import Cartas.Seguidor;

public class Poro extends Seguidor {
    public Poro(Mesa mesa, Jogador jogador) {
        super("Poro", 1, 2, 1, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {

    }
}
