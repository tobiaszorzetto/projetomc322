package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;

public class DiaDoProgresso extends Feitico {

    public DiaDoProgresso(Mesa mesa, Jogador jogador){
        super("Dia do Progresso", 8, mesa, jogador);
    }


    @Override
    protected void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        for (int i = 0; i < 3; i++){
            Carta sorteada = this.getJogador().sortearDoDeck();
            if (sorteada != null){
                sorteada.diminuirCustoMana();
            }
        }
    }
}
