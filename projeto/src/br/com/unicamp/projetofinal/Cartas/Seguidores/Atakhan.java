package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;

import java.util.ArrayList;

/*
*
* 10|5|12
*
* Toda vez que vai atacar soma em seu ataque o ataque dos seus aliados
*
*
*
* */

public class Atakhan extends Seguidor {

    public Atakhan(Mesa mesa, Jogador jogador){
        super("Atakhan", 10, 5, 12, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
    }

    public void atacar() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        this.aumentarVezesAtacou();
        int endereco = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);
        int ataque_adicional = calcularAtaqueAdicional();

        this.setAtaque(this.getAtaque() + ataque_adicional);

        Jogador adversario = this.getAdversario();
        ArrayList<Seguidor> cartas_adversario = this.getMesa().getCartasMesa(adversario);
        Seguidor carta_adversario = cartas_adversario.get(endereco);

        if(deveAtacarNexus( carta_adversario )) {
            this.atacarNexus(adversario, this.getAtaque());
        }
        else{//atacar a carta na posicao do adversario
            this.realizarCombate(carta_adversario);
        }

        this.setAtaqueOriginal();
    }

    private int calcularAtaqueAdicional() {
        ArrayList<Seguidor> cartas_mesa_aliadas = this.getMesa().getCartasMesa(this.getJogador());
        int ataque_adicional = 0;
        for (Seguidor seguidor : cartas_mesa_aliadas){
            if (seguidor != null && seguidor != this){
                ataque_adicional += seguidor.getAtaque();
            }
        }
        return ataque_adicional;
    }

}
