package br.com.unicamp.projetofinal;

import br.com.unicamp.projetofinal.Cartas.Campeoes.*;
import br.com.unicamp.projetofinal.Cartas.Feiticos.*;
import br.com.unicamp.projetofinal.Cartas.Seguidores.*;
import br.com.unicamp.projetofinal.Enums.TipoDeck;

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
            deck.adicionarCarta(new GolpeCerteiro(mesa, jogador));
        }
        return deck;
    }

    public static Deck fazerDeckPersonalizado(Deck deck, Mesa mesa, Jogador jogador){

        System.out.println("Escolha das seguintes no máximo 40 cartas para montar Deck: ");
        System.out.println(
                "CAMPEOES\n" +
                "1. Aurelion Sol\n" +
                "2. Azir\n" +
                "3. Braum\n" +
                "4. Darius\n" +
                "5. Gangplank\n" +
                "6. Garen\n" +
                "7. Lux\n" +
                "8. Tryndamere\n" +
                "9. Swain\n" +
                "10. Zed\n" +
                "\n" +
                "\n" +
                "\n" +
                "SEGUIDORES\n" +
                "11. Aluno Em Treinamento   \n" +
                "12. Aranha Doméstica   \n" +
                "13. Artífice Das Areias\n" +
                "14. Atirador\n" +
                "15. Atakhan\n" +
                "16. Açougueiro Voraz\n" +
                "17. Balista De Ferro\n" +
                "18. Capitão Farron\n" +
                "19. Corsária Atiradora\n" +
                "20. Cria Arachnida\n" +
                "21. Curandeira\n" +
                "22. Demolidor Imperial\n" +
                "23. Duelista\n" +
                "24. Estátua De Macaco\n" +
                "25. Garreroz Alfa\n" +
                "26. Gnomo\n" +
                "27. Guarda Terrestre Avarosiano\n" +
                "28. Miquinho Explosivo\n" +
                "29. Leviatã\n" +
                "30. Poro\n" +
                "31. Poro Defensor\n" +
                "32. Poro Poderoso\n" +
                "33. Soldado De Areia\n" +
                "34. Sombra Viva\n" +
                "35. Taverneiro Gentil\n" +
                "36. Thor\n" +
                "37. Tiana\n" +
                "38. Vanguarda\n" +
                "39. Vigilante Das Dunas\n" +
                "40. Dragao Furioso\n" +
                "\n" +
                "\n" +
                "\n" +
                "FEITICOS\n" +
                "41. A Ruina\n" +
                "42. Avalanche\n" +
                "43. Banquete Cruel\n" +
                "44. Centelha Final\n" +
                "45. Combate Um A Um\n" +
                "46. Cura\n" +
                "47. Dia do Progresso\n" +
                "48. Disparo Místico\n" +
                "49. Dizimar\n" +
                "50. Golpe Certeiro\n" +
                "51. Golpe Expurgante\n" +
                "52. Julgamento\n" +
                "53. Lamento Fulminante\n" +
                "54. Lótus da morte\n" +
                "55. Mão Da Morte\n" +
                "56. Raio Termogênico\n" +
                "57. Valor Redobrado\n" +
                "58. Vingança\n" +
                "59. Vislumbre do Além\n" +
                "60. Volta Ao Lar\n");


        boolean running = true;
        while (running && deck.getSize() < 40) {

            int numero_carta = PrintFactory.pedirInput("Escolha uma carta");

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
                    case 11:
                        deck.adicionarCarta(new CorsariaAtiradora(mesa, jogador));
                        break;
                    case 12:
                        deck.adicionarCarta(new PoroPoderoso(mesa, jogador));
                        break;
                    case 13:
                        deck.adicionarCarta(new Lux(mesa, jogador));
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
