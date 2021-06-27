package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

public class Cura extends Feitico {


    public Cura(Mesa mesa, Jogador jogador) {
        super("Cura", 3, mesa, jogador);
    }

    @Override
    protected void realizarEfeito() {
        GerenciadorEfeitos.escolherCartaCurar(this);
    }

}

