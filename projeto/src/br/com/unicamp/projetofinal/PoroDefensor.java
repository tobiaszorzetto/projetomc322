package br.com.unicamp.projetofinal;

public class PoroDefensor extends Seguidor{

    public PoroDefensor(Mesa mesa, Jogador jogador, GerenciadorEfeitos ge) {
        super("Poro Defensor", 1, 1, 2, mesa, jogador, ge);
    }

    @Override
    public void verificarCondicao() {

    }

    @Override
    public void matarSeguidor(){
        this.getMesa().getCartasMesa(this.getJogador()).remove(this);

        this.getJogador().sortearDoDeck();
        //pensar num jeito aqui
        this.getMesa().verificarCondicoes();
    }
}
