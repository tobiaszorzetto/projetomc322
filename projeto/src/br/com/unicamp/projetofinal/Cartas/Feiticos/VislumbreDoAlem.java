package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

import java.util.ArrayList;

public class VislumbreDoAlem extends Feitico {

    public VislumbreDoAlem(Mesa mesa, Jogador jogador){
        super("Vislumbre do além", 2, mesa, jogador);
    }

    @Override
    protected void realizarEfeito(){
        int posicao_sacrificio = PrintFactory.pedirInput("Escolha a carta que deseja sacrificar");
        ArrayList<Seguidor> cartas_mesa = this.getMesa().getCartasMesa(this.getJogador());
        try{
            cartas_mesa.remove(posicao_sacrificio - 1);
            this.getJogador().sortearDoDeck();
            this.getJogador().sortearDoDeck();
        } catch (IndexOutOfBoundsException e){
            System.out.println("Posicao digitada eh invalida");
            realizarEfeito();
        } catch (NullPointerException e){
            System.out.println("Não há carta nessa posicao");
            realizarEfeito();
        }

    }
}
