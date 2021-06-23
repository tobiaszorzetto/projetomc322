package br.com.unicamp.projetofinal;

public abstract class Feitico extends Carta{
    private String tipo;

    public Feitico(String nome, int custo_mana, Mesa mesa, int jogador, String tipo){
        super(nome, custo_mana, mesa, jogador);
        this.tipo = tipo;
        this.tipo = tipo;
    }

    public abstract void efeito();
}
