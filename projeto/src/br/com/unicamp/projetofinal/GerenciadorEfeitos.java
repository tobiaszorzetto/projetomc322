package br.com.unicamp.projetofinal;

import br.com.unicamp.projetofinal.Cartas.Feiticos.CombateUmAUm;
import br.com.unicamp.projetofinal.Cartas.Feiticos.ValorRedobrado;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

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

    public static void atacarNexus(Jogador jogador, int dano){
        jogador.diminuirVida(dano);
    }

    public static void cartaAleatoriaAtacarNexus(Carta carta, ArrayList<Seguidor> cartas_na_mesa){
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

    public static void escolherCartaAdversariaParaDarDano(Carta carta, int dano) {
        ArrayList<Seguidor> mesa_adversario = carta.getMesa().getCartasMesaAdversario(carta.getJogador());
        int numero_carta = GerenciadorEfeitos.pedirInput("Escolha uma carta inimiga para dar 1 de dano");
        Seguidor carta_adversario = mesa_adversario.get(numero_carta);
        if(carta_adversario!=null) {
            boolean morreu = carta_adversario.diminuirVida(dano);
            if(morreu){
                carta.verificarDepujante(carta_adversario);
            }
        }

    }

    public static void escolherCartaBaterEmTodos(Carta carta) {
        int numero_carta = pedirInput("Escolha uma carta para golpear os inimigos") - 1;

        Seguidor carta_escolhida = carta.getMesa().getCartasMesa(carta.getJogador()).get(numero_carta);

        ArrayList<Seguidor> cartas_adversario = carta.getMesa().getCartasMesa(carta.getAdversario());

        for(int i = 0; i<6; i++){
            if (cartas_adversario.get(i)!=null)
            cartas_adversario.get(i).diminuirVida(carta_escolhida.getAtaque());
        }
    }

    public static void escolherCartaDobrarValores(Carta carta) {
        ArrayList<Seguidor> cartas_mesa = carta.getMesa().getCartasMesa(carta.getJogador());

        int numero_carta = pedirInput("Escolha uma carta para golpear os inimigos") - 1;

        Seguidor carta_escolhida = cartas_mesa.get(numero_carta);

        if(carta_escolhida!=null){
            GerenciadorEfeitos.aumentarAtaqueVida(carta_escolhida, carta_escolhida.getVidaOriginal(), carta_escolhida.getAtaque());
        }
    }

    public static void escolherCartasCombate(Carta carta) {
        ArrayList<Seguidor> cartas_mesa = carta.getMesa().getCartasMesa(carta.getJogador());
        ArrayList<Seguidor> cartas_adversario = carta.getMesa().getCartasMesa(carta.getAdversario());

        int numero_carta = pedirInput("Escolha uma carta sua para combater") - 1;
        int numero_adversario = pedirInput("Escolha uma carta adversaria para combater") - 1;

        Seguidor carta_escolhida = cartas_mesa.get(numero_carta);
        Seguidor carta_adversario = cartas_adversario.get(numero_adversario);

        carta_escolhida.realizarCombate(carta_adversario);

    }
}
