package br.com.unicamp.projetofinal.Cartas.Seguidores;

import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;
import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Traco;

/*
*  6 | 7 | 6
*
* Traco -> sobrepujar
*
* */

public class GarrerozAlfa extends Seguidor{

	public GarrerozAlfa(Mesa mesa, Jogador jogador) {
		super("Garreroz Alfa", 6, 7, 6, mesa, jogador);
		this.setTraco(Traco.SOBREPUJAR);
		
	}

	
}
