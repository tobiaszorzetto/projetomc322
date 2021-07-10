package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

import java.util.ArrayList;

/*
* Custo de mana: 3
* Escolha uma carta do inimigo com 3 ou menos de ataque para matar
*
*
*
* */

public class GolpeExpurgante extends Feitico {

    public GolpeExpurgante(Mesa mesa, Jogador jogador){
        super("Golpe Expurgante", 3, mesa, jogador);
    }

    @Override
    public void realizarEfeito() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        ArrayList<Seguidor> cartas_mesa_adversario = this.getMesa().getCartasMesaAdversario(this.getJogador());
        if (temCartaCom3OuMenosDePoder()){
            int num_carta = this.getMesa().getJanela().pedirInput("Escolha a carta com 3 ou menos de poder que deseja expurgar") - 1;
            try{
                if (cartas_mesa_adversario.get(num_carta).getAtaque() > 3){
                    this.getMesa().getJanela().trocarAviso("A carta escolhida tem poder maior que 3");
                }
                Seguidor expurgado = cartas_mesa_adversario.get(num_carta);
                expurgado.matarSeguidor();
            } catch(NullPointerException e){
                this.getMesa().getJanela().trocarAviso("A posicao escolhida nao possui carta");
                realizarEfeito();
            } catch(IndexOutOfBoundsException e){
                this.getMesa().getJanela().trocarAviso("Posicao digitada para expurgar invalida");
                realizarEfeito();
            }
        }
    }

    private boolean temCartaCom3OuMenosDePoder(){
        ArrayList<Seguidor> cartas_mesa_adversario = this.getMesa().getCartasMesaAdversario(this.getJogador());
        for (Seguidor seguidor : cartas_mesa_adversario){
            if (seguidor.getAtaque() <= 3){
                return true;
            }
        }
        return false;
    }


}
