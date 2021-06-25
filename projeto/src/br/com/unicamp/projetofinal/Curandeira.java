package br.com.unicamp.projetofinal;

import java.util.ArrayList;
import java.util.Scanner;

public class Curandeira extends Seguidor{
	//cura aumenta a vida de uma carta aliada quando entra em jogo
	
	private boolean primeira_rodada;
	
	public Curandeira(Mesa mesa, Jogador jogador, GerenciadorEfeitos ge) {
		super("curandeira", 4,2,4, mesa, jogador, ge);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void verificarCondicao() {

	}

	@Override
	public void atuarNaMesa(Jogador jogador) {
		this.ge.escolherCartaCurar(this);
		this.getMesa().colocarCartaMesa(jogador, this);
		this.getMesa().verificarCondicoes();
	}
}

