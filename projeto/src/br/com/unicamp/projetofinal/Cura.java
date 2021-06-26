package br.com.unicamp.projetofinal;

import java.util.ArrayList;
import java.util.Scanner;

public class Cura extends Feitico{


    public Cura(Mesa mesa, Jogador jogador, GerenciadorEfeitos ge) {
        super("Cura", 3, mesa, jogador, ge);
    }

    @Override
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) {
        this.ge.escolherCartaCurar(this);
    }
}

