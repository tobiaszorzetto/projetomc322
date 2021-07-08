package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Traco;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;

/*
*  3 | 4 | 3
*
* Traco -> Sobrepujar
*
* */

public class BalistaDeFerro extends Seguidor {
    public BalistaDeFerro(Mesa mesa, Jogador jogador) {
        super("Balista De Ferro", 3, 4, 3, mesa, jogador);
        this.setTraco(Traco.SOBREPUJAR);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

    }
}
