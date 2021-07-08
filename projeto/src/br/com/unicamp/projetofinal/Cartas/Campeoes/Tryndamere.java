package br.com.unicamp.projetofinal.Cartas.Campeoes;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Campeao;
import br.com.unicamp.projetofinal.Enums.Traco;

public class Tryndamere extends Campeao {

    public Tryndamere(Mesa mesa, Jogador jogador) {
        super("Tryndamere", 8, 8, 6, mesa, jogador);
        this.setTraco(Traco.SOBREPUJAR);
    }

    @Override
    public void checarLevelUp() {
        GerenciadorEfeitos.curar(this);
        GerenciadorEfeitos.aumentarAtaqueVida(this,1,1);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

    }
    public void matarSeguidor() throws ManaInsuficienteException, PosicaoMesaOcupadaException{
        if(this.naoMorreu()){
            this.checarLevelUp();
        }else{
            int posicao = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);
            this.getMesa().getCartasMesa(this.getJogador()).remove(this);//remove da lista
            this.getMesa().getCartasMesa(this.getJogador()).add(posicao, null);//adiciona null no lugar
        }
        this.setMorreu();
    }
}
