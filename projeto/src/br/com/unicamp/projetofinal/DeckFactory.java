package br.com.unicamp.projetofinal;

import java.util.Scanner;

public class DeckFactory {

    public static Deck fazerDeck(TipoDeck tipo, Mesa mesa, Jogador jogador){
        Deck deck = new Deck();
        switch (tipo){
            case PADRAO:
                return fazerDeckPadrao(deck, mesa, jogador);
            default:
                return fazerDeckPersonalizado(deck, mesa, jogador);
        }
    }

    public static Deck fazerDeckPadrao(Deck deck, Mesa mesa, Jogador jogador){
        for(int i = 0; i< 4; i++){
            deck.adicionarCarta(new Thor(mesa, jogador));
            deck.adicionarCarta(new Gnomo(mesa, jogador));
            deck.adicionarCarta(new Curandeira(mesa, jogador));
            deck.adicionarCarta(new Garen(mesa, jogador));
            deck.adicionarCarta(new Tiana(mesa, jogador));
            deck.adicionarCarta(new Vanguarda(mesa, jogador));
            deck.adicionarCarta(new Duelista(mesa, jogador));
            deck.adicionarCarta(new Poro(mesa, jogador));
            deck.adicionarCarta(new PoroDefensor(mesa, jogador));
            deck.adicionarCarta(new Cura(mesa, jogador));
        }
        return deck;
    }

    public static Deck fazerDeckPersonalizado(Deck deck, Mesa mesa, Jogador jogador){

        System.out.println("Escolha das seguintes no máximo 40 cartas para montar Deck: ");
        System.out.println("1: Thor            2: Gnomo           3: Curandeira      4: Garen\n" +
                "5: Tiana           6: Vanguarda       7: Duelista        8: Poro\n"  +
                "9: Poro Defensor  10: Cura");


        boolean running = true;
        while (running && deck.getSize() < 40) {

            int numero_carta = GerenciadorEfeitos.pedirInput("Escolha uma carta");

            if (numero_carta == 0) {
                running = false;
                for(Carta carta : deck.getDeck()){
                    System.out.println(carta.getNome());
                }
            }
            else{

                switch (numero_carta){
                    case 1:
                        deck.adicionarCarta(new Thor(mesa, jogador));
                        break;
                    case 2:
                        deck.adicionarCarta(new Gnomo(mesa, jogador));
                        break;
                    case 3:
                        deck.adicionarCarta(new Curandeira(mesa, jogador));
                        break;
                    case 4:
                        deck.adicionarCarta(new Garen(mesa, jogador));
                        break;
                    case 5:
                        deck.adicionarCarta(new Tiana(mesa, jogador));
                        break;
                    case 6:
                        deck.adicionarCarta(new Vanguarda(mesa, jogador));
                        break;
                    case 7:
                        deck.adicionarCarta(new Duelista(mesa, jogador));
                        break;
                    case 8:
                        deck.adicionarCarta(new Poro(mesa, jogador));
                        break;
                    case 9:
                        deck.adicionarCarta(new PoroDefensor(mesa, jogador));
                        break;
                    case 10:
                        deck.adicionarCarta(new Cura(mesa, jogador));
                        break;
                    default:
                        System.out.println("Não existe carta com esse numero");
                        break;
                }
                System.out.println("Numero de cartas escolhidas: "+ deck.getSize());
            }
        }

        return  deck;
    }

}
