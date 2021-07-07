package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;

public class LotusDaMorte extends Feitico {

    public LotusDaMorte(Mesa mesa, Jogador jogador){
        super("Lótus da Morte", 2, mesa, jogador);
    }

    @Override
    protected void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.AtacarTodasAsCartas(this.getMesa(), 1);
    }
}
