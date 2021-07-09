package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;
import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;

/*
*  5 | 5 | 5
*
*  Ao ser colocado na mesa -> da 1 | 1 para todos os seguidores aliados
* */

public class GuardaTerrestreAvarosiano extends Seguidor{

	public GuardaTerrestreAvarosiano(Mesa mesa, Jogador jogador) {
		super("Guarda Terrestre Avarosiano", 5, 5, 5, mesa, jogador);
		
	}

	@Override
	protected void realizarEfeitoAntesDeColocado() {
		for(Seguidor carta: this.getMesa().getCartasMesa(this.getJogador())) {
			if(carta!=null) {
				GerenciadorEfeitos.aumentarAtaqueVida(carta, 1, 1);
			}
		}
	}

}
