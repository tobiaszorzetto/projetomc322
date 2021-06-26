package Cartas;

import br.com.unicamp.projetofinal.Carta;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

public abstract class Feitico extends Carta {

    public Feitico(String nome, int custo_mana, Mesa mesa, Jogador jogador){
        super(nome, custo_mana, mesa, jogador);

    }

}
