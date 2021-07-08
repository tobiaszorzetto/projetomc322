package br.com.unicamp.projetofinal.Cartas.Seguidores;
import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

/*
*  3 | 0 | 5
*
*  Todo inicio de rodada evoca 1 miquinho explosivo e perde 2 de vida
*
*
* */


public class EstatuaDeMacaco extends Seguidor {
    public EstatuaDeMacaco(Mesa mesa, Jogador jogador) {
        super("Estatua De Macaco", 3, 0, 5, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        if(this.getMesa().getParteDaRodada() == 4){
            this.diminuirVida(2);
            GerenciadorEfeitos.evocarSeguidor(new MiquinhoExplosivo(this.getMesa(),this.getJogador()));
        }
    }
}
