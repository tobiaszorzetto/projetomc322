package Cartas.Feiticos;

import Cartas.Feitico;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;

public class Cura extends Feitico {


    public Cura(Mesa mesa, Jogador jogador) {
        super("Cura", 3, mesa, jogador);
    }

    @Override
    public void atuarNaMesa(Jogador jogador, int posicao_alocacao) {
        GerenciadorEfeitos.escolherCartaCurar(this);
    }
}

