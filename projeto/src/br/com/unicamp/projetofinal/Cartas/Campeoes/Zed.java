package br.com.unicamp.projetofinal.Cartas.Campeoes;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Campeao;
import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Cartas.Seguidores.SombraViva;

import java.util.ArrayList;

public class Zed extends Campeao {

    private int vezesAtacouNexus = 0;

    public Zed(Mesa mesa, Jogador jogador){
        super("Zed", 3, 3,2,mesa,jogador);
    }

    @Override
    public void checarLevelUp() {
        if (this.getNaoUpou() && vezesAtacouNexus >= 2){
            this.aumentarAtaque(1);
            this.aumentarVida(1);
            this.upou();
        }
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        checarLevelUp();
    }

    @Override
    public void atacar() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        this.aumentarVezesAtacou();
        int endereco = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);

        Jogador adversario = this.getAdversario();
        ArrayList<Seguidor> cartas_adversario = this.getMesa().getCartasMesa(adversario);
        Seguidor carta_adversario = cartas_adversario.get(endereco);

        GerenciadorEfeitos.evocarSeguidorAtacante(new SombraViva(this.getMesa(), this.getJogador()));

        if(deveAtacarNexus( carta_adversario )) {
            this.atacarNexus(adversario, this.getAtaque());
            vezesAtacouNexus++;
        }
        else{//atacar a carta na posicao do adversario
            this.realizarCombate(carta_adversario);
        }
    }

}
