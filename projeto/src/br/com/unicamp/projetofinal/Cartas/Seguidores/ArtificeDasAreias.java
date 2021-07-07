package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;

public class ArtificeDasAreias extends Seguidor {
    public ArtificeDasAreias(Mesa mesa, Jogador jogador) {
        super("Artifice Das Areias", 4, 3, 5, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {

    }

    @Override
    public void realizarCombate(Seguidor carta_adversario) throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        GerenciadorEfeitos.evocarSeguidorAtacante(new SoldadoDeAreia(this.getMesa(),this.getJogador()));
        this.diminuirVida(carta_adversario.getAtaque());//diminui a vida desse seguidor
        boolean adversario_morreu = carta_adversario.diminuirVida(this.getAtaque());//diminui a vida do adversario e verifica se ele morreu
        if (adversario_morreu){
            verificarFuria(); //chama-se apenas quando sabemos que matou o inimigo
            verificarSobrepujar(carta_adversario);
        }
    }

}
