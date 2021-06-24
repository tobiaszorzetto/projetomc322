package br.com.unicamp.projetofinal;

public class Duelista extends Seguidor{


    public Duelista(Mesa mesa, Jogador jogador, GerenciadorEfeitos ge) {
        super("Duelista", 3, 3, 2, mesa, jogador, ge);
    }

    @Override
    public void verificarCondicao() {
        if(this.getMatouAlguem()){
            this.setMatouAlguem(false);
            this.ge.colocarCartaNaMao(this, new Poro(this.getMesa(), this.getJogador(), this.ge));
        }
    }

}
