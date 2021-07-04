package br.com.unicamp.projetofinal;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Marcador;
import br.com.unicamp.projetofinal.Enums.TipoDeck;

import java.util.ArrayList;

public class Computador extends Jogador{

    public Computador(Mesa mesa) {
        super(mesa);
    }

    @Override
    public int escolherCartaCombater(){
        ArrayList<Seguidor> carta_mesa = this.getMesa().getCartasMesa(this);
        if (this.getMarcador() == Marcador.ATACANTE) {
            ArrayList<Seguidor> cartas_mesa = this.getMesa().getCartasMesa(this);
            for(int i= 0 ; i< 6 ; i++){
                Seguidor carta = carta_mesa.get(i);
                if (carta!=null && !carta.getVaiAtacar()){
                    return i+1;
                }
            }
        }

        if (this.getMarcador() == Marcador.DEFENSOR) {
            ArrayList<Seguidor> cartas_mesa = this.getMesa().getCartasMesa(this);
            for(int i= 0 ; i< 6 ; i++){
                Seguidor carta = carta_mesa.get(i);
                if (carta!=null && carta.naoVaiDefender()){
                    return i+1;
                }
            }
        }
        return 0;
    }

    @Override
    public Deck escolherDeck(Mesa mesa, Jogador jogador) {
        return DeckFactory.fazerDeck(TipoDeck.PADRAO,mesa, jogador);
    }

    @Override
    public int escolherCartaColocar(){
        int num_cartas_quer_colocar = this.getMesa().numCartasMesa();
        ArrayList<Seguidor> cartas_adversario = this.getMesa().getCartasMesaAdversario(this);
        Deck cartas = this.getDeck();
        Deck deck = getDeck();
        int mana = this.getMana();

        LinkedList<Seguidor> max = new LinkedList<Seguidor>();
        LinkedList<Seguidor> conjunto = new LinkedList<Seguidor>();
        int num  = verSeTemCarta(conjunto, cartas, num_cartas_quer_colocar, mana, max );
        if (num == -1 && conjunto.size() == 0) return 0;
        else if (num == -1) return cartas.getDeck().indexOf(conjunto.getLast()) + 1;
        else return num;

    }

    private int verSeTemCarta(LinkedList<Seguidor> conjunto, Deck cartas, int num, int mana, LinkedList<Seguidor> max) throws IllegalArgumentException{
        if (num == 0){
            return cartas.getDeck().indexOf(conjunto.getLast()) + 1;

        }
        else if(mana<=0){
            return -1;
        }
        else{
            for(Carta carta: cartas.getDeck()){

                if(carta.getMana()<=mana){
                    try {
                        conjunto.addLast((Seguidor) carta);
                        int carta_a_voltar  = verSeTemCarta(conjunto, cartas, num- 1, mana - carta.getMana(), max);
                        if (carta_a_voltar != -1) {
                            if(conjunto.size()>max.size()){
                                max = conjunto;
                            }
                            return carta_a_voltar;
                        } else{
                            conjunto.removeLast();
                        }
                    } catch (IllegalArgumentException e){
                        continue;
                    }
                }
            }
            return  -1;
        }



    }
