package br.com.unicamp.projetofinal;

import javax.swing.*;

public class Janela extends JFrame {

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
    private Mesa mesa;
    public Janela(String titulo, Mesa mesa){
        super(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(painel);
        this.pack();
        this.mesa = mesa;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
