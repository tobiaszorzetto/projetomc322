package br.com.unicamp.projetofinal;

import br.com.unicamp.projetofinal.Cartas.Seguidor;

import java.util.ArrayList;
import java.util.Random;


public class GerenciadorEfeitos {

    public GerenciadorEfeitos(){

    }

    public static void aumentarVida(Seguidor carta, int vida){
        carta.aumentarVida(vida);
    }

    public static void aumentarAtaque(Seguidor carta, int ataque){
        carta.aumentarAtaque(ataque);
    }

    public static void aumentarAtaqueVida(Seguidor carta, int ataque, int vida){
        GerenciadorEfeitos.aumentarAtaque(carta, ataque);
        GerenciadorEfeitos.aumentarVida(carta, vida);
    }

    public static void curar(Seguidor carta){
        carta.setVidaOriginal();
    }

    public static void curar(Seguidor carta, int quant){
        GerenciadorEfeitos.aumentarVida(carta, quant);
    }

    public static void escolherCartaCurar(Carta carta){
        int numero_carta;
        ArrayList<Seguidor> cartas_na_mesa = carta.getMesa().getCartasMesa(carta.getJogador());
        boolean setar_alguem = false;
        for (Seguidor seguidor: cartas_na_mesa){
            if (seguidor!= null){
                setar_alguem = true;
                break;
            }
        }
        if (setar_alguem){
            numero_carta = PrintFactory.pedirInputInt(carta.getJogador().getNome() + ", quais dessas cartas deseja curar?") - 1;
            try{
                GerenciadorEfeitos.curar(cartas_na_mesa.get(numero_carta));
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Escolha um indice valido!");
                GerenciadorEfeitos.escolherCartaCurar(carta);
            }
        }
    }

    public static void atacarNexus(Jogador jogador, int dano){
        jogador.diminuirVida(dano);
    }

    public static void curarNexus(Jogador jogador, int cura){
        jogador.aumentarVida(cura);
    }

    public static void cartaAleatoriaAtacarNexus(Carta carta, ArrayList<Seguidor> cartas_na_mesa){
        Random sorteio = new Random();
        Mesa mesa = carta.getMesa();
        Jogador jogador = carta.getJogador();

        if (mesa.temCartasMesa(jogador)){
            while(true) {
                Seguidor carta_sorteada = cartas_na_mesa.get(sorteio.nextInt(6));
                if (carta_sorteada!=null && carta_sorteada != carta) {// vai dar algo errado?
                    carta_sorteada.atacarNexus(carta.getAdversario(),carta_sorteada.getAtaque());
                    break;
                }
            }
        }
    }

    public static void colocarCartaNaMao(Seguidor carta, Carta carta_vai_pra_mao){
        Jogador jogador= carta.getJogador();
        jogador.colocarCartaNaMao(carta_vai_pra_mao);
    }

