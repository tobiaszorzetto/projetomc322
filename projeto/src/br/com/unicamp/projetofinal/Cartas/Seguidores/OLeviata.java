package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Campeoes.Swain;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

public class OLeviata extends Seguidor {
    public OLeviata(Mesa mesa, Jogador jogador) {
        super("O leviata", 8, 5, 8, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        if(this.getMesa().getParteDaRodada() == 0){
            GerenciadorEfeitos.atacarNexus(this.getAdversario(), 3);
        }
    }

    @Override
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
        if (posicao_alocacao<0){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(this.getMesa().getCartasMesa(jogador).get(posicao_alocacao-1) != null){
            throw new PosicaoMesaOcupadaException();
        }
        GerenciadorEfeitos.colocarCartaNaMao(this, new Swain(this.getMesa(), this.getJogador()));
        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
    }

}
