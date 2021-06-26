package br.com.unicamp.projetofinal;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

    public void curar(Seguidor carta, int quant){
        this.aumentarVida(carta, quant);
    }

    public void escolherCartaCurar(Carta carta){
        int numero_carta;
        ArrayList<Seguidor> cartas_na_mesa = carta.getMesa().getCartasMesa(carta.getJogador());
        while(true){
            numero_carta = this.pedirInput(carta.getJogador().getNome() + ", quais dessas cartas deseja curar?") - 1;
            if (numero_carta<= cartas_na_mesa.size() && numero_carta>=0){
                this.curar(cartas_na_mesa.get(numero_carta));
                break;
            } else {
                System.out.println("indice nao existente");
            }
        }
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

    public int pedirInput(String mensagem){
        Scanner scan = new Scanner(System.in);
        System.out.println(mensagem);
        return scan.nextInt();
    }

}
