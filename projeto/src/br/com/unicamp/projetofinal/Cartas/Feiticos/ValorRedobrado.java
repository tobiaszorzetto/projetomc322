package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

/*
*
* Custo mana: 6
* Efeito: Escolhe uma carta para dobrar ataque e vida
*
* */


public class ValorRedobrado extends Feitico {
    public ValorRedobrado( Mesa mesa, Jogador jogador) {
        super("Valor redobrado", 6, mesa, jogador);
    }

    public void realizarEfeito(){
        GerenciadorEfeitos.escolherCartaDobrarValores(this);
    }
}
