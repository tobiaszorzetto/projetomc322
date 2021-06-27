package br.com.unicamp.projetofinal.Cartas;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Enums.Traco;

public class Feitico extends Carta {

    private Traco traco = Traco.NENHUM;

    public Feitico(String nome, int custo_mana, Mesa mesa, Jogador jogador){
        super(nome, custo_mana, mesa, jogador);

    }


    public void atuarNaMesa(Jogador jogador) {
        this.getJogador().addManaGastaFeitico(this.getMana());
        this.realizarEfeito();
    }

    @Override
    public void jogarCarta() {
        Jogador jogador = this.getJogador();
        Deck mao = jogador.getMao();
        Mesa mesa = this.getMesa();
        int mana_de_feitico = jogador.getManaDeFeitico();
        int mana = jogador.getMana();

        if(mana_de_feitico>=this.getMana()){
            this.atuarNaMesa(jogador);
            mao.removerCarta(this);
            jogador.setManaDeFeitico(mana_de_feitico - this.getMana());
        }
        else if (mana + mana_de_feitico >=this.getMana()){
            jogador.setManaDeFeitico(0);
            this.atuarNaMesa(jogador);
            mao.removerCarta(this);
            jogador.setMana(mana + mana_de_feitico - this.getMana());
        }
        else{
            System.out.println("Sem mana suficiente!");
        }
    }

    protected void realizarEfeito(){

    }

    public Traco getTraco() {
        return traco;
    }

    public void setTraco(Traco traco) {
        this.traco = traco;
    }

    @Override
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) {

    }
}
