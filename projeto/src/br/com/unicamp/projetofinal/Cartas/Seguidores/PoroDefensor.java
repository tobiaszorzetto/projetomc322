package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

public class PoroDefensor extends Seguidor {

    public PoroDefensor(Mesa mesa, Jogador jogador) {
        super("Poro Defensor", 1, 1, 2, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {

    }

    @Override
    public void matarSeguidor(){
        this.getMesa().getCartasMesa(this.getJogador()).remove(this);

        this.getJogador().sortearDoDeck();
        //pensar num jeito aqui
        this.getMesa().verificarCondicoes();
    }
}
