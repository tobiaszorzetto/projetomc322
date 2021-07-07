package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;

public class CombateUmAUm extends Feitico {
    public CombateUmAUm(String nome, int custo_mana, Mesa mesa, Jogador jogador) {
        super(nome, custo_mana, mesa, jogador);
    }

    public void realizarEfeito() throws ManaInsuficienteException {
        GerenciadorEfeitos.escolherCartasCombate(this);
    }
}
