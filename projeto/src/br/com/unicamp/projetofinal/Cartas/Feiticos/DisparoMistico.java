package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.Enums.Traco;

public class DisparoMistico extends Feitico{

    public DisparoMistico(Mesa mesa, Jogador jogador) {
        super("Disparo Mistico", 2, mesa, jogador);

    }

    public void realizarEfeito() throws PosicaoMesaOcupadaException, ManaInsuficienteException {
        GerenciadorEfeitos.escolherCoisaParaDarDano(this, 2);
    }


}
