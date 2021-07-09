package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.*;
import br.com.unicamp.projetofinal.Cartas.Seguidor;
/*
* 0 | 3 | 2
*
* Para ser colocado no jogo, deve-se abater um aliado
*
* */
public class AcougueiroVoraz extends Seguidor {
    public AcougueiroVoraz(Mesa mesa, Jogador jogador) {
        super("Acougueiro Voraz", 0, 3, 2, mesa, jogador);
    }


    @Override
    public void jogarCarta() throws ManaInsuficienteException{

        boolean abateu = GerenciadorEfeitos.abaterAliado(this.getJogador());
        if(abateu){
            Jogador jogador = this.getJogador();
            Deck mao = jogador.getMao();
            Mesa mesa = this.getMesa();
            int posicao_alocacao = jogador.escolherPosicao();
            try{
                jogador.setMana(jogador.getMana() - this.getMana());
                this.atuarNaMesa(jogador, posicao_alocacao);
                mao.removerCarta(this);
            }
            catch (ManaInsuficienteException e){
                System.out.println("Sem mana suficiente!");
            }
            catch (PosicaoMesaOcupadaException e){
                jogador.setMana(jogador.getMana() + this.getMana());
                System.out.println("Essa posicao ja esta ocupada pelo " + mesa.getCartasMesa(jogador).get(posicao_alocacao-1).getNome());
            }
            catch (ArrayIndexOutOfBoundsException e){
                jogador.setMana(jogador.getMana() + this.getMana());
                System.out.println("O mapa tem tamanho maximo de 6 cartas");
            }
        }

    }
}
