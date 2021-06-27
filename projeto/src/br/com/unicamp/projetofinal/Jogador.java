package br.com.unicamp.projetofinal;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Marcador;
import br.com.unicamp.projetofinal.Enums.TipoDeck;
import br.com.unicamp.projetofinal.Enums.Traco;

import java.lang.*;
import java.util.*;

public class Jogador {
	private Mesa mesa;
	private String nome;
	private int vida;
	private Marcador marcador;
	private int mana;
	private Deck deck;
	private Deck mao;
	private int mana_de_feitico = 0;
	private int mana_gasta_feitico;
	
	public Jogador(Mesa mesa) {
		this.setNome();
		this.mesa = mesa;
		mesa.setJogador(this);
		this.vida = 30;
		this.mana = 100; // ainda n sabemos quanto
		this.deck = escolherDeck(mesa, this);
		this.mao = new Deck();
	}
	
	//getters e setters

	public void setManaDeFeitico(int quant){
		if(quant>=3)
			this.mana_de_feitico = 3;
		else{
			this.mana_de_feitico = quant;
		}
	}

	public int getManaDeFeitico() {
		return this.mana_de_feitico;
	}

	public int getMana() {
		return mana;
	}

	public Deck getMao() {
		return mao;
	}

	public void addManaGastaFeitico(int mana){
		this.mana_gasta_feitico+=mana;
	}

	public int getManaGastaFeitico() {
		return this.mana_gasta_feitico;
	}

	public Deck getDeck(){
		return this.deck;
	}

	public String getNome(){
		return this.nome;
	}

	public int getVida(){
		return vida;
	}

	public void setMana(int mana){
		this.mana = mana;
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
			//TODO quando jogado perde
		}
	}


	public void colocarCartaNaMao(Carta carta){
		this.mao.adicionarCarta(carta);
	}

	public void sortearDoDeck(){
		//sortear um numero do deck
		Random sorteio = new Random();
		int carta_sorteada = sorteio.nextInt(this.deck.getSize());
		this.colocarCartaNaMao(this.deck.getCarta(carta_sorteada));
		this.deck.removerCarta(this.deck.getCarta(carta_sorteada));
	}

	public void printCartasNaMao(){
		int i = 0;
		for (Carta carta : mao.getDeck()){
			i++;
			System.out.printf("| %d - %s (%d) |", i, carta.getNome(), carta.getMana());
		}
		System.out.println();
	}

	public void evocarCartas(){

		this.sortearDoDeck();
		boolean running = true;
		while (running) {
			if (this.mao.getSize() > 0) {
				this.mesa.printCartasNaMesa(this.mana);
				this.printCartasNaMao();

				int numero_carta = GerenciadorEfeitos.pedirInput(this.nome + ", escolha que carta quer colocar no jogo");
				if (numero_carta == 0) {
					running = false;
				} else if (numero_carta <= mao.getSize()) {
					Carta carta = this.mao.getCarta(numero_carta - 1);
					carta.jogarCarta();
				}
			} else {
				System.out.println(this.nome + " voce ja n tem mais cartas disponiveis");
				running = false;
			}
		}
	}

	public boolean decidirQueCartasAtacar(){
		int cont = 0;
		ArrayList<Seguidor> cartas_na_mesa = this.mesa.getCartasMesa(this);

		while (true){
			this.mesa.printCartasNaMesa(this.mana);

			int numero_carta = GerenciadorEfeitos.pedirInput(this.nome + ", escolha que cartas quer usar para atacar");
			if (numero_carta == 0) {
				break;
			} else if (numero_carta <= 6) {
				cont++;
				Seguidor carta = cartas_na_mesa.get(numero_carta - 1);
				//VERIFICAR NULIDADE
				carta.setVaiAtacar(true);
			}
			else {
				System.out.println(this.nome + " voce ja n tem mais cartas disponiveis para atacar");
				break;
			}
		}
		return cont != 0;//se atacar retorna true
	}

	public void decidirQueCartasDefender(){
		int cont = 0;
		ArrayList<Seguidor> cartas_na_mesa = this.mesa.getCartasMesa(this);

		while (true){
			this.mesa.printCartasNaMesa(this.mana);

			int numero_carta = GerenciadorEfeitos.pedirInput(this.nome + ", escolha que cartas quer usar para defender");
			if (numero_carta == 0) {
				break;
			} else if (numero_carta <= 6) {
				cont++;
				Seguidor carta = cartas_na_mesa.get(numero_carta - 1);
				//VERIFICAR NULIDADE
				carta.setVaiDefender(true);
			}
			else {
				System.out.println(this.nome + " voce ja n tem mais cartas disponiveis para defender");
				break;
			}
		}
	}

	public void receberAtaques(){
		for (Seguidor seguidor : mesa.getCartasMesaAdversario(this)){
			if(seguidor!= null && seguidor.getVaiAtacar()){
				seguidor.atacar();
				seguidor.setVaiAtacar(false);
				if(seguidor.getTraco() == Traco.ATAQUEDUPLO && seguidor.getGetVezesQueVaiAtacar() == 2 && !seguidor.isMorreu()){
					seguidor.atacar();
				}
			}
		}
	}

	public void desarmarDefesa(){
		for(Seguidor carta : this.mesa.getCartasMesa(this)){
			if (carta!=null) carta.setVaiDefender(false);
		}
	}

	public boolean atacar() {
		this.evocarCartas();
		setManaDeFeitico(this.getMana());
		return decidirQueCartasAtacar();

	}

	public void defender(){
		this.evocarCartas();
		this.decidirQueCartasDefender();
		this.receberAtaques();
		this.desarmarDefesa();
		setManaDeFeitico(this.getMana());
	}

	private Deck escolherDeck(Mesa mesa, Jogador jogador) {
		TipoDeck tipo;
		int num = GerenciadorEfeitos.pedirInput("Digite 1 para deck padrao ou 2 para personalizado");
		if (num == 1){
			tipo = TipoDeck.PADRAO;
		}
		else{
			tipo = TipoDeck.PERSONALIZADO;
		}
		return DeckFactory.fazerDeck(tipo, mesa, jogador);
	}



}
