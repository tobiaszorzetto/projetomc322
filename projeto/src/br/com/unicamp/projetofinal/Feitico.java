package br.com.unicamp.projetofinal;

public abstract class Feitico extends Carta{
    private String tipo;

    public Feitico(String nome, String tipo, int custo_mana, Mesa mesa){
        super(nome, custo_mana, mesa);
        this.tipo = tipo;
    }

    public abstract void efeito();
}
