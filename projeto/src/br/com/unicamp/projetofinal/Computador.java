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
        ArrayList<Seguidor> carta_mesa = this.getMesa().getCartasMesa(this);
        if (this.getMarcador() == Marcador.ATACANTE) {
            for (int i = 0; i < 6; i++) {
                Seguidor carta = carta_mesa.get(i);
                if (carta != null && !carta.getVaiAtacar()) {
                    return i + 1;
                }
            }
        }

        if (this.getMarcador() == Marcador.DEFENSOR) {
            for (int i = 0; i < 6; i++) {
                Seguidor carta = carta_mesa.get(i);
                if (carta != null && carta.naoVaiDefender()) {
                    return i + 1;
                }
            }
        }
        return 0;
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
        Deck mao = this.getMao();
        int mana = this.getMana();
        int max = 0;
        int pos = 0;
        for(int i = 0; i<mao.getSize(); i++){
            if(mao.getDeck().get(i).getMana() > max && mao.getDeck().get(i).getMana()<=mana){
                max = mao.getDeck().get(i).getMana();
                pos = i+1;
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

        int num = verSeTemCarta(conjunto, cartas, num_cartas_quer_colocar, mana, max);
        if (num == -1 && conjunto.size() == 0) return 0;
        else if (num == -1) return cartas.getDeck().indexOf(conjunto.getLast()) + 1;
        else return num;
    }

    private int verSeTemCarta(LinkedList<Seguidor> conjunto, Deck cartas, int num, int mana, LinkedList<Seguidor> max) throws IllegalArgumentException {
    if (num == 0) {
        try{
            return cartas.getDeck().indexOf(conjunto.getLast()) + 1;
        } catch (NoSuchElementException e){
            return -1;
        }

        } else if (mana <= 0) {
            return -1;
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
                        return carta_a_voltar;
                    } else {
                        conjunto.removeLast();
                    }
                } catch (IllegalArgumentException e) {
                    continue;
                }
            }
        }
        return -1;
        }
    }

    public int calcularNumCartasColocar(){
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
        ArrayList<Seguidor> mesa_adversario = this.getMesa().getCartasMesaAdversario(this);
        ArrayList<Seguidor> mesa_aliada = this.getMesa().getCartasMesa(this);

        for(int i = 0; i<6; i++){
            if (mesa_adversario.get(i)!= null && mesa_aliada.get(i) == null){
                return i+1;
            }
        }
        for(int i = 0; i<6; i++){
            if (mesa_aliada == null){
                return i+1;
            }
        }
        return 0;
    }
}