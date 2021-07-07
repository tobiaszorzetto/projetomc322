package br.com.unicamp.projetofinal.Cartas.Seguidores;
import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

public class EstatuaDeMacaco extends Seguidor {
    public EstatuaDeMacaco(Mesa mesa, Jogador jogador) {
        super("Estatua De Macaco", 3, 0, 5, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        if(this.getMesa().getParteDaRodada() == 0){
            this.diminuirVida(2);
            GerenciadorEfeitos.evocarSeguidor(new MiquinhoExplosivo(this.getMesa(),this.getJogador()));
        }
    }
}
