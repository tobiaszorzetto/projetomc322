package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Feiticos.Dizimar;
import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Traco;

/*
* 8 | 8 | 8
*
* Ao ser colocado na mesa, cria 2 cartas dizimar na mao do jogador
* 
* 
* 
* */


public class CapitaoFarron extends Seguidor {
    public CapitaoFarron(Mesa mesa, Jogador jogador) {
        super("Capitao Farron", 8, 8, 8, mesa, jogador);
        this.setTraco(Traco.SOBREPUJAR);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

    }
    @Override
    public void realizarEfeitoAntesDeColocado(){
        GerenciadorEfeitos.colocarCartaNaMao(this, new Dizimar(this.getMesa(),this.getJogador()));
        GerenciadorEfeitos.colocarCartaNaMao(this, new Dizimar(this.getMesa(),this.getJogador()));
    }
}
