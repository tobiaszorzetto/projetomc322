package br.com.unicamp.projetofinal;

import br.com.unicamp.projetofinal.Cartas.Feiticos.DisparoMistico;
import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Cartas.Seguidores.DemolidorImperial;
import br.com.unicamp.projetofinal.Cartas.Seguidores.SoldadoDeAreia;

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
            numero_carta = PrintFactory.pedirInput(carta.getJogador().getNome() + ", quais dessas cartas deseja curar?") - 1;
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
            ArrayList<Seguidor> cartas_mesa = jogador_atingido.getMesa().getCartasMesa(jogador_atingido);
            int numero_carta = PrintFactory.pedirInput("Escolha uma carta para dar "+ dano +"dano");
            try{
                Seguidor carta_escolhida = cartas_mesa.get(numero_carta);
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
        int numero_carta = PrintFactory.pedirInput("Escolha uma carta para golpear os inimigos") - 1;

        Seguidor carta_escolhida = carta.getMesa().getCartasMesa(carta.getJogador()).get(numero_carta);

        ArrayList<Seguidor> cartas_adversario = carta.getMesa().getCartasMesa(carta.getAdversario());

        for(int i = 0; i<6; i++){
            if (cartas_adversario.get(i)!=null)
            cartas_adversario.get(i).diminuirVida(carta_escolhida.getAtaque());
        }
    }

    public static void escolherCartaDobrarValores(Carta carta) {
        ArrayList<Seguidor> cartas_mesa = carta.getMesa().getCartasMesa(carta.getJogador());

        int numero_carta = PrintFactory.pedirInput("Escolha uma carta para golpear os inimigos") - 1;

        Seguidor carta_escolhida = cartas_mesa.get(numero_carta);

        if(carta_escolhida!=null){
            GerenciadorEfeitos.aumentarAtaqueVida(carta_escolhida, carta_escolhida.getVidaOriginal(), carta_escolhida.getAtaque());
        }
    }

    public static void escolherCartasCombate(Carta carta) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        ArrayList<Seguidor> cartas_mesa = carta.getMesa().getCartasMesa(carta.getJogador());
        ArrayList<Seguidor> cartas_adversario = carta.getMesa().getCartasMesa(carta.getAdversario());

        int numero_carta = PrintFactory.pedirInput("Escolha uma carta sua para combater") - 1;
        int numero_adversario = PrintFactory.pedirInput("Escolha uma carta adversaria para combater") - 1;

        Seguidor carta_escolhida = cartas_mesa.get(numero_carta);
        Seguidor carta_adversario = cartas_adversario.get(numero_adversario);
        carta_escolhida.realizarCombate(carta_adversario);
    }

    public static void evocarSeguidorAtacante(Seguidor carta) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        carta.jogarCarta();
        carta.atacar();
        
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

    public static void AtacarTodasAsCartasInimigo(Carta carta) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        ArrayList<Seguidor> cartas_mesa_inimigo = carta.getMesa().getCartasMesaAdversario(carta.getJogador());

        for (Seguidor seguidor : cartas_mesa_inimigo){
            if (seguidor != null){
                seguidor.diminuirVida(1);
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
            }
        }
    }

    public static void escolherCoisaParaDarDano(Carta carta_que_chamou, int dano) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        int escolha = 0;
        while( escolha < 1 || escolha >2 )
            escolha = PrintFactory.pedirInput("1:Dano em uma carta na mesa || 2:Dano ao nexus inimigo");
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

    public static boolean abaterAliado(Jogador jogador_atingido) {
        if (jogador_atingido.getMesa().temCartasMesa(jogador_atingido)){
            ArrayList<Seguidor> cartas_mesa = jogador_atingido.getMesa().getCartasMesa(jogador_atingido);
            int numero_carta = PrintFactory.pedirInput("Escolha uma carta para dar abater");
            try{
                Seguidor carta_escolhida = cartas_mesa.get(numero_carta);
                carta_escolhida.diminuirVida(carta_escolhida.getVidaAtual());
                return true;

            } catch (IndexOutOfBoundsException e){
                System.out.println("Posicao de ataque invalida.");
                return false;
            } catch (NullPointerException e){
                System.out.println("Não há carta nessa posicao");
                return false;
            } catch (Exception e) {}

        }
        return false;
    }

    public static void curarAliadoOuNexus(Carta carta_que_chamou, int quant){
        int escolha = 0;
        while( escolha < 1 || escolha >2 )
            escolha = PrintFactory.pedirInput("1: Curar aliado || 2: Curar nexus em " + quant);
        if (escolha == 1){
            GerenciadorEfeitos.escolherCartaCurar(carta_que_chamou);
        } else{
            GerenciadorEfeitos.curarNexus(carta_que_chamou.getJogador(), 3);
        }
    }
}
