package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;

public class CriaAracnidea extends Seguidor {
    public CriaAracnidea(Mesa mesa, Jogador jogador) {
        super("Cria Aracnidea", 1, 1, 1, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException {

    }
}
