package br.com.unicamp.projetofinal.Cartas.Campeoes;

import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.ManaInsuficienteException;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PosicaoMesaOcupadaException;
import br.com.unicamp.projetofinal.Cartas.Campeao;
import br.com.unicamp.projetofinal.Cartas.Seguidores.PoroDefensor;
import br.com.unicamp.projetofinal.Enums.Traco;

/*
*  4 / 0 / 5
*
*  A primeira vez que diminui vida -> evoca um poro
*
*  Todo inicio de rodada -> regenera a vida para a original
*
*  Traco -> Barreira
* */

public class Braum extends Campeao{
	
	int dano_tomado = 0;
	boolean ainda_nao_tomou_dano = true;
	
	public Braum(Mesa mesa, Jogador jogador) {
		super("Braum", 4, 0, 5, mesa, jogador);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checarLevelUp() {
		if(this.dano_tomado >=10){
            GerenciadorEfeitos.aumentarAtaqueVida(this,1,1);
            this.dano_tomado-=10;
        }
		
	}

	@Override
	public void verificarCondicao() throws ManaInsuficienteException {
		
		if(this.getMesa().getParteDaRodada() == 4){
	           this.setVidaOriginal();
	       }
	    this.checarLevelUp();
		
	    
	       
	}
	
	@Override
	public boolean diminuirVida(int quantidade) throws PosicaoMesaOcupadaException, ManaInsuficienteException{
		if(this.getTraco() == Traco.BARREIRA){
			this.setTraco(Traco.NENHUM);
			return false;
		}
		vida_atual -= quantidade;
		if (vida_atual <= 0){
			matarSeguidor();
			return true;
		}
		if(this.ainda_nao_tomou_dano) {
			GerenciadorEfeitos.evocarSeguidor(new PoroDefensor(this.getMesa(),this.getJogador()));
		}
		this.dano_tomado+=quantidade;
		
		return false;
	}

}
