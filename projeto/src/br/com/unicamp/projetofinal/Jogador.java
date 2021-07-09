package br.com.unicamp.projetofinal;

import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Marcador;
import br.com.unicamp.projetofinal.Enums.TipoDeck;

import java.lang.*;
import java.util.*;

public class Jogador {
	private final Mesa mesa;
	private String nome;
	private int vida;
	private Marcador marcador;
	private int mana;
	private final Deck deck;
	private final Deck mao;
	private int mana_de_feitico = 0;
	private int mana_gasta_feitico;
	private boolean golpe_ao_nexus;
	
	public Jogador(Mesa mesa) {
		this.setNome();
		this.mesa = mesa;
		mesa.setJogador(this);
		this.vida = 30;
		this.mana = 0;
		this.deck = escolherDeck(mesa, this);
		this.mao = new Deck();
	}

	public Jogador(Mesa mesa, String nome) {
		this.setNome(nome);
		this.mesa = mesa;
		mesa.setJogador(this);
		this.vida = 30;
		this.mana = 0;
		this.deck = escolherDeck(mesa, this);
		this.mao = new Deck();
	}

	//GETTERS E SETTERS
		//Getters

	public int getManaDeFeitico() {
		return this.mana_de_feitico;
	}

	public int getMana() {
		return mana;
	}

	public Deck getMao() {
		return mao;
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

	protected Mesa getMesa() { return mesa; }

	public Marcador getMarcador(){ return this.marcador;}

		//Setters

	public void setManaDeFeitico(int quant){
		this.mana_de_feitico = Math.min(quant, 3);
	}

	public void addManaGastaFeitico(int mana){
		this.mana_gasta_feitico+=mana;
	}

	public void setMana(int mana) throws ManaInsuficienteException {
		if (mana<0){
			throw new ManaInsuficienteException();
		}
		this.mana = mana;
	}

	public void setMarcador(Marcador marcador){
		this.marcador = marcador;
	}

	private void setNome() {
		Scanner scan = new Scanner(System.in);
		this.nome = scan.nextLine();
	}

	protected void setNome(String nome){
		this.nome = nome;
	}


	// MUDA VIDA
	
	public void aumentarVida(int pontos) {
		this.vida +=  pontos;
	}
	
	public void diminuirVida(int pontos) {
		this.vida -=  pontos;
		if (vida <= 0){
			this.mesa.acabarJogo();
		}
		this.setGolpeAoNexus(true);
	}

	public void setGolpeAoNexus(boolean b) {
		this.golpe_ao_nexus = b;
	}

	// DECK

	public Deck escolherDeck(Mesa mesa, Jogador jogador) {
		TipoDeck tipo;
		int num = PrintFactory.pedirInput("Digite: 1 para deck Padrao || 2 para deck Evocador || 3 para deck Bravura || 4 para personalizar");
		if (num == 1){
			tipo = TipoDeck.PADRAO;
		} else if(num == 2){
			tipo = TipoDeck.EVOCADOR;
		} else if (num == 3){
			tipo = TipoDeck.BRAVURA;
		}
		else{
			tipo = TipoDeck.PERSONALIZADO;
		}
		return DeckFactory.fazerDeck(tipo, mesa, jogador);
	}

	// CHAMAR ATAQUE OU DEFESA

	public void escolherQuantasInciaisFicar(){
		Random sorteio = new Random();
		int quant =  PrintFactory.pedirInput("Quer trocar quantas");
		if (quant == 0) return;
		for(int i = 0; i< quant; i++){
			int numero  = sorteio.nextInt(mao.getSize()) ;
			this.deck.adicionarCarta(mao.getCarta(numero));
			this.mao.removerCarta(mao.getCarta(numero));
		}for(int i = 0; i< quant; i++){
			this.sortearDoDeck();
		}
	}

	public boolean atacar() throws ManaInsuficienteException {
		this.evocarCartas();
		setManaDeFeitico(this.getMana());
		return decidirQueCartasCombater();

	}

	public void defender() throws ManaInsuficienteException {
		this.evocarCartas();
		this.decidirQueCartasCombater();
		setManaDeFeitico(this.getMana());
	}

	// FUNCOES GERAIS

	public void colocarCartaNaMao(Carta carta){
		this.mao.adicionarCarta(carta);
	}

	public Carta sortearDoDeck(){
		//sortear um numero do deck
		Random sorteio = new Random();

		try{
			int carta_sorteada = sorteio.nextInt(this.deck.getSize());
			this.colocarCartaNaMao(this.deck.getCarta(carta_sorteada));
			this.deck.removerCarta(this.deck.getCarta(carta_sorteada));
			return this.deck.getCarta(carta_sorteada);
		} catch (IndexOutOfBoundsException | IllegalArgumentException e){ //acabaram as cartas do deck
			System.out.println("Nao ha mais cartas para serem sorteadas");
			return null;
		}
	}


	public int escolherCartaColocar(){
		return PrintFactory.pedirInput(this.nome + ", escolha que carta quer colocar no jogo") - 1;
	}

	public void evocarCartas() throws ManaInsuficienteException {
		this.sortearDoDeck();
		boolean running = true;
		while (running) {
			if (this.mao.getSize() > 0) {
				PrintFactory.printCartasNaMesa(this.mesa);
				PrintFactory.printCartasNaMao(this);

				int numero_carta = escolherCartaColocar();
				if (numero_carta == -1) {
					running = false;
				} else{
					try{
						Carta carta = this.mao.getCarta(numero_carta);
						carta.jogarCarta();
					} catch (IndexOutOfBoundsException | PosicaoMesaOcupadaException e){
						System.out.println("Nao há carta nessa posicao");
					}
					catch (ManaInsuficienteException e){
						System.out.println("Sem mana suficiente!");
					}
				}
			} else {
				running = false;
			}
		}
	}


	public int escolherPosicao(){
		return PrintFactory.pedirInput(this.nome + ", escolha a posicao da mesa em que quer colocar a carta") - 1;
	}

	public int escolherCartaCombater(){
		if (this.marcador == Marcador.ATACANTE) {
			return PrintFactory.pedirInput(this.nome + ", escolha que cartas quer usar para atacar") - 1;
		}
		return PrintFactory.pedirInput(this.nome + ", escolha que cartas quer usar para defender") - 1;
	}

	public boolean decidirQueCartasCombater(){
		int cont = 0;
		ArrayList<Seguidor> cartas_na_mesa = this.mesa.getCartasMesa(this);

		while (true){
			PrintFactory.printCartasNaMesa(this.mesa);
			int numero_carta = this.escolherCartaCombater();
			if (numero_carta == -1) {
				break;
			} else if (numero_carta <= 6) {
				cont++;
				Seguidor carta = cartas_na_mesa.get(numero_carta);
				this.prepararParaCombate(carta);
			} else {
				break;
			}
		}
		return cont != 0;//se atacar retorna true
	}

	private void prepararParaCombate(Seguidor carta) {
		if(marcador == Marcador.ATACANTE){
			try{
				carta.setVaiAtacar(true);
			} catch(NullPointerException e) {
				System.out.println("Nao ha carta na posicao escolhida");
			}
		} else{
			try{
				carta.setVaiDefender(true);
			} catch(NullPointerException e) {
				System.out.println("Nao ha carta na posicao escolhida");
			}
		}
	}

	public void desarmarDefesa(){
		for(Seguidor carta : this.mesa.getCartasMesa(this)){
			if (carta != null){
				carta.setVaiDefender(false);
			}
		}
	}

	public boolean golpeAoNexusAtivo() {
		return golpe_ao_nexus;
	}
}
