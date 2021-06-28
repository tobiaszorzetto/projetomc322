package br.com.unicamp.projetofinal;

import br.com.unicamp.projetofinal.Cartas.Seguidor;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintFactory {

    public static int pedirInput(String mensagem){
        Scanner scan = new Scanner(System.in);
        System.out.println(mensagem);
        return scan.nextInt();
    }

    public static void printLinhaCartaEsquerda(Seguidor carta1, String cor, String branco){
        System.out.println( cor +
                " ("+ carta1.getAtaque() + "/" + carta1.getVidaAtual()+ ") "+
                carta1.getNome() + branco + "    |    -------------" );
    }

    public static void printLinhaCartaDireita(Seguidor carta2, String cor, String branco){
        System.out.println("-----------    |    " + cor + carta2.getNome() +
                " ("+ carta2.getAtaque() + "/" + carta2.getVidaAtual()+ ")" + branco);
    }

    public static void printLinha2Cartas(Seguidor carta1, Seguidor carta2, String cor1, String cor2, String branco){
        System.out.println( cor1 +
                "("+ carta1.getAtaque() + "/" + carta1.getVidaAtual()+ ") "+ carta1.getNome() + cor2 +
                "    |    " + carta2.getNome() + " ("+ carta2.getAtaque() + "/" + carta2.getVidaAtual()+ ") "+ branco);
    }

    public static void printCartasNaMesa(Mesa mesa, ArrayList<Seguidor> cartas_a_colorir){
        String amarelo = ConsoleColors.YELLOW;
        String branco = ConsoleColors.BLUE;

        Jogador jogador1 = mesa.getJogador(1);
        Jogador jogador2 = mesa.getJogador(2);

        System.out.print(branco);
        System.out.println("========================================");

        System.out.println(jogador1.getNome() + " ("+ jogador1.getVida()+ ") "+ "............."
                            + " ("+ jogador2.getVida()+ ") "+ jogador2.getNome());
        System.out.println();

        for (int i = 0; i< 6; i++){
            Seguidor carta1 = mesa.getCartasMesa(jogador1).get(i);
            Seguidor carta2 = mesa.getCartasMesa(jogador2).get(i);

            if(carta1 == null && carta2==null){
                System.out.println("-------------    |    -------------");
            }
            else if (carta1 != null && carta2!=null){
                if (cartas_a_colorir.contains(carta1)){
                    PrintFactory.printLinha2Cartas(carta1, carta2, amarelo, branco, branco);
                }
                else if (cartas_a_colorir.contains(carta2)){
                    PrintFactory.printLinha2Cartas(carta1, carta2, branco, amarelo, branco);
                }
                else PrintFactory.printLinha2Cartas(carta1, carta2, branco, branco, branco);
            }
            else if(carta1 == null){

                if (cartas_a_colorir.contains(carta2)){
                    PrintFactory.printLinhaCartaDireita(carta2, amarelo, branco);
                }
                else PrintFactory.printLinhaCartaDireita(carta2, branco, branco);
            }
            else {
                if (cartas_a_colorir.contains(carta1)){
                    PrintFactory.printLinhaCartaEsquerda(carta1, amarelo, branco);
                }
                else PrintFactory.printLinhaCartaEsquerda(carta1, branco, branco);
            }
        }

        System.out.println("========================================");
    }

    public static void printCartasNaMesa(Mesa mesa, int mana){
        ArrayList<Seguidor> lista = new ArrayList<Seguidor>();
        for (Seguidor carta: mesa.getCartasMesa(mesa.getAtacante())){
            if(carta !=null && carta.getVaiAtacar()) lista.add(carta);
        }
        System.out.println("========================================");
        System.out.println("MANA: " + mana + "           MANA DE FEITICO: " + mesa.getAtacante().getManaDeFeitico());
        PrintFactory.printCartasNaMesa(mesa, lista);

    }

    public static void printCartasNaMao(Jogador jogador){
        int i = 0;
        for (Carta carta : jogador.getDeck().getDeck()){
            i++;
            System.out.printf("| %d - %s (%d) |", i, carta.getNome(), carta.getMana());
        }
        System.out.println();
    }

}
