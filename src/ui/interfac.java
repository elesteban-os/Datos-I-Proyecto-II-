package src.ui;

import javax.swing.*;

import src.sockets.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import src.Calculator;

public class interfac {

    private Calculator calculator = new Calculator();

    private JFrame window = new JFrame("Calculadora");
    private JButton calcular = new JButton("Calcular");

    private JTextField operation = new JTextField();

    private JLabel lOperation = new JLabel("Operación");
    private JLabel lResultText = new JLabel("Resultado: ");
    private JLabel lResult = new JLabel("0");

    private JTextField name = new JTextField();
    private JLabel lName = new JLabel("Nombre");
    private JButton entrar = new JButton("Unirse");

    //private JTable

    private interfac thisInterfaz;
    private String named = "";
    private Client client;

    public interfac getThisInterfaz() {
        return thisInterfaz;
    }

    public void setThisInterfaz(interfac thisInterfaz) {
        this.thisInterfaz = thisInterfaz;
    }

    public String getNamed() {
        return named;
    }

    public void setNamed(String named) {
        this.named = named;
    }
    ActionListener letters = new ActionListener(){
        public void actionPerformed(ActionEvent event){
        /* String words = operation.getText();
        for(int i = 0; i != words.length(); i++){
            char c = words.charAt(i);
            if(Character.isLetter(c)){
                words.;
            }
        } */
        }
    };

    ActionListener escuchador = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent event){
            try {
                String expression = operation.getText();
                if (calculator.validate(expression)) {
                    client.sendMessage("operation " + expression);
                }
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    };

    ActionListener enter = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent event){
            String named = name.getText();
            try {
                client = new Client(thisInterfaz, named);
            } catch (IOException io){
                io.printStackTrace();
            }
            calcular.setEnabled(true);
            entrar.setEnabled(false);
            operation.setEnabled(true);
            name.setEnabled(false);
        }
    };


    /**
     * Constructor que implementa todas las características de la interfaz.
     */
    public interfac() {
        System.out.println();


        // Botón
        calcular.setBounds(50, 300, 100, 30);
        calcular.addActionListener(escuchador);
        calcular.setEnabled(false);
        entrar.setBounds(410, 20, 100, 30);
        entrar.addActionListener(enter);

        // Cuadros de texto
        operation.setBounds(50, 100, 100, 30);
        operation.setEnabled(false);
        name.setBounds(300, 20, 100, 30);

        // Labels
        lOperation.setBounds(50, 80, 100, 10);
        lResult.setBounds(50, 170, 100, 10);
        lResultText.setBounds(50, 150, 100, 10);
        lName.setBounds(300, 8, 100, 10);

        operation.addActionListener(letters);

        window.add(operation);
        window.add(lOperation);
        window.add(calcular);
        window.add(lResult);
        window.add(lResultText);
        window.add(name);
        window.add(lName);
        window.add(entrar);

        window.setSize(600, 400);
        window.setLayout(null);
        window.setResizable(false);
        window.setVisible(true);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public void setResult(String result) {
        this.lResult.setText(result);
    }


    private class Interfac {
    }
}