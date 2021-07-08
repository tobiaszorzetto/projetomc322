package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;

import java.util.ArrayList;

public class SombraViva extends Seguidor {
    private int rodada_evocado;

    public SombraViva(Mesa mesa, Jogador jogador){
        super("Sombra Viva", 3, 3,2, mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        if (this.getMesa().getRodada() > rodada_evocado){
            this.matarSeguidor();
        }
    }

    @Override
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
        if (posicao_alocacao<0){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(this.getMesa().getCartasMesa(jogador).get(posicao_alocacao-1) != null){
            throw new PosicaoMesaOcupadaException();
        }
        this.rodada_evocado = this.getMesa().getRodada();
        this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
    }

    @Override
    public void atacar() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        this.aumentarVezesAtacou();
        int endereco = this.getMesa().getCartasMesa(this.getJogador()).indexOf(this);

        Jogador adversario = this.getAdversario();
        ArrayList<Seguidor> cartas_adversario = this.getMesa().getCartasMesa(adversario);
        Seguidor carta_adversario = cartas_adversario.get(endereco);

        if(deveAtacarNexus( carta_adversario )) {
            this.atacarNexus(adversario, this.getAtaque());
        }
        else{//atacar a carta na posicao do adversario
            this.realizarCombate(carta_adversario);
        }
        matarSeguidor();//apos golpear morre
    }
}
