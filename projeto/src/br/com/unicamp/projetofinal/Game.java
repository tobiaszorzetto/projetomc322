package br.com.unicamp.projetofinal;

public class Game {

    public void start(){

        Mesa mesa = new Mesa();

        System.out.println("Digite o Nome do Jogador 1");
        Jogador jogador1 = new Jogador(mesa);
        jogador1.escolherDeck();


        System.out.println("Digite o Nome do Jogador 2");
        Jogador jogador2 = new Jogador(mesa);
        jogador2.escolherDeck();

        mesa.destribuirCartasIniciais();

        boolean roda = true;
        while(roda){
            mesa.passarRodada();

        }

    }

}
