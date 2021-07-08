package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feiticos.Dizimar;
import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Traco;

public class CapitaoFarron extends Seguidor {
    public CapitaoFarron(Mesa mesa, Jogador jogador) {
        super("Capitao Farron", 8, 8, 8, mesa, jogador);
        this.setTraco(Traco.SOBREPUJAR);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

    }
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
        if (posicao_alocacao<0){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(this.getMesa().getCartasMesa(jogador).get(posicao_alocacao-1) != null){
            throw new PosicaoMesaOcupadaException();
        }
        GerenciadorEfeitos.colocarCartaNaMao(this, new Dizimar(this.getMesa(),this.getJogador()));
        GerenciadorEfeitos.colocarCartaNaMao(this, new Dizimar(this.getMesa(),this.getJogador()));
        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
    }
}
