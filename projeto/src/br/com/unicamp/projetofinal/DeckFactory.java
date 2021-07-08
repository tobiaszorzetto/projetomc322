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
                        deck.adicionarCarta(new AurelionSol(mesa, jogador));
                        break;
                    case 2:
                        deck.adicionarCarta(new Azir(mesa, jogador));
                        break;
                    case 3:
                        deck.adicionarCarta(new Braum(mesa, jogador));
                        break;
                    case 4:
                        deck.adicionarCarta(new Darius(mesa, jogador));
                        break;
                    case 5:
                        deck.adicionarCarta(new Gangplank(mesa, jogador));
                        break;
                    case 6:
                        deck.adicionarCarta(new Garen(mesa, jogador));
                        break;
                    case 7:
                        deck.adicionarCarta(new Lux(mesa, jogador));
                        break;
                    case 8:
                        deck.adicionarCarta(new Tryndamere(mesa, jogador));
                        break;
                    case 9:
                        deck.adicionarCarta(new Swain(mesa, jogador));
                        break;
                    case 10:
                        deck.adicionarCarta(new Zed(mesa, jogador));
                        break;
                    case 11:
                        deck.adicionarCarta(new AlunoEmTreinamento(mesa, jogador));
                        break;
                    case 12:
                        deck.adicionarCarta(new AranhaDomestica(mesa, jogador));
                        break;
                    case 13:
                        deck.adicionarCarta(new ArtificeDasAreias(mesa, jogador));
                        break;
                    case 14:
                        deck.adicionarCarta(new Atirador(mesa, jogador));
                        break;
                    case 15:
                        deck.adicionarCarta(new Atakhan(mesa, jogador));
                        break;
                    case 16:
                        deck.adicionarCarta(new AcougueiroVoraz(mesa, jogador));
                        break;
                    case 17:
                        deck.adicionarCarta(new BalistaDeFerro(mesa,jogador));
                        break;
                    case 18:
                        deck.adicionarCarta(new CapitaoFarron(mesa,jogador));
                        break;
                    case 19:
                        deck.adicionarCarta(new CorsariaAtiradora(mesa, jogador));
                        break;
                    case 20:
                        deck.adicionarCarta(new CriaAracnidea(mesa, jogador));
                        break;
                    case 21:
                        deck.adicionarCarta(new Curandeira(mesa, jogador));
                        break;
                    case 22:
                        deck.adicionarCarta(new DemolidorImperial(mesa, jogador));
                        break;
                    case 23:
                        deck.adicionarCarta(new Duelista(mesa, jogador));
                        break;
                    case 24:
                        deck.adicionarCarta(new EstatuaDeMacaco(mesa, jogador));
                        break;
                    case 25:
                        deck.adicionarCarta(new GarrerozAlfa(mesa, jogador));
                        break;
                    case 26:
                        deck.adicionarCarta(new Gnomo(mesa, jogador));
                        break;
                    case 27:
                        deck.adicionarCarta(new GuardaTerrestreAvarosiano(mesa, jogador));
                        break;
                    case 28:
                        deck.adicionarCarta(new MiquinhoExplosivo(mesa, jogador));
                        break;
                    case 29:
                        deck.adicionarCarta(new OLeviata(mesa, jogador));
                        break;
                    case 30:
                        deck.adicionarCarta(new Poro(mesa, jogador));
                        break;
                    case 31:
                        deck.adicionarCarta(new PoroDefensor(mesa, jogador));
                        break;
                    case 32:
                        deck.adicionarCarta(new PoroPoderoso(mesa, jogador));
                        break;
                    case 33:
                        deck.adicionarCarta(new SoldadoDeAreia(mesa, jogador));
                        break;
                    case 34:
                        deck.adicionarCarta(new SombraViva(mesa, jogador));
                        break;
                    case 35:
                        deck.adicionarCarta(new TaverneiroGentil(mesa, jogador));
                        break;
                    case 36:
                        deck.adicionarCarta(new Thor(mesa, jogador));
                    case 37:
                        deck.adicionarCarta(new Tiana(mesa,jogador));
                        break;
                    case 38:
                        deck.adicionarCarta(new Vanguarda(mesa,jogador));
                        break;
                    case 39:
                        deck.adicionarCarta(new VigilanteDasDunas(mesa,jogador));
                        break;
                    case 40:
                        deck.adicionarCarta(new DragaoFurioso(mesa,jogador));
                        break;
                    case 41:
                        deck.adicionarCarta(new ARuina(mesa,jogador));
                        break;
                    case 42:
                        deck.adicionarCarta(new Avalanche(mesa,jogador));
                        break;
                    case 43:
                        deck.adicionarCarta(new BanqueteCruel(mesa,jogador));
                        break;
                    case 44:
                        deck.adicionarCarta(new CentellhaFinal(mesa,jogador));
                        break;
                    case 45:
                        deck.adicionarCarta(new CombateUmAUm(mesa,jogador));
                        break;
                    case 46:
                        deck.adicionarCarta(new Cura(mesa,jogador));
                        break;
                    case 47:
                        deck.adicionarCarta(new DiaDoProgresso(mesa,jogador));
                        break;
                    case 48:
                        deck.adicionarCarta(new DisparoMistico(mesa,jogador));
                        break;
                    case 49:
                        deck.adicionarCarta(new Dizimar(mesa,jogador));
                        break;
                    case 50:
                        deck.adicionarCarta(new GolpeCerteiro(mesa,jogador));
                        break;
                    case 51:
                        deck.adicionarCarta(new GolpeExpurgante(mesa,jogador));
                        break;
                    case 52:
                        deck.adicionarCarta(new Julgamento(mesa,jogador));
                        break;
                    case 53:
                        deck.adicionarCarta(new LamentoFulminante(mesa,jogador));
                        break;
                    case 54:
                        deck.adicionarCarta(new LotusDaMorte(mesa,jogador));
                        break;
                        case 55:
                        deck.adicionarCarta(new MaoDaMorte(mesa,jogador));
                        break;
                    case 56:
                        deck.adicionarCarta(new RaioTermogenico(mesa,jogador));
                        break;
                    case 57:
                        deck.adicionarCarta(new ValorRedobrado(mesa,jogador));
                        break;
                    case 58:
                        deck.adicionarCarta(new Vinganca(mesa,jogador));
                        break;
                    case 59:
                        deck.adicionarCarta(new VislumbreDoAlem(mesa,jogador));
                        break;
                    case 60:
                        deck.adicionarCarta(new VoltaAoLar(mesa,jogador));
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
