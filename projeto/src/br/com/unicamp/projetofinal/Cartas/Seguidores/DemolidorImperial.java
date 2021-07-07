package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

public class DemolidorImperial extends Seguidor {
    public DemolidorImperial(String nome, int custo_mana, int ataque, int vida, Mesa mesa, Jogador jogador) {
        super("Demolidor Imperial", 2, 2, 3, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

    }

    @Override
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
        if (posicao_alocacao<0){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(this.getMesa().getCartasMesa(jogador).get(posicao_alocacao-1) != null){
            throw new PosicaoMesaOcupadaException();
        }

        GerenciadorEfeitos.darDanoEmAliadoParaAtacarNexus(this, 1, 2);

        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
    }

}
