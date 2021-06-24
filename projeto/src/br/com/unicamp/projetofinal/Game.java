package br.com.unicamp.projetofinal;

public class Game {

    public void start(){

        Mesa mesa = new Mesa();
        GerenciadorEfeitos ge = new GerenciadorEfeitos();

        System.out.println("Digite o Nome do Jogador 1");
        Jogador jogador1 = new Jogador(mesa);
        jogador1.escolherDeck(ge);


        System.out.println("Digite o Nome do Jogador 2");
        Jogador jogador2 = new Jogador(mesa);
        jogador2.escolherDeck(ge);

        mesa.destribuirCartasIniciais();

        boolean roda = true;
        while(roda){
            mesa.passarRodada();

        }

    }

}
