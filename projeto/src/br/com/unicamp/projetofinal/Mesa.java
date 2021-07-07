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
	private int manaJogo =100;
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

	// GETTERS E SETTERS

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
		for(Seguidor carta: getCartasMesa(jogador)){
			if(carta!=null) return true;
		}
		return false;
	}

		// Getters

	public Jogador getAtacante(){
		return this.atacante;
	}

	public Jogador getJogador(int i){
		if (i==1){
			return jogador1;
		}
		return jogador2;
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

	public  ArrayList<Seguidor> getCartasMesa(Jogador jogador){
		if (jogador.equals(jogador1)){
			return cartas_mesa1;
		} else{
			return cartas_mesa2;
		}
	}

		//Setters

	public void setJogador(Jogador jogador){
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

	// FUNCOES GERAIS

	public void verificarCondicoes() throws ManaInsuficienteException, PosicaoMesaOcupadaException{
		for (Seguidor carta: cartas_mesa1){
			if (carta!= null)
				carta.verificarCondicao();
		}
		for (Seguidor carta: cartas_mesa2){
			if (carta!= null)
				carta.verificarCondicao();
		}
		for (Feitico feitico: this.feiticos_ativos){
			feitico.verificarCondicao();
		}

	}

	public void destribuirCartasIniciais(Jogador jogador){
		for(int i = 0; i<4; i++){
			jogador.sortearDoDeck();
		}
		PrintFactory.printLinha(jogador.getNome() + " | Cartas iniciais:");
		PrintFactory.printCartasNaMao(jogador);
		jogador.escolherQuantasInciaisFicar();
	}

	public void aumentarMana() throws ManaInsuficienteException {
		if(this.manaJogo < 10){
			this.manaJogo++;

		}
		jogador1.setMana(manaJogo);
		jogador2.setMana(manaJogo);
	}

	public void trocarMarcacoes(){
		this.atacante.setMarcador(Marcador.DEFENSOR);
		this.defensor.setMarcador(Marcador.ATACANTE);
		Jogador aux = this.atacante;
		this.atacante = this.defensor;//o atacante eh agora quem antes estava defendendo
		this.defensor = aux;
	}

	public void realizarCombates() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		for (int i = 0; i < 6; i++){
			Seguidor seguidor  = this.getCartasMesa(this.atacante).get(i);
			if(seguidor!= null && seguidor.getVaiAtacar()){
				seguidor.atacar();
				seguidor.setVaiAtacar(false);
				if(seguidor.getTraco() == Traco.ATAQUEDUPLO && seguidor.getGetVezesQueVaiAtacar() == 2 && !seguidor.isMorreu()){
					seguidor.atacar();
				}
			}
		}
	}

	public void colocarCartaMesa(Jogador jogador, Seguidor carta, int posicao_alocacao) throws ManaInsuficienteException, PosicaoMesaOcupadaException{
		if (jogador.equals(jogador1)){
			cartas_mesa1.add(posicao_alocacao-1, carta);
		} else{
			cartas_mesa2.add(posicao_alocacao-1,carta);
		}
		this.verificarCondicoes();
	}
	// PARTES

	public boolean realizarParte0() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		this.parte_da_rodada = 0;
		this.verificarCondicoes();
		return this.atacante.atacar();
	}

	public void realizarParte1() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		this.parte_da_rodada = 1;
		this.verificarCondicoes();
		defensor.defender();
		this.parte_da_rodada = 2;
		this.verificarCondicoes();
	}

	public void realizarParte2() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		this.parte_da_rodada = 3;
		this.realizarCombates();
		this.verificarCondicoes();
		this.defensor.desarmarDefesa();
	}

	// PRINCIPAL

	public boolean passarRodada() throws ManaInsuficienteException, PosicaoMesaOcupadaException {
		this.rodada++;
		this.aumentarMana();
		this.trocarMarcacoes();

		boolean atacou = this.realizarParte0();
		if (atacou){
			this.realizarParte1();
			this.realizarParte2();
		}
		return this.continuar;
	}

	public int numCartasMesa(Jogador jogador){
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
			if (carta == null){//se houver posicao vazia
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
	
}



