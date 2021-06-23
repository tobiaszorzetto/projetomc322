package br.com.unicamp.projetofinal;

import java.security.KeyStore;
import java.util.ArrayList;

public class Mesa {
	private Jogador jogador1 = null;
	private Jogador jogador2 = null;
	private int rodada = 0;
	private Jogador atacante;
	private Jogador defensor;

	private Deck cartas_mesa_1 = new Deck(); //cartas do jogador 1 jogadas na mesa
	private Deck cartas_mesa_2 = new Deck(); //cartas do jogador 2 jogadas na mesa
	
	public Mesa() {
	}

	public void setJogador(Jogador jogador){
		if(this.jogador1 == null){
			this.jogador1 = jogador;
			this.defensor = jogador;
		}
		else{
			this.jogador2 = jogador;
			this.atacante = jogador;
		}
	}

	private void verificarCondicoes(){
		for (Seguidor carta: cartas_mesa1){
			carta.verificarCondicao();
		}
		for (Seguidor carta: cartas_mesa2){
			carta.verificarCondicao();
		}
	}

	public void passarRodada(){
		this.rodada++;

		Jogador aux = this.atacante;
		this.atacante = this.defensor;//o atacante eh agora quem antes estava defendendo
		this.defensor = aux;

		this.atacante.jogarTurno();
		//caso nao verifique alguma condicao, verificar aqui (menos eficiente porem mais funcional)
		this.defensor.jogarTurno();
		this.atacante.atacar();

	}

	public void colocarCartaNaMesa(Jogador jogador, Carta carta){
		if (jogador == jogador1){
			cartas_mesa_1.adicionarCarta(carta);
		} else{
			cartas_mesa_2.adicionarCarta(carta);
		}
		this.verificarCondicoes();
	}
	
	// Getters e Setters
	public  ArrayList<Seguidor> getCartasMesa(Jogador jogador){
		if (jogador.equals(jogador1)){
			return cartas_mesa1;
		} else{
			return cartas_mesa2;
		}
	}

}

