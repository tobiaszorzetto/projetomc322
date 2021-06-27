package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

public class Julgamento extends Feitico {

    public Julgamento( Mesa mesa, Jogador jogador) {
        super("Julgamento", 8, mesa, jogador);
    }

    public void realizarEfeito(){
        GerenciadorEfeitos.escolherCartaBaterEmTodos(this);
    }
}
