package Cartas.Seguidores;

import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;
import Cartas.Seguidor;

public class Curandeira extends Seguidor {
	//cura aumenta a vida de uma carta aliada quando entra em jogo
	
	private boolean primeira_rodada;
	
	public Curandeira(Mesa mesa, Jogador jogador) {
		super("curandeira", 4,2,4, mesa, jogador);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void verificarCondicao() {

	}

	@Override
	public void atuarNaMesa(Jogador jogador, int posicao_alocacao) {
		GerenciadorEfeitos.escolherCartaCurar(this);
		this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
		this.getMesa().verificarCondicoes();
	}
}

