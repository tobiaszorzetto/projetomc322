package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.Enums.Traco;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;

/*
*  0
*
*  Causa 4 de mana a uma carta adversaria da sua escolha
*
* */

public class CentellhaFinal extends Feitico {

    public CentellhaFinal(Mesa mesa, Jogador jogador) {
        super("Centelha Final", 0, mesa, jogador);
        this.setTraco(Traco.SOBREPUJAR);
    }

    public void realizarEfeito() throws PosicaoMesaOcupadaException, ManaInsuficienteException{
        GerenciadorEfeitos.escolherCartaParaDarDano(this, this.getAdversario(), 4);
    }
}
