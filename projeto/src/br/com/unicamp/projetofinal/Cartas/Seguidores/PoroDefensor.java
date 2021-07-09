package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

/*
* 1 | 1 | 2
*
* Quando morre -> compra uma carta do deck
*
* */

public class PoroDefensor extends Seguidor {

    public PoroDefensor(Mesa mesa, Jogador jogador) {
        super("Poro Defensor", 1, 1, 2, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {
    }

    @Override
    public void matarSeguidor() throws ManaInsuficienteException, PosicaoMesaOcupadaException{
        this.setMorreu();
        int posicao = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);
        this.getMesa().getCartasMesa(this.getJogador()).set(posicao, null);//remove da lista e coloca null no lugar
        this.getJogador().sortearDoDeck();
    }
}
