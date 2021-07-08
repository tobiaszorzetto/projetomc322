package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;

/*
*Custo de mana: 8
* Sorteia 3 cartas do deck (se houverem) e diminui seu custo de mana em 1
*
*
* */

public class DiaDoProgresso extends Feitico {

    public DiaDoProgresso(Mesa mesa, Jogador jogador){
        super("Dia do Progresso", 8, mesa, jogador);
    }

    @Override
    public void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        for (int i = 0; i < 3; i++){
            Carta sorteada = this.getJogador().sortearDoDeck();
            if (sorteada != null){
                sorteada.diminuirCustoMana(1);
            }
        }
    }
}
