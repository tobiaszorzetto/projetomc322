package br.com.unicamp.projetofinal.Cartas.Campeoes;
import br.com.unicamp.projetofinal.Cartas.Campeao;
import br.com.unicamp.projetofinal.Cartas.Feiticos.DisparoMistico;
import br.com.unicamp.projetofinal.Enums.Traco;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

public class Ezreal extends Campeao{


    public Ezreal( Mesa mesa, Jogador jogador) {
        super("Ezreal", 3, 3, 1, mesa, jogador);
        this.setTraco(Traco.ELUSIVO);
    }

    public void checarLevelUp(){

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