    public static void escolherCartaParaDarDano(Carta carta, Jogador jogador_atingido, int dano) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
        if (jogador_atingido.getMesa().temCartasMesa(jogador_atingido)){
            try{
                Seguidor carta_escolhida = escolherSeguidor(jogador_atingido, jogador_atingido.getMesa(), "Escolha uma carta para dar "+ dano +" dano");
                boolean morreu = carta_escolhida.diminuirVida(dano);
                if(morreu) {
                    carta.verificarSobrepujar(carta_escolhida);
                }
            } catch (IndexOutOfBoundsException e){
                System.out.println("Posicao de ataque invalida.");
                escolherCoisaParaDarDano(carta, dano);
            } catch (NullPointerException e){
                System.out.println("Não há carta nessa posicao");
                escolherCoisaParaDarDano(carta, dano);
            }
        }
    }

    public static void escolherCartaBaterEmTodos(Carta carta) throws PosicaoMesaOcupadaException, ManaInsuficienteException {

        Seguidor carta_escolhida = escolherSeguidor(carta.getJogador(), carta.getMesa(), "Escolha uma carta para golpear os inimigos");
        ArrayList<Seguidor> cartas_adversario = carta.getMesa().getCartasMesa(carta.getAdversario());

        for(int i = 0; i<6; i++){
            if (cartas_adversario.get(i)!=null)
                cartas_adversario.get(i).diminuirVida(carta_escolhida.getAtaque());
        }
    }

    public static void escolherCartaDobrarValores(Carta carta) {
        Seguidor carta_escolhida = escolherSeguidor(carta.getJogador(), carta.getMesa(), "Escolha uma carta para golpear os inimigos");

        if(carta_escolhida!=null){
            GerenciadorEfeitos.aumentarAtaqueVida(carta_escolhida, carta_escolhida.getVidaOriginal(), carta_escolhida.getAtaque());
        }
    }

    public static void escolherCartasCombate(Carta carta) throws ManaInsuficienteException, PosicaoMesaOcupadaException {

        Seguidor carta_escolhida = escolherSeguidor(carta.getJogador(), carta.getMesa(), "Escolha uma carta sua para combater");
        Seguidor carta_adversario = escolherSeguidor(carta.getAdversario(), carta.getMesa(), "Escolha uma carta adversaria para combater");
        carta_escolhida.realizarCombate(carta_adversario);
    }

    public static void evocarSeguidorAtacante(Seguidor carta) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        ArrayList<Seguidor> cartas_mesa = carta.getMesa().getCartasMesa(carta.getJogador());
        boolean evocou = false;
        int posicao_alocao = 0;
        for (int i = 0; i < 6; i ++){
            if (cartas_mesa.get(i) == null) {
                carta.atuarNaMesa(carta.getJogador(), i+1);
                evocou = true;
                break;
            }
        }
        if (evocou){
            carta.setVaiAtacar(true);
            PrintFactory.printCartasNaMesa(carta.getMesa());
        }
    }

    public static void AtacarTodasAsCartas(Mesa mesa, int dano) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        ArrayList<Seguidor> cartas_mesa1 = mesa.getCartas_mesa1();
        ArrayList<Seguidor> cartas_mesa2 = mesa.getCartas_mesa2();

        for (Seguidor seguidor : cartas_mesa1){
            if (seguidor != null){
                seguidor.diminuirVida(dano);
            }
        }
        for(Seguidor seguidor : cartas_mesa2){
            if (seguidor != null){
                seguidor.diminuirVida(dano);
            }
        }
    }

    public static void AtacarTodasAsCartasInimigo(Carta carta, int dano) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        ArrayList<Seguidor> cartas_mesa_inimigo = carta.getMesa().getCartasMesaAdversario(carta.getJogador());

        for (Seguidor seguidor : cartas_mesa_inimigo){
            if (seguidor != null){
                seguidor.diminuirVida(dano);
            }
        }
    }

    public static void evocarSeguidor(Seguidor carta) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
        ArrayList<Seguidor> cartas_mesa = carta.getMesa().getCartasMesa(carta.getJogador());
        int posicao_alocao = 0;
        for (Seguidor seguidor : cartas_mesa){
            posicao_alocao += 1;
            if (seguidor == null){
                carta.atuarNaMesa(carta.getJogador(), posicao_alocao);
                break;
            }
        }
    }

    public static void escolherCoisaParaDarDano(Carta carta_que_chamou, int dano) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        int escolha = 0;
        while( escolha < 1 || escolha >2 )
            escolha = PrintFactory.pedirInputInt("1:Dano em uma carta na mesa || 2:Dano ao nexus inimigo");
        if (escolha == 1){
            GerenciadorEfeitos.escolherCartaParaDarDano(carta_que_chamou,carta_que_chamou.getAdversario(), dano);
        } else{
            GerenciadorEfeitos.atacarNexus(carta_que_chamou.getAdversario(), dano);
        }
    }

    public static void darDanoEmAliadoParaAtacarNexus(Carta carta_que_chamou, int dano_mesa, int dano_nexus) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.escolherCartaParaDarDano(carta_que_chamou, carta_que_chamou.getJogador(), dano_mesa);
        GerenciadorEfeitos.atacarNexus(carta_que_chamou.getAdversario(), dano_nexus);
    }

    public static boolean abaterAliado(Jogador jogador_atingido) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        if (jogador_atingido.getMesa().temCartasMesa(jogador_atingido)){
            Seguidor carta_escolhida = escolherSeguidor(jogador_atingido, jogador_atingido.getMesa(),"Escolha uma carta para dar abater" );
            carta_escolhida.matarSeguidor();
            return true;
        }
        return false;
    }

    public static void curarAliadoOuNexus(Carta carta_que_chamou, int quant){
        int escolha = 0;
        while( escolha < 1 || escolha >2 )
            escolha = PrintFactory.pedirInputInt("1: Curar aliado || 2: Curar nexus em " + quant);
        if (escolha == 1){
            GerenciadorEfeitos.escolherCartaCurar(carta_que_chamou);
        } else{
            GerenciadorEfeitos.curarNexus(carta_que_chamou.getJogador(), 3);
        }
    }

    public static void matarTodasAsCartasNaMesa(Carta carta) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.matarTodasAsCartasJogador(carta.getJogador());
        GerenciadorEfeitos.matarTodasAsCartasJogador(carta.getAdversario());
    }

    public static void matarTodasAsCartasJogador(Jogador jogador) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        ArrayList<Seguidor> mesa_jogador = jogador.getMesa().getCartasMesa(jogador);
        for(int i = 0; i< 6; i++){
            if (mesa_jogador.get(i) != null){
                mesa_jogador.get(i).matarSeguidor();
            }
        }
    }

    public static boolean retornarCartaMao(Jogador jogador){
        ArrayList<Seguidor> mesa_jogador = jogador.getMesa().getCartasMesa(jogador.getMesa().getAdversario(jogador));
        int num_carta_escolhida = PrintFactory.pedirInputInt("escolha carta de" +jogador.getNome() + " para retornar para a mao") - 1;
        Seguidor carta_escolhida = mesa_jogador.get(num_carta_escolhida);
        if(carta_escolhida !=null){
            carta_escolhida.resetar();
            GerenciadorEfeitos.colocarCartaNaMao(carta_escolhida, carta_escolhida);
            jogador.getMesa().getCartasMesa(jogador).set(num_carta_escolhida, null);
            return true;
        }
        return false;
    }

    public static void matarCartaInimiga(Carta carta_que_chamou) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        Seguidor carta_escolhida = escolherSeguidor(carta_que_chamou.getJogador(), carta_que_chamou.getMesa(),"Que carta quer abater ?");

        if(carta_escolhida !=null){
            carta_escolhida.matarSeguidor();
        }
    }

    public static Seguidor escolherSeguidor(Jogador jogador, Mesa mesa, String mensagem){
        try{
            ArrayList<Seguidor> mesa_jogador = jogador.getMesa().getCartasMesa(mesa.getAdversario(jogador));
            int num_carta_escolhida = PrintFactory.pedirInputInt(mensagem) - 1;
            return mesa_jogador.get(num_carta_escolhida);
        }
        catch (IndexOutOfBoundsException | NullPointerException e){
            PrintFactory.printLinha("Posicao invalida. Tente Novamente.");
            return escolherSeguidor(jogador, mesa, mensagem);
        }
    }
}
