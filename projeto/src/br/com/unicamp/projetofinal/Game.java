package br.com.unicamp.projetofinal;

import javax.swing.*;

public class Game {

    public void start() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

        Mesa mesa = new Mesa();

        int escolha = PrintFactory.pedirInput("1.1x1 | 2.1xPC");

        System.out.println(ConsoleColors.BLUE + "Digite o Nome do Jogador 1");
        Jogador jogador1 = new Jogador(mesa);
        Jogador jogador2;
        if (escolha == 1){
            System.out.println("Digite o Nome do Jogador 2");
            jogador2 = new Jogador(mesa);
        } else{
            jogador2 = new Computador(mesa);
        }

        mesa.destribuirCartasIniciais(jogador1);
        mesa.destribuirCartasIniciais(jogador2);

        boolean roda = true;
        while(roda){
            roda = mesa.passarRodada();
        }

        PrintFactory.printarFinal(mesa);

    }

}
