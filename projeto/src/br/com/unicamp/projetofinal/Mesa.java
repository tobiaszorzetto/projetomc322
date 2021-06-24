package br.com.unicamp.projetofinal;

import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;

public class Mesa {
	private Jogador jogador1 = null;
	private Jogador jogador2 = null;
	private int rodada = 0;
	private Jogador atacante;
	private Jogador defensor;
	private int manaJogo =100;
	private int parte_da_rodada;

	private ArrayList<Seguidor> cartas_mesa1 = new ArrayList<Seguidor>(); //cartas do jogador 1 jogadas na mesa
	private ArrayList<Seguidor> cartas_mesa2 = new ArrayList<Seguidor>(); //cartas do jogador 2 jogadas na mesa
	
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

	public void verificarCondicoes(){
		for (Seguidor carta: cartas_mesa1){
			carta.verificarCondicao();
		}
		for (Seguidor carta: cartas_mesa2){
			carta.verificarCondicao();
		}
	}

	public void destribuirCartasIniciais(){
		for(int i = 0; i<3; i++){
			jogador1.sortearDoDeck();
			jogador2.sortearDoDeck();
		}
	}

	public void passarRodada(){
		this.rodada++;
		this.manaJogo++;
		jogador1.setMana(manaJogo);
		jogador2.setMana(manaJogo);


		Jogador aux = this.atacante;
		this.atacante = this.defensor;//o atacante eh agora quem antes estava defendendo
		this.defensor = aux;

		this.parte_da_rodada = 0;
		this.atacante.jogarTurno();
		//this.printCartasNaMesa();
		//caso nao verifique alguma condicao, verificar aqui (menos eficiente porem mais funcional)
		this.parte_da_rodada = 1;
		this.defensor.jogarTurno();
		//this.printCartasNaMesa();
		this.parte_da_rodada = 2;
		this.atacante.atacar();

		this.parte_da_rodada = 3;
		this.verificarCondicoes();
		//this.printCartasNaMesa();
	}

	public void colocarCartaMesa(Jogador jogador, Seguidor carta){
		if (jogador.equals(jogador1)){
			cartas_mesa1.add(carta);
		} else{
			cartas_mesa2.add(carta);
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

	public Jogador getAdversario(Jogador aliado){
		if (aliado.equals(jogador1)){
			return jogador2;
		} else{
			return jogador1;
		}
	}

	public int getParteDaRodada(){
		return parte_da_rodada;
	}

	public void printCartasNaMesa(){

		System.out.println("========================================");

		System.out.println(this.jogador1.getNome() + " ("+ this.jogador1.getVida()+ ") "+ "............." + " ("+ this.jogador2.getVida()+ ") "+ this.jogador2.getNome());
		System.out.println();

		for (int i = 0; i< 40; i++){
			if (this.cartas_mesa1.size()< i+1 && this.cartas_mesa2.size()<i+1){
				break;
			}
			else if (this.cartas_mesa1.size()>= i+1 && this.cartas_mesa2.size()>= i+1){
				System.out.println(
						"("+ this.cartas_mesa1.get(i).getAtaque() + "/" + this.cartas_mesa1.get(i).getVidaAtual()+ ") "+
						this.cartas_mesa1.get(i).getNome() + "    |    " + this.cartas_mesa2.get(i).getNome() +
						" ("+ this.cartas_mesa2.get(i).getAtaque() + "/" + this.cartas_mesa2.get(i).getVidaAtual()+ ") ");
			}
			else if(this.cartas_mesa1.size()< i+1 && this.cartas_mesa2.size()>= i+1){
				System.out.println("-----------    |    " + this.cartas_mesa2.get(i).getNome() +
						" ("+ this.cartas_mesa2.get(i).getAtaque() + "/" + this.cartas_mesa2.get(i).getVidaAtual()+ ")");
			}
			else if(this.cartas_mesa1.size()>= i+1 && this.cartas_mesa2.size()< i+1){
				System.out.println(
						" ("+ this.cartas_mesa1.get(i).getAtaque() + "/" + this.cartas_mesa1.get(i).getVidaAtual()+ ") "+
						this.cartas_mesa1.get(i).getNome() + "    |    -------------" );
			}
		}

		System.out.println("========================================");

	}
}



