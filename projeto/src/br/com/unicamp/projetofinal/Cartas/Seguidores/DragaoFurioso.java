package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Traco;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;

/*
*  5 | 4 | 5
*
*  Traco -> Dragao Furioso
*
* */

public class DragaoFurioso extends Seguidor {
    public DragaoFurioso(Mesa mesa, Jogador jogador) {
        super("Dragao Furioso", 5, 4, 5, mesa, jogador);
        this.setTraco(Traco.FURIA);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

    }
}
