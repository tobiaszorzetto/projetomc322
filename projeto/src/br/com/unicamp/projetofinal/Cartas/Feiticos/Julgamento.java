package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;


/*
*
* Custo mana: 8
* Escolhe uma carta para golpear (nao perde vida) em todas as cartas do inimigo
*
*
* */

public class Julgamento extends Feitico {

    public Julgamento( Mesa mesa, Jogador jogador) {
        super("Julgamento", 8, mesa, jogador);
    }

    public void realizarEfeito() throws PosicaoMesaOcupadaException, ManaInsuficienteException{
        GerenciadorEfeitos.escolherCartaBaterEmTodos(this);
    }
}
