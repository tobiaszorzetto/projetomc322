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

    public static void escolherCartaCurar(Carta carta){

        if (carta.getMesa().temCartasMesa(carta.getJogador())){ // primeiro checa se existem alguem p curar

            Seguidor seguidor = escolherSeguidor(carta.getJogador(),carta.getMesa(), carta.getJogador().getNome() + ", quais dessas cartas deseja curar?");
            GerenciadorEfeitos.curar(seguidor);

        }
    }

    public static void atacarNexus(Jogador jogador, int dano){
        jogador.diminuirVida(dano);
    }

    public static void curarNexus(Jogador jogador, int cura){
        jogador.aumentarVida(cura);
    }

    public static void cartaAleatoriaAtacarNexus(Carta carta_que_chamou, ArrayList<Seguidor> cartas_na_mesa){
        Random sorteio = new Random();
        Mesa mesa = carta_que_chamou.getMesa();
        Jogador jogador = carta_que_chamou.getJogador();

        if (mesa.temCartasMesa(jogador)){
            while(true) { // tenta ate achar uma carta, ja que o aleatorio pode pegar posicoes nulas
                Seguidor carta_sorteada = cartas_na_mesa.get(sorteio.nextInt(6));
                if (carta_sorteada!=null ) {
                    carta_sorteada.atacarNexus(carta_que_chamou.getAdversario(),carta_sorteada.getAtaque());
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
            Seguidor carta_escolhida = escolherSeguidor(jogador_atingido, jogador_atingido.getMesa(), "Escolha uma carta para dar "+ dano +" dano");
            boolean morreu = carta_escolhida.diminuirVida(dano);
            if(morreu) {
                carta.verificarSobrepujar(carta_escolhida);
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
        // evocar unidade especifica e ja setar ele para atacar // so serve para quando jogador que jogar for atacante
        ArrayList<Seguidor> cartas_mesa = carta.getMesa().getCartasMesa(carta.getJogador());
        boolean evocou = false;

        for (int i = 0; i < 6; i ++){
            if (cartas_mesa.get(i) == null) { // se passar achou lugar vazio
                carta.atuarNaMesa(carta.getJogador(), i+1);
                evocou = true;
                break;
            }
        }
        if (evocou){ // existe a chance de n evocar a carta, entao mesmo que ela tenha sido criada ela so eh deixada de lado
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
        // evoca uma determinada unidade na primeira posicao disponivel
        ArrayList<Seguidor> cartas_mesa = carta.getMesa().getCartasMesa(carta.getJogador());
        int posicao_alocao = 0;
        for (Seguidor seguidor : cartas_mesa){
            posicao_alocao += 1;
            if (seguidor == null){
                carta.atuarNaMesa(carta.getJogador(), posicao_alocao);
                break;
            }
        } // se n tiver nenhuma posicao disponivel ele n faz nada
    }

    public static void escolherCoisaParaDarDano(Carta carta_que_chamou, int dano) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        // pode ser unidade inimiga ou nexus inimigo
        int escolha = 0;
        while( escolha < 1 || escolha >2 ) // p/ so escolher 1 ou 2
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
        while( escolha < 1 || escolha >2 ) // p/ so escolher 1 ou 2
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

    public static boolean retornarCartaMao(Jogador jogador){ // deixa escolher que carta na mesa de um jogador voltar para a mao do dono

        ArrayList<Seguidor> mesa_jogador = jogador.getMesa().getCartasMesa(jogador.getMesa().getAdversario(jogador));
        int num_carta_escolhida = PrintFactory.pedirInputInt("escolha carta de" +jogador.getNome() + " para retornar para a mao") - 1;
        Seguidor carta_escolhida = mesa_jogador.get(num_carta_escolhida);
;
        if(carta_escolhida !=null){ // o jogador pode escolher uma posicao nula por erro
            carta_escolhida.resetar();
            GerenciadorEfeitos.colocarCartaNaMao(carta_escolhida, carta_escolhida);
            jogador.getMesa().getCartasMesa(jogador).set(num_carta_escolhida, null);
            return true;
        }
        return false; // perde a carta
    }

    public static void matarCartaInimiga(Carta carta_que_chamou) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        Seguidor carta_escolhida = escolherSeguidor(carta_que_chamou.getJogador(), carta_que_chamou.getMesa(),"Que carta quer abater ?");

        if(carta_escolhida !=null){
            carta_escolhida.matarSeguidor();
        }
    }

    public static Seguidor escolherSeguidor(Jogador jogador, Mesa mesa, String mensagem){
        // passa uma mensagem especifica p/ o jogador, envolvendo escolher uma unidade da mesa p/ alguma acao
        try{
            ArrayList<Seguidor> mesa_jogador = jogador.getMesa().getCartasMesa(mesa.getAdversario(jogador));
            int num_carta_escolhida = PrintFactory.pedirInputInt(mensagem) - 1;
            return mesa_jogador.get(num_carta_escolhida);
        }
        catch (IndexOutOfBoundsException | NullPointerException e){ // essa funcao ja tarta as excessoes para as cartas que pedem ela, ja q ja antecipa erros de input
            PrintFactory.printLinha("Posicao invalida. Tente Novamente.");
            return escolherSeguidor(jogador, mesa, mensagem);
        }
    }
}
