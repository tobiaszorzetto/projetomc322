package br.com.unicamp.projetofinal.Cartas.Campeoes;
import br.com.unicamp.projetofinal.Cartas.Campeao;
import br.com.unicamp.projetofinal.Cartas.Feiticos.DisparoMistico;
import br.com.unicamp.projetofinal.Enums.Traco;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

public class Ezreal extends Campeao{

    int vezes_que_upou = 0;

    public Ezreal( Mesa mesa, Jogador jogador) {
        super("Ezreal", 3, 3, 1, mesa, jogador);
        this.setTraco(Traco.ELUSIVO);

    }

    public void checarLevelUp(){
        if(getVezesQueAtacou() - vezes_que_upou*6 >= 6){
            vezes_que_upou++;
            GerenciadorEfeitos.aumentarAtaqueVida(this,1,1);
        }
    }

    public void verificarCondicao(){
        this.checarLevelUp();
    }

    @Override
    public void atacarNexus(Jogador adversario, int quant){
        adversario.diminuirVida(quant);
        GerenciadorEfeitos.colocarCartaNaMao(this, new DisparoMistico(this.getMesa(),this.getJogador()));
    }
}
