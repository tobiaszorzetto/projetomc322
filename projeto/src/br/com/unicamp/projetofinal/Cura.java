package br.com.unicamp.projetofinal;

import java.util.Scanner;

public class Cura extends Feitico{


    public Cura(Mesa mesa, Jogador jogador, GerenciadorEfeitos ge) {
        super("Cura", 3, mesa, jogador, ge);
    }

    @Override
    public void atuarNaMesa(Jogador jogador) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(this.getNome() + ", quais dessas cartas deseja curar?");

        while(true){

                String command = keyboard.nextLine();

                if (Integer.parseInt(command)<= this.getMesa().getCartasMesa(this.getJogador()).size()){
                    int numero_carta = Integer.parseInt(command) - 1;
                    this.getMesa().getCartasMesa(this.getJogador()).get(numero_carta).setVidaOriginal();
                    break;
                } else {
                    System.out.println("indice nao existente");
                }
        }
    }
}

