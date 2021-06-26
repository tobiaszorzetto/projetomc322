package br.com.unicamp.projetofinal;

import java.util.ArrayList;
import java.util.Scanner;

public class Cura extends Feitico{


    public Cura(Mesa mesa, Jogador jogador) {
        super("Cura", 3, mesa, jogador);
    }

    @Override
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) {
        GerenciadorEfeitos.escolherCartaCurar(this);
    }
}

