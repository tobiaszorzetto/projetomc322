package br.com.unicamp.projetofinal.Cartas.Feiticos;

import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.Enums.Traco;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

public class CentellhaFinal extends Feitico {

    public CentellhaFinal(Mesa mesa, Jogador jogador) {
        super("Centelha Final", 0, mesa, jogador);
        this.setTraco(Traco.SOBREPUJAR);
    }

    public void realizarEfeito(){
        GerenciadorEfeitos.escolherCartaAdversariaParaDarDano(this, 4);
    }
}
