package Cartas.Seguidores;

import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;
import Cartas.Seguidor;

public class Gnomo extends Seguidor {

	public Gnomo(Mesa mesa, Jogador jogador) {
		super("gnomo", 4, 2, 4, mesa, jogador);
	}

	@Override
	public void verificarCondicao() {
		
	}                                                                  

}
