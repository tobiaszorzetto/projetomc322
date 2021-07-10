package br.com.unicamp.projetofinal;

import javax.swing.*;

public class Game {


    public void start() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

        Mesa mesa = new Mesa();
        Janela janela = new Janela("Legends Of Runetera", mesa);
        janela.setVisible(true);
        mesa.setJanela(janela);

        if(janela!=null){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            int escolha = janela.pedirInput("1.1x1 | 2.1xPC");


            Jogador jogador1 = new Jogador(mesa, janela);
            Jogador jogador2;

            if (escolha == 1){
                jogador2 = new Jogador(mesa, janela);
            } else{
                jogador2 = new Computador(mesa,janela);
            }

            mesa.destribuirCartasIniciais(jogador1);
            mesa.destribuirCartasIniciais(jogador2);

            boolean roda = true;
            while(roda){
                roda = mesa.passarRodada();
            }


        }


    }

}
