package br.com.unicamp.projetofinal;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Marcador;
import br.com.unicamp.projetofinal.Enums.TipoDeck;

import java.util.*;

public class Computador extends Jogador {

    public Computador(Mesa mesa) {
        super(mesa, "PC");
    }

    @Override
    public int escolherCartaCombater() {
        // seta todas as cartas para participar do combate, seja defesa ou ataque em cada caso
        ArrayList<Seguidor> carta_mesa = this.getMesa().getCartasMesa(this);
        if (this.getMarcador() == Marcador.ATACANTE) {
            for (int i = 0; i < 6; i++) {
                Seguidor carta = carta_mesa.get(i);
                if (carta != null && !carta.getVaiAtacar()) {
                    return i;
                }
            }
        }

        if (this.getMarcador() == Marcador.DEFENSOR) {
            for (int i = 0; i < 6; i++) {
                Seguidor carta = carta_mesa.get(i);
                if (carta != null && carta.naoVaiDefender()) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public Deck escolherDeck(Mesa mesa, Jogador jogador) {
        return DeckFactory.fazerDeck(TipoDeck.PADRAO, mesa, jogador);
    }

    @Override
    public int escolherCartaColocar() {
        if(this.getMarcador() == Marcador.DEFENSOR){
            return this.escolherCartaColocarDefensor();
        } else{
            return this.escolherCartaColocarAtacante();
        }

    }

    private int escolherCartaColocarAtacante() {
        // retorna a posicao na mao da carta mais cara possivel de colocar
        Deck mao = this.getMao();
        int mana = this.getMana();
        int max = 0;
        int pos = 0;
        for(int i = 0; i<mao.getSize(); i++){
            if(mao.getDeck().get(i).getMana() > max && mao.getDeck().get(i).getMana()<=mana){
                max = mao.getDeck().get(i).getMana();
                pos = i;
            }
        }
        return pos;
    }

    private int escolherCartaColocarDefensor() {
        int num_cartas_quer_colocar = this.calcularNumCartasColocar();
        Deck cartas = this.getDeck();

        int mana = this.getMana();

        LinkedList<Seguidor> max = new LinkedList<Seguidor>();
        LinkedList<Seguidor> conjunto = new LinkedList<Seguidor>();

        // tenta todas as combinacoes ate achar uma que tenha o numero de cartas esperado
        int num = verSeTemCarta(conjunto, cartas, num_cartas_quer_colocar, mana, max); // se retorna -1 nao existe essa combinacao
        if (num == -1 && conjunto.size() == 0) return -1; // nao existe mais possibilidade ou acabaram as cartas
        else if (num == -1) return cartas.getDeck().indexOf(conjunto.getLast()); //nao existe combinacao entao retorna o ultimo da melhor combinacao possivel que achou
        else return num;
    }

    private int verSeTemCarta(LinkedList<Seguidor> conjunto, Deck cartas, int num, int mana, LinkedList<Seguidor> max) throws IllegalArgumentException {
    if (num == 0) {
        try{
            return cartas.getDeck().indexOf(conjunto.getLast()) ;
        } catch (NoSuchElementException e){
            return -1; // retorna -1 se acabaram as cartas
        }

        } else if (mana <= 0) {
            return -1; // retorna -1 se acabou a mana do pc
        } else {
        for (Carta carta : cartas.getDeck()) {
            if (carta.getMana() <= mana) {
                try {
                    conjunto.addLast((Seguidor) carta);
                    int carta_a_voltar = verSeTemCarta(conjunto, cartas, num - 1, mana - carta.getMana(), max);
                    if (carta_a_voltar != -1) {
                        if (conjunto.size() > max.size()) {
                            max = conjunto;
                        }
                        return carta_a_voltar; // se retornar -1 vai tentar a proxima combinacao
                    } else {
                        conjunto.removeLast();
                    }
                } catch (IllegalArgumentException ignored) {
                }
            }
        }
        return -1; //retorna -1 se n tem mais cartas com a quant de mana
        }
    }

    public int calcularNumCartasColocar(){
        // ve quantas cartas do adversario vao atacar
        ArrayList<Seguidor> mesa_adversario = this.getMesa().getCartasMesaAdversario(this);
        ArrayList<Seguidor> mesa_aliada = this.getMesa().getCartasMesa(this);
        int contador = 0;
        for(int i = 0; i<6; i++){
            if (mesa_adversario.get(i)!= null && mesa_aliada.get(i) == null){
                contador++;
            }
        }
        return contador;
    }

    public int escolherPosicao(){
        // retorna a primeira posicao vazia em que no outro lado existe uma unidade que vai atacar
        ArrayList<Seguidor> mesa_adversario = this.getMesa().getCartasMesaAdversario(this);
        ArrayList<Seguidor> mesa_aliada = this.getMesa().getCartasMesa(this);

        for(int i = 0; i<6; i++){
            if (mesa_adversario.get(i)!= null && mesa_aliada.get(i) == null){
                return i;
            }
        }
        for(int i = 0; i<6; i++){
            if (mesa_aliada == null){
                return i;
            }
        }
        return 0;
    }
}