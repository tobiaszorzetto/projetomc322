package br.com.unicamp.projetofinal.Cartas;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Enums.Traco;

public abstract class Feitico extends Carta {

    private Traco traco = Traco.NENHUM;

    public Feitico(String nome, int custo_mana, Mesa mesa, Jogador jogador){
        super(nome, custo_mana, mesa, jogador);

    }

    public void atuarNaMesa(Jogador jogador) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        this.getJogador().addManaGastaFeitico(this.getMana());
        this.realizarEfeito();
    }
    
    public void verificarCondicao(){

    }
    //kkkkkkkk vc escreveu conficao
    @Override
    public void jogarCarta() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
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
            try{
                jogador.setMana(mana + mana_de_feitico - this.getMana());
                jogador.setManaDeFeitico(0);
                this.atuarNaMesa(jogador);
                mao.removerCarta(this);
            }
            catch (ManaInsuficienteException e){
                System.out.println("Sem mana suficiente!");
            }

        }

    }

    protected abstract void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException;

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
