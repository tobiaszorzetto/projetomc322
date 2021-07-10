package br.com.unicamp.projetofinal;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Janela extends JFrame {

    static int valor;
    static String valor_string;

    JPanel painel;
    private JTextField textField1;
    private JLabel pos_1_1Label;
    private JLabel pos_2_1Label;
    private JLabel pos_3_1Label;
    private JLabel pos_4_1Label;
    private JLabel pos_5_1Label;
    private JLabel pos_6_1Label;

    private JLabel pos_1_2Label;
    private JLabel pos_2_2Label;
    private JLabel pos_3_2Label;
    private JLabel pos_4_2Label;
    private JLabel pos_5_2Label;
    private JLabel pos_6_2Label;

    static ArrayList<JLabel> labels_1 = new ArrayList<JLabel>();
    static ArrayList<JLabel> labels_2 = new ArrayList<JLabel>();
    private JLabel pedir_inputLabel;
    private JLabel avisoLabel;
    private JLabel cartas_maoLabel;
    static Mesa mesa;
    public Janela(String titulo, Mesa mesa){
        super(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(painel);
        this.pack();
        Janela.mesa = mesa;
        addLabels_1();
        addLabels_2();
    }
    public void addLabels_1(){
        labels_1.add(pos_1_1Label);
        labels_1.add(pos_2_1Label);
        labels_1.add(pos_3_1Label);
        labels_1.add(pos_4_1Label);
        labels_1.add(pos_5_1Label);
        labels_1.add(pos_6_1Label);
    }

    public void addLabels_2(){
        labels_2.add(pos_1_2Label);
        labels_2.add(pos_2_2Label);
        labels_2.add(pos_3_2Label);
        labels_2.add(pos_4_2Label);
        labels_2.add(pos_5_2Label);
        labels_2.add(pos_6_2Label);
    }


    public int pedirInput(String mensagem){
        pedir_inputLabel.setText(mensagem);
        textField1.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
              super.keyTyped(e);
              if(e.getKeyChar() == '\n'){
                  Janela.valor = Integer.parseInt(textField1.getText());
              }


          }
      });
        return Janela.valor;
    }

    public String pedirInputString(String mensagem){
        pedir_inputLabel.setText(mensagem);
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyChar() == '\n'){
                    Janela.valor_string = textField1.getText();
                }

            }
        });
        return Janela.valor_string;
    }

    public void atualizarTela(){
        for(int i = 0; i< 6; i++){
            if(mesa.getCartas_mesa1().get(i)!=null)
                labels_1.get(i).setText(mesa.getCartas_mesa1().get(i).getNome());
            else
                labels_1.get(i).setText("-----");
            if(mesa.getCartas_mesa2().get(i)!=null)
                labels_2.get(i).setText(mesa.getCartas_mesa2().get(i).getNome());
            else
                labels_2.get(i).setText("-----");
        }
    }
    
    public void trocarAviso(String mensagem){
        avisoLabel.setText(mensagem);
    }

    public static void mostrarCartasMao(Jogador jogador){

    }
}0
