package src.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import src.sockets.Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import src.Calculator;

/**
 * Clase que procesa las características de la interfaz.
 */
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
    private JTable table;
    private DefaultTableModel writeTable;
    private interfac thisInterfaz;
    private Client client;
    public void setThisInterfaz(interfac thisInterfaz) {
        this.thisInterfaz = thisInterfaz;
    }

    /**
     * Función que escucha al botón de calcular.
     */
    ActionListener escuchador = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
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

    /**
     * Función que escucha al botón de entrar.
     */
    ActionListener enter = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            String named = name.getText();
            try {
                client = new Client(thisInterfaz, named);
            } catch (IOException io) {
                io.printStackTrace();
            }
            calcular.setEnabled(true);
            entrar.setEnabled(false);
            operation.setEnabled(true);
            name.setEnabled(false);
        }
    };

    /**
     * Función que agrega una nueva fila a la tabla.
     * @param name nombre
     * @param oper operacion
     * @param date fecha
     * @param result resultado
     */
    public void appendTable(String name, String oper, String date, String result) {
        System.out.println("enttre a apen" + name + "7");
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.addRow(new Object[]{name, oper, date, result});
    }

    /**
     * Función que genera una tabla a partir del texto que el servidor envía.
     * @param message
     */
    public void setRecord(String message) {
        System.out.println("si");
        if (message.equals("onlyTable")){
            String[][] data = new String[1][4];
            String[] colums = {"Nombre", "Operación", "Fecha", "Resultado"};

            data[0][0] = "Nombre";
            data[0][1] = "Operación";
            data[0][2] = "Fecha";
            data[0][3] = "Resultado";

            this.writeTable = new DefaultTableModel(data, colums);
            this.table = new JTable(this.writeTable);
            this.table.setBounds(200, 100, 320, 370);
            this.window.add(this.table);
            this.window.repaint();
        } else{
            String[] operations = message.split(";");

            System.out.println(operations.length);

            String[][] data = new String[(operations.length / 4) + 1][4];
            String[] colums = {"Nombre", "Operación", "Fecha", "Resultado"};

            data[0][0] = "Nombre";
            data[0][1] = "Operación";
            data[0][2] = "Fecha";
            data[0][3] = "Resultado";

            int j = 1;
            int k = 0;
            for (int i = 0; i < operations.length; i++) {
                data[j][k] = operations[i];
                if (k == 3) {
                    j++;
                    k = 0;
                } else {
                    k++;
                }
            }
            this.writeTable = new DefaultTableModel(data, colums);
            this.table = new JTable(this.writeTable);
            this.table.setBounds(200, 100, 320, 370);
            this.window.add(this.table);
            this.window.repaint();
        }
    }


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

    /**
     * Función que coloca el resultado de la operación en el label del resultado.
     * @param result resultado de la operación
     */
    public void setResult(String result) {
        this.lResult.setText(result);
    }

}