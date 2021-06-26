package br.com.unicamp.projetofinal;

import Cartas.Seguidor;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GerenciadorEfeitos {

    public GerenciadorEfeitos(){

    }

    public static void aumentarVida(Seguidor carta, int vida){
        carta.aumentarVida(vida);
    }

    public static void aumentarAtaque(Seguidor carta, int ataque){
        carta.aumentarAtaque(ataque);
    }

    public static void aumentarAtaqueVida(Seguidor carta, int ataque, int vida){
        GerenciadorEfeitos.aumentarAtaque(carta, ataque);
        GerenciadorEfeitos.aumentarVida(carta, vida);
    }

    public static void curar(Seguidor carta){
        carta.setVidaOriginal();
    }

    public static void curar(Seguidor carta, int quant){
        GerenciadorEfeitos.aumentarVida(carta, quant);
    }

    public static void escolherCartaCurar(Carta carta){
        int numero_carta;
        ArrayList<Seguidor> cartas_na_mesa = carta.getMesa().getCartasMesa(carta.getJogador());
        while(true){
            numero_carta = GerenciadorEfeitos.pedirInput(carta.getJogador().getNome() + ", quais dessas cartas deseja curar?") - 1;
            if (numero_carta<= cartas_na_mesa.size() && numero_carta>=0){
                GerenciadorEfeitos.curar(cartas_na_mesa.get(numero_carta));
                break;
            } else {
                System.out.println("indice nao existente");
            }
        }
    }

    public static void cartaAleatoriaAtacarNexus(Seguidor carta, ArrayList<Seguidor> cartas_na_mesa){
        Random sorteio = new Random();

        Mesa mesa = carta.getMesa();
        Jogador jogador = carta.getJogador();

        if (mesa.temCartasMesa(jogador)){
            while(true) {
                Seguidor carta_sorteada = cartas_na_mesa.get(sorteio.nextInt(6));
                if (carta_sorteada!=null && carta_sorteada != carta) {// vai dar algo errado?
                    carta_sorteada.atacarNexus(carta.getAdversario());
                    break;
                }
            }
        }
    }
    public static void colocarCartaNaMao(Seguidor carta, Carta carta_vai_pra_mao){
        Jogador jogador= carta.getJogador();
        jogador.colocarCartaNaMao(carta_vai_pra_mao);
    }

    public static int pedirInput(String mensagem){
        Scanner scan = new Scanner(System.in);
        System.out.println(mensagem);
        return scan.nextInt();
    }

}
