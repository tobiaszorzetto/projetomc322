package br.com.unicamp.projetofinal;

import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.Enums.Marcador;
import br.com.unicamp.projetofinal.Enums.Traco;

import java.util.ArrayList;

public class Mesa {
	private Jogador jogador1 = null;
	private Jogador jogador2 = null;
	private int rodada = 0;
	private Jogador atacante;
	private Jogador defensor;
	private int manaJogo =0;
	private int parte_da_rodada;
	private boolean continuar = true;


	private ArrayList<Feitico> feiticos_ativos = new ArrayList<Feitico>();
	private ArrayList<Seguidor> cartas_mesa1 = new ArrayList<Seguidor>(); //cartas do jogador 1 jogadas na mesa
	private ArrayList<Seguidor> cartas_mesa2 = new ArrayList<Seguidor>(); //cartas do jogador 2 jogadas na mesa

	public Mesa() {
		for(int i = 0; i<6; i++){
			cartas_mesa1.add(null);
			cartas_mesa2.add(null);
		}
	}

	public void acabarJogo(){
		this.continuar = false;
	}

	// GETTERS E SETTERS -----------------------------------------------------------------------------------------------

	public void addFeiticoAtivo(Feitico feitico){
		this.feiticos_ativos.add(feitico);
	}

	public void removerFeiticoAtivo(Feitico feitico){
		this.feiticos_ativos.remove(feitico);
	}

	public int getRodada(){
		return this.rodada;
	}


	public boolean temCartasMesa(Jogador jogador){
		//funcao auxiliar para verificar se ha cartas jogadas na mesa
		for(Seguidor carta: getCartasMesa(jogador)){
			if(carta!=null) return true;
		}
		return false;
	}

	// Getters .........................................................................................................

	public Jogador getAtacante(){
		return this.atacante;
	}

	public Jogador getJogador(int i){
		//devolver o jogador escolhido
		if (i==1){
			return jogador1;
		}
		return jogador2;
	}

	public ArrayList<Seguidor> getCartasMesaAdversario(Jogador jogador){
		//funcao para retornar as cartas na mesa do oponente do jogador passado
		if (jogador.equals(jogador2)){
			return cartas_mesa1;
		} else{
			return cartas_mesa2;
		}
	}

	public Jogador getAdversario(Jogador aliado){
		//funcao para retornar o adversario
		if (aliado.equals(jogador1)){//se o jogador passado for 1
			return jogador2;//devolve o jogador 2
		} else{
			return jogador1;//caso contrario devolve o 1
		}
	}

	public int getParteDaRodada(){
		//devolve a parte da rodada em que o jogo se encontra
		return parte_da_rodada;
	}

	public  ArrayList<Seguidor> getCartasMesa(Jogador jogador){
		//devolve as cartas na mesa do jogador recebido
		if (jogador.equals(jogador1)){
			return cartas_mesa1;
		} else{ // se for o jogador 2
			return cartas_mesa2;
		}
	}

		//Setters ......................................................................................................

	public void setJogador(Jogador jogador){
		//define os atributos iniciais relacionados aos jogadores
		if(this.jogador1 == null){
			this.jogador1 = jogador;
			this.defensor = jogador;
			this.defensor.setMarcador(Marcador.DEFENSOR);
		}
		else{
			this.jogador2 = jogador;
			this.atacante = jogador;
			this.atacante.setMarcador(Marcador.ATACANTE);
		}
	}

	// FUNCOES GERAIS --------------------------------------------------------------------------------------------------

	public void verificarCondicoes() throws ManaInsuficienteException, PosicaoMesaOcupadaException{
		//verifica as condicoes das cartas na mesa
		for (int i = 0; i<6; i++){
			//passa pelas cartas na mesa e verifica sua condicao
			if (cartas_mesa1.get(i)!= null)
				cartas_mesa1.get(i).verificarCondicao();
		}
		for (int i = 0; i < 6; i++){
			//passa pelas cartas na mesa do outro jogador e verifica sua condicao
			if (cartas_mesa2.get(i) != null)
				cartas_mesa2.get(i).verificarCondicao();
		}
		for (int i = 0; i< feiticos_ativos.size(); i++){
			//passa pelos feiticos ativos e verifica a condicao deles
			feiticos_ativos.get(i).verificarCondicao();
		}

	}

	public void destribuirCartasIniciais(Jogador jogador){
		//funcao para distribuir as cartas inciais dos jogadores
		for(int i = 0; i<4; i++){
			jogador.sortearDoDeck();
		}
		PrintFactory.printLinha(jogador.getNome() + " | Cartas iniciais:");
		PrintFactory.printCartasNaMao(jogador);
		jogador.escolherQuantasInciaisFicar();
	}

