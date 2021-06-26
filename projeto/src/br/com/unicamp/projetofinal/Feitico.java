package br.com.unicamp.projetofinal;

public abstract class Feitico extends Carta{

    public Feitico(String nome, int custo_mana, Mesa mesa, Jogador jogador, GerenciadorEfeitos ge){
        super(nome, custo_mana, mesa, jogador, ge);

    }

}
