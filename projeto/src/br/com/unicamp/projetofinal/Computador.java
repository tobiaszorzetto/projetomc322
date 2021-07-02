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
    }

}
