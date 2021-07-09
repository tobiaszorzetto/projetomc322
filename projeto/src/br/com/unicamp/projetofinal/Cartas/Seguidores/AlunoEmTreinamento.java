package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

/*
*  1 | 1 | 1
*
* Para cada aliado evocado, ganhe 1 | 1 apenas nessa rodada
*
* */

public class AlunoEmTreinamento extends Seguidor {

    private int quantos_aliados_inicio;

    public AlunoEmTreinamento(Mesa mesa, Jogador jogador) {
        super("Aluno Em Treinamento", 1, 1, 1,mesa, jogador);
    }

    @Override
    public void verificarCondicao() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
        int parte = this.getMesa().getParteDaRodada();
        if(parte == 0){
            this.setVidaOriginal();
            this.setAtaqueOriginal();
            this.quantos_aliados_inicio = this.getMesa().numCartasMesa(this.getJogador());
        }
        if (parte == 1 || parte == 2){
            int aumento = this.getMesa().numCartasMesa(this.getJogador()) - quantos_aliados_inicio;
            this.quantos_aliados_inicio = this.getMesa().numCartasMesa(this.getJogador());
            GerenciadorEfeitos.aumentarAtaqueVida(this, aumento, aumento);
        }

    }

    @Override
    public void realizarEfeitoAntesDeColocado(){
        this.quantos_aliados_inicio = this.getMesa().numCartasMesa(this.getJogador()) + 1;
    }

}
