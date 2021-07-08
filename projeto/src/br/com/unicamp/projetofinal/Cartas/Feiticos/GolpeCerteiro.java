package br.com.unicamp.projetofinal.Cartas.Feiticos;
import br.com.unicamp.projetofinal.Cartas.Feitico;
import br.com.unicamp.projetofinal.Cartas.Seguidor;
import br.com.unicamp.projetofinal.GerenciadorEfeitos;
import br.com.unicamp.projetofinal.Jogador;
import br.com.unicamp.projetofinal.Mesa;
import br.com.unicamp.projetofinal.PrintFactory;

/*
*
* Custo de mana: 1
* Concede 1|1 para um jogador nessa rodada
*
*
*
* */


public class GolpeCerteiro extends Feitico {
    private Seguidor alvo;
    private int rodada_criado;

    public GolpeCerteiro(Mesa mesa, Jogador jogador) {
        super("Golpe Certeiro", 1, mesa, jogador);
    }

    @Override
    public void verificarCondicao() {
        if (this.getMesa().getRodada() > rodada_criado){
            GerenciadorEfeitos.aumentarAtaqueVida(this.alvo, -1,-1);
            this.getMesa().removerFeiticoAtivo(this);
        }
    }

    @Override
    protected void realizarEfeito() {
        this.rodada_criado = this.getMesa().getRodada();
        int num_mesa = PrintFactory.pedirInput("Que carta quer dar 1/1 nessa rodada?");
        this.alvo = this.getMesa().getCartasMesa(this.getJogador()).get(num_mesa - 1);
        GerenciadorEfeitos.aumentarAtaqueVida(this.alvo, 1, 1);
        this.getMesa().addFeiticoAtivo(this);
    }

}