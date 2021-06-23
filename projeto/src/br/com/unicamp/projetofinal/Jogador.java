package br.com.unicamp.projetofinal;

public class Jogador {
	private Mesa mesa;
	private String nome;
	private int vida;
	private Marcador marcador;
	private int mana;
	
	public Jogador(String nome, Mesa mesa) {
		this.mesa = mesa;
		this.nome = nome;
		this.vida = 20;
		this.mana = 1; // ainda n sabemos quanto
	}
	
	//getters e setters
	
	public void set_marcador(Marcador marcador) {
		this.marcador = marcador;
	}
	
	// muda vida
	
	private void aumentar_vida(int pontos) {
		this.vida +=  pontos;
	}
	
	private void diminuir_vida(int pontos) {
		this.vida -=  pontos;
	}

	private void jogarCarta(int numero_carta){
		Carta carta = this.mao.getCarta(numero_carta);

		carta.atuarNaMesa(this);
		this.mao.removerCarta(carta);
	}

	private void sortearDoDeck(){
		//sortear um numero do deck
		Random sorteio = new Random();
		int carta_sorteada = sorteio.nextInt(deck.getSize());
		mao.adicionarCarta(deck.getCarta(carta_sorteada));
	}

	public void jogarTurno(){

		this.sortearDoDeck();

		Scanner keyboard = new Scanner(System.in);
		boolean running = true;
		while (running) {

			System.out.print("Que carta deseja jogar: ");
			String command = keyboard.nextLine();

			if (command.compareTo("nenhuma") == 0) {
				running = false;
			}
			else{
				int numero_carta = Integer.parseInt(command);
				this.jogarCarta(numero_carta);
				}

		}
	}
}

