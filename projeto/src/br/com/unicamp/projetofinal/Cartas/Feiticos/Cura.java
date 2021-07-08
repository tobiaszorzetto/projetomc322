package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

/*
*  3
*
*  Cura inteiramente uma carta aliada de sua escolha
*
* */

public class Cura extends Feitico {


    public Cura(Mesa mesa, Jogador jogador) {
        super("Cura", 3, mesa, jogador);
    }

    @Override
    protected void realizarEfeito() {
        GerenciadorEfeitos.escolherCartaCurar(this);
    }

}

