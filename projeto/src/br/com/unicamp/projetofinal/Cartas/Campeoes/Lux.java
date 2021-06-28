package br.com.unicamp.projetofinal.Cartas.Campeoes;

import br.com.unicamp.projetofinal.Cartas.Campeao;
import br.com.unicamp.projetofinal.Cartas.Feiticos.CentellhaFinal;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

public class Lux extends Campeao {
    private boolean ja_upou = false;
    private final int mana_inicial;
    private int cont_mana = 0;
    public Lux(Mesa mesa, Jogador jogador) {
        super("Lux", 6, 4, 5, mesa, jogador);
        mana_inicial = this.getJogador().getManaGastaFeitico();
    }

    @Override
    public void checarLevelUp() {
        if(!ja_upou && cont_mana>=6){
            GerenciadorEfeitos.colocarCartaNaMao(this, new CentellhaFinal(this.getMesa(),this.getJogador()));
            ja_upou = true;
        }
    }

    @Override
    public void verificarCondicao() {
        if (this.getMesa().getParteDaRodada() == 1 ){
            this.cont_mana = this.getJogador().getManaGastaFeitico() - this.mana_inicial;
        }
        this.checarLevelUp();
    }
}
