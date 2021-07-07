package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;

public class RaioTermogenico extends Feitico {

    public RaioTermogenico(Mesa mesa, Jogador jogador){
        super("Raio Termogênico", 0,mesa, jogador);
    }

    @Override
    protected void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        int mana_jogador = this.getJogador().getMana();
        this.getJogador().setMana(0);
        GerenciadorEfeitos.escolherCartaParaDarDano(this, this.getAdversario(), mana_jogador);
    }

}
