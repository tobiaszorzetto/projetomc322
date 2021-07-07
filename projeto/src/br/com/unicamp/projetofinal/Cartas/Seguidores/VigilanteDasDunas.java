package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Seguidor;


public class VigilanteDasDunas extends Seguidor {

    public VigilanteDasDunas(Mesa mesa, Jogador jogador){
        super("Vigilante Das Dunas", 1, 1, 2, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException {
    }

    @Override
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException {
        if (posicao_alocacao<0){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(this.getMesa().getCartasMesa(jogador).get(posicao_alocacao-1) != null){
            throw new PosicaoMesaOcupadaException();
        }
        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
        GerenciadorEfeitos.evocarSeguidor(new SoldadoDeAreia(this.getMesa(), this.getJogador()));
    }
}
