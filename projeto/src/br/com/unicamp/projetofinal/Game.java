package br.com.unicamp.projetofinal;

public class Game {

    public void start(){

        Mesa mesa = new Mesa();

        System.out.println(ConsoleColors.BLUE + "Digite o Nome do Jogador 1");
        Jogador jogador1 = new Jogador(mesa);



        System.out.println("Digite o Nome do Jogador 2");
        Jogador jogador2 = new Jogador(mesa);


        mesa.destribuirCartasIniciais(jogador1);
        mesa.destribuirCartasIniciais(jogador2);

        boolean roda = true;
        while(roda){
            roda = mesa.passarRodada();
        }

    }

}
