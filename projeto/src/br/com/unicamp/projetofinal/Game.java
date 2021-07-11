package br.com.unicamp.projetofinal;

public class Game {

    public void start() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

        Mesa mesa = new Mesa();

        System.out.println(ConsoleColors.BLUE + "Digite o Nome do Jogador 1");
        Jogador jogador1 = new Jogador(mesa);
        Jogador jogador2 = escolherContraQuem(mesa);

        mesa.destribuirCartasIniciais(jogador1);
        mesa.destribuirCartasIniciais(jogador2);

        boolean roda = true;
        while(roda){
            roda = mesa.passarRodada();
        }

        PrintFactory.printarFinal(mesa);
    }

    public Jogador escolherContraQuem(Mesa mesa){
        int escolha = PrintFactory.pedirInputInt("1.1x1 | 2.1xPC");
        if (escolha == 1){
            System.out.println("Digite o Nome do Jogador 2");
            return new Jogador(mesa);
        } else if(escolha == 2){
            return new Computador(mesa);
        }
        else
            return escolherContraQuem(mesa);
    }

}
