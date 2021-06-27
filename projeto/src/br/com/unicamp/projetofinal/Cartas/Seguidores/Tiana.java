package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

import java.util.ArrayList;

public class Tiana extends Seguidor {

    public Tiana (Mesa mesa, Jogador jogador) {
        super("Tiana", 8, 7, 7, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {

    }

    private void fazerEfeito(){
        ArrayList<Seguidor> cartas_na_mesa = this.getMesa().getCartasMesa(this.getJogador());
        GerenciadorEfeitos.cartaAleatoriaAtacarNexus(this, cartas_na_mesa);
    }

    public void atuarNaMesa(Jogador jogador, int posicao_alocacao){
        this.fazerEfeito();
        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
        this.getMesa().verificarCondicoes();
    }
}
