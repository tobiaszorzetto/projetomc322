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

	public Jogador(Mesa mesa, String nome) { //overload para o computador, que tem sempre o mesmo nome
		this.setNome(nome);
		this.mesa = mesa;
		mesa.setJogador(this);
		this.vida = 30;
		this.mana = 0;
		this.deck = escolherDeck(mesa, this);
		this.mao = new Deck();
	}

	//GETTERS E SETTERS ------------------------------------------------------------------------------------------------
		//Getters ......................................................................................................

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

		//Setters ......................................................................................................

	public void setManaDeFeitico(int quant){
		this.mana_de_feitico = Math.min(quant + this.mana_de_feitico, 3);
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


	// MUDA VIDA -------------------------------------------------------------------------------------------------------
	
	public void aumentarVida(int pontos) {
		this.vida +=  pontos;
	}
	
	public void diminuirVida(int pontos) {
		this.vida -=  pontos;
		if (vida <= 0){ //se a vida chega a zero ou negativo encerra o jogo
			this.mesa.acabarJogo();
		}
		this.setGolpeAoNexus(true);//coloca q esse jogador recebeu golpe no nexus nessa rodada
	}

	public void setGolpeAoNexus(boolean b) {
		this.golpe_ao_nexus = b;
	}

	// DECK ------------------------------------------------------------------------------------------------------------

	public Deck escolherDeck(Mesa mesa, Jogador jogador) {//utilizacao do padrao Factory de projeto
		TipoDeck tipo;
		int num = PrintFactory.pedirInputInt("Digite: 1 para deck Padrao || 2 para deck Evocador || 3 para deck Bravura || 4 para personalizar");
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
		//o tipo de deck desejado pelo jogador sera repassado para Deck Factory
		// que produzira esse deck e retornara para o jogador
		return DeckFactory.fazerDeck(tipo, mesa, jogador);
	}

	public void escolherQuantasInciaisFicar(){//jogador escolhe o numero de cartas q deseja trocar aleatoriamente

		Random sorteio = new Random();
		int quant =  PrintFactory.pedirInputInt("Quer trocar quantas");
		if (quant == 0) return;
		for(int i = 0; i< quant; i++){ //sorteia quant cartas na mao e retorna elas ao deck
			int numero  = sorteio.nextInt(mao.getSize()) ;
			this.deck.adicionarCarta(mao.getCarta(numero));
			this.mao.removerCarta(mao.getCarta(numero));
		}for(int i = 0; i< quant; i++){
			this.sortearDoDeck();//sorteia quant novas cartas
		}
	}

	public Carta sortearDoDeck(){
		//sortear um numero do deck
		Random sorteio = new Random();
		try{
			int carta_sorteada = sorteio.nextInt(this.deck.getSize());//sorteia uma carta
			this.colocarCartaNaMao(this.deck.getCarta(carta_sorteada));//coloca a carta na mao
			this.deck.removerCarta(this.deck.getCarta(carta_sorteada));//remove do Deck
			return this.deck.getCarta(carta_sorteada);//retorna essa carta sorteada para caso quem chamou precise
		} catch (IndexOutOfBoundsException | IllegalArgumentException e){ //acabaram as cartas do deck
			System.out.println("Nao ha mais cartas para serem sorteadas");
			return null;
		}
	}

	// CHAMAR ATAQUE OU DEFESA -----------------------------------------------------------------------------------------

	public boolean atacar() throws ManaInsuficienteException {
		this.sortearDoDeck();
		this.evocarCartas();
		setManaDeFeitico(this.getMana());//seta a mana de feitico de acordo com a mana restante
		return decidirQueCartasCombater(0);//retorna se realizou ataque ou nao
	}

	public void defender() throws ManaInsuficienteException {
		this.sortearDoDeck();
		this.evocarCartas();
		this.decidirQueCartasCombater(0);
		setManaDeFeitico(this.getMana());//seta a mana de feitico de acordo com a mana restante
	}

	// FUNCOES GERAIS --------------------------------------------------------------------------------------------------

	public void colocarCartaNaMao(Carta carta){
		this.mao.adicionarCarta(carta);
	}

	public int escolherCartaColocar(){//funcao auxiliar para receber posicao da carta q deseja colocar
		return PrintFactory.pedirInputInt(this.nome + ", escolha que carta quer colocar no jogo") - 1;
	}

	public void evocarCartas() throws ManaInsuficienteException {
		if (this.mao.getSize() > 0) {
			PrintFactory.printCartasNaMesa(this.mesa); //para evocar, mostra a mesa atualizada
			PrintFactory.printCartasNaMao(this);//mostra a mao do jogador atualizada

			int numero_carta = escolherCartaColocar();//utiliza da funcao auxiliar para escolher a posicao da carta
			if (numero_carta == -1) {
				return; // o jogador na quer mais evocar nenhuma
			} else{
				try{
					Carta carta = this.mao.getCarta(numero_carta);
					carta.jogarCarta();// tenta joga-la
				} catch (IndexOutOfBoundsException | PosicaoMesaOcupadaException e){
					System.out.println("Nao há carta nessa posicao");
				}
				catch (ManaInsuficienteException e){
					System.out.println("Sem mana suficiente!");
				}
				finally {
					this.evocarCartas(); //ve se o jogador quer evocar novamente
				}
			}
		}
		PrintFactory.printLinha("Sua mao esta vazia");
	}

	public int escolherPosicao(){//funcao auxiliar para escolher posicao que sera jogada a carta
		return PrintFactory.pedirInputInt(this.nome + ", escolha a posicao da mesa em que quer colocar a carta") - 1;
	}

	public int escolherCartaCombater(){
		if (this.marcador == Marcador.ATACANTE) {//se for atacante faz um pergunta diferente para o usuario
			return PrintFactory.pedirInputInt(this.nome + ", escolha que cartas quer usar para atacar") - 1;
		}
		return PrintFactory.pedirInputInt(this.nome + ", escolha que cartas quer usar para defender") - 1;
	}

	public boolean decidirQueCartasCombater(int cont){ // cont marca as cartas ja escolhidas p atacar
		ArrayList<Seguidor> cartas_na_mesa = this.mesa.getCartasMesa(this);
		PrintFactory.printCartasNaMesa(this.mesa);
		int numero_carta = this.escolherCartaCombater();

		if (numero_carta == -1) { //se o jogador escolheu que nao quer colocar mais nenhuma
			return cont != 0;//se setou alguma p/ atacar retorna true
		} else if (numero_carta < 6) {
			Seguidor carta = cartas_na_mesa.get(numero_carta);
			this.prepararParaCombate(carta);
			return this.decidirQueCartasCombater(cont+ 1); // continua, incrementanddo cont
		} else {
			return cont != 0;//se nao satisfazer nenhuma condicao, devolve se o jogador quer atacar ou defender
		}
	}

	private void prepararParaCombate(Seguidor carta) {
		if(marcador == Marcador.ATACANTE){
			try{
				carta.setVaiAtacar(true);//se for atacante coloca a carta para atacar
			} catch(NullPointerException e) {
				System.out.println("Nao ha carta na posicao escolhida");
			}
		} else{//caso em que eh defensor
			try{
				carta.setVaiDefender(true);//tenta colocar a carta para defender
			} catch(NullPointerException e) {
				System.out.println("Nao ha carta na posicao escolhida");
			}
		}
	}

	public void desarmarDefesa(){ // seta todas as cartas do jogador para nao vao defender

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
