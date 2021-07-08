package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

import java.util.ArrayList;

public class MiquinhoExplosivo extends Seguidor {
    int rodada_criado;
    public MiquinhoExplosivo(Mesa mesa, Jogador jogador) {
        super("MiquinhoExplosivo", 1, 2, 1, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        if(this.getMesa().getRodada()>rodada_criado){
            this.matarSeguidor();
        }
    }

    @Override
    public void matarSeguidor() throws ManaInsuficienteException, PosicaoMesaOcupadaException{
        this.setMorreu();
        GerenciadorEfeitos.atacarNexus(this.getAdversario(), 1);
        int posicao = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);
        this.getMesa().getCartasMesa(this.getJogador()).remove(this);//remove da lista
        this.getMesa().getCartasMesa(this.getJogador()).add(posicao, null);//adiciona null no lugar
    }


    @Override
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
        if (posicao_alocacao<0){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(this.getMesa().getCartasMesa(jogador).get(posicao_alocacao-1) != null){
            throw new PosicaoMesaOcupadaException();
        }
        this.rodada_criado= this.getMesa().getRodada();
        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
    }

}
