package br.com.unicamp.projetofinal;

import java.lang.*;
import java.util.*;

import java.util.*;

public class Jogador {
	private Mesa mesa;
	private String nome;
	private int vida;
	private Marcador marcador;
	private int mana;
	private Deck deck;
	private Deck mao;
	
	public Jogador(Mesa mesa) {
		this.setNome();
		this.mesa = mesa;
		mesa.setJogador(this);
		this.vida = 30;
		this.mana = 1; // ainda n sabemos quanto
		this.deck = new Deck();
		this.mao = new Deck();
	}
	
	//getters e setters

	public Deck getDeck(){
		return this.deck;
	}

	public String getNome(){
		return this.nome;
	}

	public int getVida(){
		return vida;
	}


	private void setNome() {
		Scanner scan = new Scanner(System.in);
		this.nome = scan.nextLine();
		//CLOSE NAO FUNCIONA?????
	}

	public void set_marcador(Marcador marcador) {
		this.marcador = marcador;
	}
	
	// muda vida
	
	public void aumentarVida(int pontos) {
		this.vida +=  pontos;
	}
	
	public void diminuirVida(int pontos) {
		this.vida -=  pontos;
		if (vida <= 0){
			//ENCERRAR O JOGO (JOGADOR PERDEU)
		}
	}

	private void jogarCarta(int numero_carta){
		Carta carta = this.mao.getCarta(numero_carta);

		carta.atuarNaMesa(this);
		this.mao.removerCarta(carta);
	}

	public void sortearDoDeck(){
		//sortear um numero do deck
		Random sorteio = new Random();
		int carta_sorteada = sorteio.nextInt(this.deck.getSize());
		this.mao.adicionarCarta(this.deck.getCarta(carta_sorteada));
		this.deck.removerCarta(this.deck.getCarta(carta_sorteada));
	}

	public void jogarTurno(){

		this.sortearDoDeck();//pega uma carta aleatoria de seu deck

		Scanner keyboard = new Scanner(System.in);
		boolean running = true;
		while (running) {
			if (this.mao.getSize()>0){
				System.out.println(this.nome + ", quais dessas cartas deseja jogar?");
				int i = 0;
				for (Carta carta : mao.getDeck()){
					i++;
					System.out.printf("%d - %s ", i, carta.getNome());
				}
				System.out.println();
				String command = keyboard.nextLine();

				if (command.compareTo("0") == 0) {
					running = false;
				}
				else if (Integer.parseInt(command)<= mao.getSize()){
					int numero_carta = Integer.parseInt(command) - 1;
					this.jogarCarta(numero_carta);
				}

			}
			else{
				System.out.println(this.nome + " voce ja n tem mais cartas disponiveis");
				running = false;
			}

		}

	}

	public void atacar(){
		ArrayList<Seguidor> cartas_na_mesa = this.mesa.getCartasMesa(this);
		ArrayList<Seguidor> atacantes = new ArrayList<Seguidor>();

		Scanner keyboard = new Scanner(System.in);
		boolean running = true;
		while (running) {

			System.out.println("Defina carta para atacar: ");
			String command = keyboard.nextLine();

			if (command.compareTo("0") == 0) {
				running = false;
			}
			else if(Integer.parseInt(command) <= cartas_na_mesa.size()){
				int numero_carta = Integer.parseInt(command) - 1;
				atacantes.add(cartas_na_mesa.get(numero_carta));
			}
			else{
				System.out.println("nao existe carta com esse indice");
			}
		}
		for (Seguidor carta: atacantes){
			carta.atacar();
			this.mesa.printCartasNaMesa();
		}
	}

	public void escolherDeck(){
		System.out.println("Escolha das seguintes no máximo 40 cartas para montar Deck: ");
		System.out.println("1: Thor 2: Gnomo 3: Curandeira");

		Scanner keyboard = new Scanner(System.in);
		boolean running = true;
		while (running && deck.getSize() < 40) {
			String command = keyboard.nextLine();

			if (command.compareTo("0") == 0) {
				running = false;
				for(Carta carta : deck.getDeck()){
					System.out.println(carta.getNome());
				}
			}
			else{
				int numero_carta = Integer.parseInt(command);
				switch (numero_carta){
					case 1:
						this.deck.adicionarCarta(new Thor(this.mesa, this));
						break;
					case 2:
						this.deck.adicionarCarta(new Gnomo(this.mesa, this));
						break;
					case 3:
						this.deck.adicionarCarta(new Curandeira(this.mesa, this));
						break;
					default:
						System.out.println("Não existe carta com esse numero");
						break;
				}
				System.out.println("Numero de cartas escolhidas: "+ deck.getSize());
			}
		}

	}

}
