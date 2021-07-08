package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;
import br.com.unicamp.projetofinal.Cartas.Seguidor;

/*
*  4 | 2 | 4
*
*  ao Ser colocado -> cura um seguidor aliado totalmente
*
* */

public class Curandeira extends Seguidor {
	//cura aumenta a vida de uma carta aliada quando entra em jogo
	
	private boolean primeira_rodada;
	
	public Curandeira(Mesa mesa, Jogador jogador) {
		super("curandeira", 4,2,4, mesa, jogador);
	}

	@Override
	public void verificarCondicao() {

	}

	@Override
	public void atuarNaMesa(Jogador jogador, int posicao_alocacao) throws PosicaoMesaOcupadaException, ManaInsuficienteException {
		if (posicao_alocacao<=0){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(this.getMesa().getCartasMesa(jogador).get(posicao_alocacao-1) != null){
			throw new PosicaoMesaOcupadaException();
		}
		GerenciadorEfeitos.escolherCartaCurar(this);
		this.getMesa().colocarCartaMesa(jogador, this, posicao_alocacao);
	}
}

