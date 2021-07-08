package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

/*
* Custo de mana: 2
* Sacrifica uma carta na mesa para sortear duas do deck
*
* */

import java.util.ArrayList;

public class VislumbreDoAlem extends Feitico {

    public VislumbreDoAlem(Mesa mesa, Jogador jogador){
        super("Vislumbre do além", 2, mesa, jogador);
    }

    @Override
    protected void realizarEfeito(){
        int posicao_sacrificio = PrintFactory.pedirInput("Escolha a carta que deseja sacrificar");
        ArrayList<Seguidor> cartas_mesa = this.getMesa().getCartasMesa(this.getJogador());
        if (cartas_mesa.get(posicao_sacrificio -1) == null){//se nao tiver carta na posicao, nao acontece o sacrificio
            System.out.println("Nao ha carta na posicao escolhida para sacrificio");
        } else{//se houver carta na posicao, occore o sacrificio
            try{
                Seguidor sacrificio = cartas_mesa.get(posicao_sacrificio - 1);
                sacrificio.matarSeguidor();
                Carta conseguiu_sortear = this.getJogador().sortearDoDeck();
                if (conseguiu_sortear != null){//se conseguiu sortear na primeira, tenta a segunda
                    this.getJogador().sortearDoDeck();
                }
            } catch (IndexOutOfBoundsException e){
                System.out.println("Posicao digitada eh invalida");
                realizarEfeito();
            } catch (ManaInsuficienteException | PosicaoMesaOcupadaException e) {
                e.printStackTrace();
            }
        }
    }
}
