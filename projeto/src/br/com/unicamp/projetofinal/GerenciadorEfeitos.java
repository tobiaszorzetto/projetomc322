package br.com.unicamp.projetofinal;

import java.util.ArrayList;
import java.util.Random;

public class GerenciadorEfeitos {

    public GerenciadorEfeitos(){

    }

    public void aumentarVida(Seguidor carta, int vida){
        carta.aumentarVida(vida);
    }

    public void aumentarAtaque(Seguidor carta, int ataque){
        carta.aumentarAtaque(ataque);
    }

    public void aumentarAtaqueVida(Seguidor carta, int ataque, int vida){
        this.aumentarAtaque(carta, ataque);
        this.aumentarVida(carta, vida);
    }

    public void curar(Seguidor carta){
        carta.setVidaOriginal();
    }

    public void cartaAleatoriaAtacarNexus(Seguidor carta, ArrayList<Seguidor> cartas_na_mesa){
        Random sorteio = new Random();

        int tamanho = cartas_na_mesa.size();

        if (tamanho>1){
            while(true) {
                int carta_sorteada = sorteio.nextInt(cartas_na_mesa.size());
                if (cartas_na_mesa.get(carta_sorteada) != carta) {// vai dar algo errado?
                    cartas_na_mesa.get(carta_sorteada).atacarNexus(carta.getAdversario());
                    break;
                }
            }
        }
    }
    public void colocarCartaNaMao(Seguidor carta, Carta carta_vai_pra_mao){
        Jogador jogador= carta.getJogador();
        jogador.colocarCartaNaMao(carta_vai_pra_mao);

    }

}
