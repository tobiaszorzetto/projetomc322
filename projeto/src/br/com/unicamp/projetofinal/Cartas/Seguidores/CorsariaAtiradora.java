package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

public class CorsariaAtiradora extends Seguidor {
    int dano_ao_nexus = 0;
    public CorsariaAtiradora(Mesa mesa, Jogador jogador) {
        super("Corsaria Atiradora", 1, 1, 2, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {
        int rodada = this.getMesa().getParteDaRodada();
        if(rodada == 1){
            for(Seguidor carta: this.getMesa().getCartasMesa(this.getJogador())){
                if( carta!= null && carta!=this && carta.getVaiAtacar()){
                    dano_ao_nexus++;
                }
            }
        }
        if(rodada == 3){
            this.atacarNexus(this.getAdversario(), this.dano_ao_nexus);
            this.dano_ao_nexus = 0;
        }
    }
}
