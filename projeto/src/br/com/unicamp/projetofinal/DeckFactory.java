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
            case EVOCADOR:
                return fazerDeckEvocador(deck, mesa, jogador);
            case BRAVURA:
                return fazerDeckBravura(deck, mesa, jogador);
            default:
                return fazerDeckPersonalizado(deck, mesa, jogador);
        }
    }

    public static Deck fazerDeckPadrao(Deck deck, Mesa mesa, Jogador jogador){
        for(int i = 0; i < 4; i++){
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

    public static Deck fazerDeckEvocador(Deck deck, Mesa mesa, Jogador jogador){
        for (int i = 0; i < 3; i ++){
            deck.adicionarCarta(new Azir(mesa, jogador));
            deck.adicionarCarta(new Zed(mesa, jogador));
            deck.adicionarCarta(new VigilanteDasDunas(mesa, jogador));
            deck.adicionarCarta(new EstatuaDeMacaco(mesa, jogador));
            deck.adicionarCarta(new AranhaDomestica(mesa, jogador));
            deck.adicionarCarta(new VislumbreDoAlem(mesa, jogador));
            deck.adicionarCarta(new RaioTermogenico(mesa, jogador));
        }
        deck.adicionarCarta(new SombraViva(mesa, jogador));
        deck.adicionarCarta(new SoldadoDeAreia(mesa, jogador));
        deck.adicionarCarta(new RaioTermogenico(mesa, jogador));
        deck.adicionarCarta(new Atakhan(mesa, jogador));
        deck.adicionarCarta(new MiquinhoExplosivo(mesa, jogador));
        deck.adicionarCarta(new OLeviata(mesa, jogador));
        deck.adicionarCarta(new PoroDefensor(mesa, jogador));
        deck.adicionarCarta(new AlunoEmTreinamento(mesa, jogador));
        deck.adicionarCarta(new LamentoFulminante(mesa, jogador));
        deck.adicionarCarta(new Curandeira(mesa, jogador));
        deck.adicionarCarta(new Vinganca(mesa, jogador));
        deck.adicionarCarta(new CapitaoFarron(mesa, jogador));
        deck.adicionarCarta(new CombateUmAUm(mesa, jogador));
        deck.adicionarCarta(new Avalanche(mesa, jogador));
        deck.adicionarCarta(new VoltaAoLar(mesa, jogador));
        deck.adicionarCarta(new GolpeExpurgante(mesa, jogador));
        deck.adicionarCarta(new GarrerozAlfa(mesa, jogador));
        deck.adicionarCarta(new Gnomo(mesa, jogador));
        deck.adicionarCarta(new PoroPoderoso(mesa, jogador));

        return deck;
    }

    public static Deck fazerDeckBravura(Deck deck, Mesa mesa, Jogador jogador){

        deck.adicionarCarta(new Tiana(mesa, jogador));
        deck.adicionarCarta(new Tiana(mesa, jogador));
        deck.adicionarCarta(new AlunoEmTreinamento(mesa, jogador));
        deck.adicionarCarta(new AlunoEmTreinamento(mesa, jogador));
        deck.adicionarCarta(new Swain(mesa, jogador));
        deck.adicionarCarta(new Lux(mesa, jogador));
        deck.adicionarCarta(new Lux(mesa, jogador));
        deck.adicionarCarta(new Zed(mesa, jogador));
        deck.adicionarCarta(new Cura(mesa, jogador));
        deck.adicionarCarta(new Cura(mesa, jogador));
        deck.adicionarCarta(new Curandeira(mesa, jogador));
        deck.adicionarCarta(new MaoDaMorte(mesa, jogador));
        deck.adicionarCarta(new LamentoFulminante(mesa, jogador));
        deck.adicionarCarta(new LamentoFulminante(mesa, jogador));
        deck.adicionarCarta(new RaioTermogenico(mesa, jogador));
        deck.adicionarCarta(new RaioTermogenico(mesa, jogador));
        deck.adicionarCarta(new Atirador(mesa, jogador));
        deck.adicionarCarta(new CorsariaAtiradora(mesa, jogador));
        deck.adicionarCarta(new CorsariaAtiradora(mesa, jogador));
        deck.adicionarCarta(new MiquinhoExplosivo(mesa, jogador));
        deck.adicionarCarta(new EstatuaDeMacaco(mesa, jogador));
        deck.adicionarCarta(new EstatuaDeMacaco(mesa, jogador));
        deck.adicionarCarta(new OLeviata(mesa, jogador));
        deck.adicionarCarta(new OLeviata(mesa, jogador));
        deck.adicionarCarta(new TaverneiroGentil(mesa, jogador));
        deck.adicionarCarta(new DragaoFurioso(mesa, jogador));
        deck.adicionarCarta(new BalistaDeFerro(mesa, jogador));
        deck.adicionarCarta(new BalistaDeFerro(mesa, jogador));
        deck.adicionarCarta(new CapitaoFarron(mesa, jogador));
        deck.adicionarCarta(new GuardaTerrestreAvarosiano(mesa, jogador));
        deck.adicionarCarta(new PoroDefensor(mesa, jogador));
        deck.adicionarCarta(new PoroPoderoso(mesa, jogador));
        deck.adicionarCarta(new PoroPoderoso(mesa, jogador));
        deck.adicionarCarta(new Garen(mesa, jogador));
        deck.adicionarCarta(new Tryndamere(mesa, jogador));
        deck.adicionarCarta(new Tryndamere(mesa, jogador));
        deck.adicionarCarta(new Vinganca(mesa, jogador));
        deck.adicionarCarta(new DemolidorImperial(mesa, jogador));
        deck.adicionarCarta(new DemolidorImperial(mesa, jogador));
        deck.adicionarCarta(new DemolidorImperial(mesa, jogador));
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
                "5. Ezreal\n" +
                "6. Gangplank\n" +
                "7. Garen\n" +
                "8. Lux\n" +
                "9. Tryndamere\n" +
                "10. Swain\n" +
                "11. Zed\n" +
                "\n" +
                "\n" +
                "\n" +
                "SEGUIDORES\n" +
                "12. Aluno Em Treinamento   \n" +
                "13. Aranha Doméstica   \n" +
                "14. Artífice Das Areias\n" +
                "15. Atirador\n" +
                "16. Atakhan\n" +
                "17. Açougueiro Voraz\n" +
                "18. Balista De Ferro\n" +
                "19. Capitão Farron\n" +
                "20. Corsária Atiradora\n" +
                "21. Cria Arachnida\n" +
                "22. Curandeira\n" +
                "23. Demolidor Imperial\n" +
                "24. Duelista\n" +
                "25. Estátua De Macaco\n" +
                "26. Garreroz Alfa\n" +
                "27. Gnomo\n" +
                "28. Guarda Terrestre Avarosiano\n" +
                "29. Miquinho Explosivo\n" +
                "30. Leviatã\n" +
                "31. Poro\n" +
                "32. Poro Defensor\n" +
                "33. Poro Poderoso\n" +
                "34. Soldado De Areia\n" +
                "35. Sombra Viva\n" +
                "36. Taverneiro Gentil\n" +
                "37. Thor\n" +
                "38. Tiana\n" +
                "39. Vanguarda\n" +
                "40. Vigilante Das Dunas\n" +
                "41. Dragao Furioso\n" +
                "\n" +
                "\n" +
                "\n" +
                "FEITICOS\n" +
                "42. A Ruina\n" +
                "43. Avalanche\n" +
                "44. Banquete Cruel\n" +
                "45. Centelha Final\n" +
                "46. Combate Um A Um\n" +
                "47. Cura\n" +
                "48. Dia do Progresso\n" +
                "49. Disparo Místico\n" +
                "50. Dizimar\n" +
                "51. Golpe Certeiro\n" +
                "52. Golpe Expurgante\n" +
                "53. Julgamento\n" +
                "54. Lamento Fulminante\n" +
                "55. Lótus da morte\n" +
                "56. Mão Da Morte\n" +
                "57. Raio Termogênico\n" +
                "58. Valor Redobrado\n" +
                "59. Vingança\n" +
                "60. Vislumbre do Além\n" +
                "61. Volta Ao Lar\n");

        boolean running = true;
        while (running && deck.getSize() < 40) {
            int numero_carta = PrintFactory.pedirInputInt("Escolha uma carta");

            if (numero_carta == 0) {
                if (deck.getSize() > 4){
                    running = false;
                    for(Carta carta : deck.getDeck()){
                        System.out.println(carta.getNome());
                    }
                }
                else{
                    System.out.println("São necessárias ao menos 4 cartas no Deck para jogar");
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
                        deck.adicionarCarta(new Ezreal(mesa, jogador));
                        break;
                    case 6:
                        deck.adicionarCarta(new Gangplank(mesa, jogador));
                        break;
                    case 7:
                        deck.adicionarCarta(new Garen(mesa, jogador));
                        break;
                    case 8:
                        deck.adicionarCarta(new Lux(mesa, jogador));
                        break;
                    case 9:
                        deck.adicionarCarta(new Tryndamere(mesa, jogador));
                        break;
                    case 10:
                        deck.adicionarCarta(new Swain(mesa, jogador));
                        break;
                    case 11:
                        deck.adicionarCarta(new Zed(mesa, jogador));
                        break;
                    case 12:
                        deck.adicionarCarta(new AlunoEmTreinamento(mesa, jogador));
                        break;
                    case 13:
                        deck.adicionarCarta(new AranhaDomestica(mesa, jogador));
                        break;
                    case 14:
                        deck.adicionarCarta(new ArtificeDasAreias(mesa, jogador));
                        break;
                    case 15:
                        deck.adicionarCarta(new Atirador(mesa, jogador));
                        break;
                    case 16:
                        deck.adicionarCarta(new Atakhan(mesa, jogador));
                        break;
                    case 17:
                        deck.adicionarCarta(new AcougueiroVoraz(mesa, jogador));
                        break;
                    case 18:
                        deck.adicionarCarta(new BalistaDeFerro(mesa,jogador));
                        break;
                    case 19:
                        deck.adicionarCarta(new CapitaoFarron(mesa,jogador));
                        break;
                    case 20:
                        deck.adicionarCarta(new CorsariaAtiradora(mesa, jogador));
                        break;
                    case 21:
                        deck.adicionarCarta(new CriaAracnidea(mesa, jogador));
                        break;
                    case 22:
                        deck.adicionarCarta(new Curandeira(mesa, jogador));
                        break;
                    case 23:
                        deck.adicionarCarta(new DemolidorImperial(mesa, jogador));
                        break;
                    case 24:
                        deck.adicionarCarta(new Duelista(mesa, jogador));
                        break;
                    case 25:
                        deck.adicionarCarta(new EstatuaDeMacaco(mesa, jogador));
                        break;
                    case 26:
                        deck.adicionarCarta(new GarrerozAlfa(mesa, jogador));
                        break;
                    case 27:
                        deck.adicionarCarta(new Gnomo(mesa, jogador));
                        break;
                    case 28:
                        deck.adicionarCarta(new GuardaTerrestreAvarosiano(mesa, jogador));
                        break;
                    case 29:
                        deck.adicionarCarta(new MiquinhoExplosivo(mesa, jogador));
                        break;
                    case 30:
                        deck.adicionarCarta(new OLeviata(mesa, jogador));
                        break;
                    case 31:
                        deck.adicionarCarta(new Poro(mesa, jogador));
                        break;
                    case 32:
                        deck.adicionarCarta(new PoroDefensor(mesa, jogador));
                        break;
                    case 33:
                        deck.adicionarCarta(new PoroPoderoso(mesa, jogador));
                        break;
                    case 34:
                        deck.adicionarCarta(new SoldadoDeAreia(mesa, jogador));
                        break;
                    case 35:
                        deck.adicionarCarta(new SombraViva(mesa, jogador));
                        break;
                    case 36:
                        deck.adicionarCarta(new TaverneiroGentil(mesa, jogador));
                        break;
                    case 37:
                        deck.adicionarCarta(new Thor(mesa, jogador));
                        break;
                    case 38:
                        deck.adicionarCarta(new Tiana(mesa,jogador));
                        break;
                    case 39:
                        deck.adicionarCarta(new Vanguarda(mesa,jogador));
                        break;
                    case 40:
                        deck.adicionarCarta(new VigilanteDasDunas(mesa,jogador));
                        break;
                    case 41:
                        deck.adicionarCarta(new DragaoFurioso(mesa,jogador));
                        break;
                    case 42:
                        deck.adicionarCarta(new ARuina(mesa,jogador));
                        break;
                    case 43:
                        deck.adicionarCarta(new Avalanche(mesa,jogador));
                        break;
                    case 44:
                        deck.adicionarCarta(new BanqueteCruel(mesa,jogador));
                        break;
                    case 45:
                        deck.adicionarCarta(new CentellhaFinal(mesa,jogador));
                        break;
                    case 46:
                        deck.adicionarCarta(new CombateUmAUm(mesa,jogador));
                        break;
                    case 47:
                        deck.adicionarCarta(new Cura(mesa,jogador));
                        break;
                    case 48:
                        deck.adicionarCarta(new DiaDoProgresso(mesa,jogador));
                        break;
                    case 49:
                        deck.adicionarCarta(new DisparoMistico(mesa,jogador));
                        break;
                    case 50:
                        deck.adicionarCarta(new Dizimar(mesa,jogador));
                        break;
                    case 51:
                        deck.adicionarCarta(new GolpeCerteiro(mesa,jogador));
                        break;
                    case 52:
                        deck.adicionarCarta(new GolpeExpurgante(mesa,jogador));
                        break;
                    case 53:
                        deck.adicionarCarta(new Julgamento(mesa,jogador));
                        break;
                    case 54:
                        deck.adicionarCarta(new LamentoFulminante(mesa,jogador));
                        break;
                    case 55:
                        deck.adicionarCarta(new LotusDaMorte(mesa,jogador));
                        break;
                    case 56:
                        deck.adicionarCarta(new MaoDaMorte(mesa,jogador));
                        break;
                    case 57:
                        deck.adicionarCarta(new RaioTermogenico(mesa,jogador));
                        break;
                    case 58:
                        deck.adicionarCarta(new ValorRedobrado(mesa,jogador));
                        break;
                    case 59:
                        deck.adicionarCarta(new Vinganca(mesa,jogador));
                        break;
                    case 60:
                        deck.adicionarCarta(new VislumbreDoAlem(mesa,jogador));
                        break;
                    case 61:
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