	public void aumentarMana() throws ManaInsuficienteException {
		//aumenta a mana de acordo com a rodada do jogo
		if(this.manaJogo < 10){//mana vai apenas ate 10
			this.manaJogo++;
		}
		jogador1.setMana(manaJogo);
		jogador2.setMana(manaJogo);
	}

	public void trocarMarcacoes(){
		//funcao para inverter o atacante e o defensor na mudanca de rodada
		this.atacante.setMarcador(Marcador.DEFENSOR);
		this.defensor.setMarcador(Marcador.ATACANTE);
		Jogador aux = this.atacante;
		this.atacante = this.defensor;
		this.defensor = aux;
	}

	public void realizarCombates() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		//funcao para realizar combate entre as cartas na mesa definidas pelos jogadores
		for (int i = 0; i < 6; i++){
			Seguidor seguidor  = this.getCartasMesa(this.atacante).get(i);
			if(seguidor!= null && seguidor.getVaiAtacar()){//se houver seguidor e ele for atacar
				seguidor.atacar();//ataca
				seguidor.setVaiAtacar(false);// ja atacou
				if(seguidor.getTraco() == Traco.ATAQUEDUPLO && seguidor.getGetVezesQueVaiAtacar() == 2 && seguidor.naoMorreu()){
					seguidor.atacar(); //verifica o Traco de ataque duplo (atacar duas vezes)
				}
			}
		}
	}

	public void colocarCartaMesa(Jogador jogador, Seguidor carta, int posicao_alocacao) throws ManaInsuficienteException, PosicaoMesaOcupadaException{
		//coloca na mesa do jogador recebido a carta recebida na posicao recebida
		if (jogador.equals(jogador1)){
			cartas_mesa1.set(posicao_alocacao, carta);
		} else{
			cartas_mesa2.set(posicao_alocacao,carta);
		}
	}
	// PARTES ----------------------------------------------------------------------------------------------------------

	public boolean realizarParte0() throws ManaInsuficienteException, PosicaoMesaOcupadaException {

		this.parte_da_rodada = 0;
		this.verificarCondicoes();
		return this.atacante.atacar();//faz o jogador decidir acoes de ataque
	}

	public void realizarParte1() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		this.parte_da_rodada = 1;
		this.verificarCondicoes();
		defensor.defender();//faz o jogador decidir acoes de defesa

		this.parte_da_rodada = 2; // essa parte serve so como transicao
		this.verificarCondicoes();

	}

	public void realizarParte3() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		//
		this.parte_da_rodada = 3;
		this.realizarCombates();//realiza os combates
		this.verificarCondicoes();
		this.defensor.desarmarDefesa();//tira da defesa as cartas que tinham sido colocadas na defesa (para a proxima rodada)
	}

	// PRINCIPAL -------------------------------------------------------------------------------------------------------

	public boolean passarRodada() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		// funcao que realiza o desenvolvimento das rodadas!

		this.verificarCondicoes();

		this.rodada++;
		this.aumentarMana();//aumenta a mana de acordo com as restricoes
		this.trocarMarcacoes();//inverte as funcoes dos jogadores

		boolean atacou = this.realizarParte0();
		if (atacou){ //so realiza se o jogador decidiu atacar na parte 0
			this.realizarParte1();
			this.realizarParte3();
		}
		this.parte_da_rodada = 4; // apesar de ser a ultima, representa a transicao para o inicio da proxima rodada
		return this.continuar;

	}

	public int numCartasMesa(Jogador jogador){
		//devolve o numero de cartas na mesa do jogador recebido
		ArrayList<Seguidor> cartas_mesa = this.getCartasMesa(jogador);
		int num_cartas = 0;
		for (Seguidor carta : cartas_mesa) {
			if (carta != null) {
				num_cartas += 1;
			}
		}
		return num_cartas;
	}

	public boolean temEspacoMesa(Jogador jogador){
		ArrayList<Seguidor> cartas_mesa = this.getCartasMesa(jogador);
		for (Seguidor carta : cartas_mesa){
			if (carta == null){//se houver posicao vazia eh pq tem espaco
				return true;
			}
		}
		return false;
	}

	public ArrayList<Seguidor> getCartas_mesa1(){
		return this.cartas_mesa1;
	}

	public ArrayList<Seguidor> getCartas_mesa2(){
		return this.cartas_mesa2;
	}

	public Jogador getDefensor() {
		return this.defensor;
	}
}



