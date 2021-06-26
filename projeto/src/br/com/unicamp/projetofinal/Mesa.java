package br.com.unicamp.projetofinal;

import Cartas.Seguidor;

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
		for(int i = 0; i<6; i++){
			cartas_mesa1.add(null);
			cartas_mesa2.add(null);
		}
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
			if (carta!= null)
				carta.verificarCondicao();
		}
		for (Seguidor carta: cartas_mesa2){
			if (carta!= null)
				carta.verificarCondicao();
		}
	}

	public void destribuirCartasIniciais(){
		for(int i = 0; i<3; i++){
			jogador1.sortearDoDeck();
			jogador2.sortearDoDeck();
		}
	}

	public void aumentarMana(){
		if(this.manaJogo < 10){
			this.manaJogo++;
			jogador1.setMana(manaJogo);
			jogador2.setMana(manaJogo);
		}

	}

	public void passarRodada(){
		this.rodada++;
		this.aumentarMana();


		Jogador aux = this.atacante;
		this.atacante = this.defensor;//o atacante eh agora quem antes estava defendendo
		this.defensor = aux;

		this.parte_da_rodada = 0;
		boolean atacou = this.atacante.atacar();
		this.parte_da_rodada = 1;
		//this.printCartasNaMesa();
		if (atacou){//se o atacante atacou o defensor pode decidir se defender
			defensor.defender();
			this.parte_da_rodada = 2;
		}
		this.parte_da_rodada = 3;
		this.verificarCondicoes();
		//this.printCartasNaMesa();
	}

	public void colocarCartaMesa(Jogador jogador, Seguidor carta, int posicao_alocacao){
		if (jogador.equals(jogador1)){
			cartas_mesa1.add(posicao_alocacao-1, carta);
		} else{
			cartas_mesa2.add(posicao_alocacao-1,carta);
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

	public ArrayList<Seguidor> getCartasMesaAdversario(Jogador jogador){
		if (jogador.equals(jogador2)){
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

	public void printLinhaCartaEsquerda(Seguidor carta1, String cor, String branco){
		System.out.println( cor +
				" ("+ carta1.getAtaque() + "/" + carta1.getVidaAtual()+ ") "+
						carta1.getNome() + branco + "    |    -------------" );
	}

	public void printLinhaCartaDireita(Seguidor carta2, String cor, String branco){
		System.out.println("-----------    |    " + cor + carta2.getNome() +
				" ("+ carta2.getAtaque() + "/" + carta2.getVidaAtual()+ ")" + branco);
	}

	public void printLinha2Cartas(Seguidor carta1, Seguidor carta2, String cor1, String cor2, String branco){
		System.out.println( cor1 +
		"("+ carta1.getAtaque() + "/" + carta1.getVidaAtual()+ ") "+ carta1.getNome() + cor2 +
				"    |    " + carta2.getNome() + " ("+ carta2.getAtaque() + "/" + carta2.getVidaAtual()+ ") "+ branco);
	}

	public void printCartasNaMesa(ArrayList<Seguidor> cartas_a_colorir){
		String amarelo = ConsoleColors.YELLOW;
		String branco = ConsoleColors.BLUE;

		System.out.print(branco);
		System.out.println("========================================");

		System.out.println(this.jogador1.getNome() + " ("+ this.jogador1.getVida()+ ") "+ "............." + " ("+ this.jogador2.getVida()+ ") "+ this.jogador2.getNome());
		System.out.println();

		for (int i = 0; i< 6; i++){
			Seguidor carta1 = this.cartas_mesa1.get(i);
			Seguidor carta2 = this.cartas_mesa2.get(i);
			if(carta1 == null && carta2==null){
				System.out.println("-------------    |    -------------");
			}
			else if (carta1 != null && carta2!=null){
				if (cartas_a_colorir.contains(carta1)){
					this.printLinha2Cartas(carta1, carta2, amarelo, branco, branco);
				}
				else if (cartas_a_colorir.contains(carta2)){
					this.printLinha2Cartas(carta1, carta2, branco, amarelo, branco);
				}
				else this.printLinha2Cartas(carta1, carta2, branco, branco, branco);
			}
			else if(carta1 == null){

				if (cartas_a_colorir.contains(carta2)){
					this.printLinhaCartaDireita(carta2, amarelo, branco);
				}
				else this.printLinhaCartaDireita(carta2, branco, branco);
			}
			else {
				if (cartas_a_colorir.contains(carta1)){
					this.printLinhaCartaEsquerda(carta1, amarelo, branco);
				}
				else this.printLinhaCartaEsquerda(carta1, branco, branco);
			}
		}

		System.out.println("========================================");
	}

	public void printCartasNaMesa(){
		ArrayList<Seguidor> lista = new ArrayList<Seguidor>();
		for (Seguidor carta: this.getCartasMesa(this.atacante)){
			if(carta !=null && carta.getVaiAtacar()) lista.add(carta);
		}
		printCartasNaMesa(lista);
	}

	public void printCartasNaMesa(int mana){
		System.out.println("========================================");
		System.out.println("MANA: " + mana);
		printCartasNaMesa(new ArrayList<Seguidor>());
	}
}



