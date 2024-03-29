package sockets;

import ui.Calculator;
import ui.csvReader;
import tree.ExpressionTree;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Clase que funciona como trabajador de un único cliente.
 */
public class ClientListener implements Runnable {
    public Socket client;
    private Calculator calculator = new Calculator();
    ExpressionTree tree = new ExpressionTree();
    private csvReader filer = new csvReader();
    DataOutputStream output;
    DataInputStream input;
    String name;
    boolean open = true;

    /**
     * Constructor de la clase.
     * @param client cliente al que se le quiere escuchar y responder.
     * @param name nombre del cliente.
     * @throws IOException
     */
    public ClientListener(Socket client, String name) throws IOException {
        this.client = client;
        this.output = new DataOutputStream(client.getOutputStream());
        this.input = new DataInputStream(client.getInputStream());
        this.name = name;
    }

    /**
     * Función que ejecuta un hilo para la constante escucha y escritura de los sockets.
     */
    public void run() {
        while (open) {
            String message = "";
            StringTokenizer action;
            try {
                message = this.input.readUTF();
                System.out.println("leo " + message);
            } catch (IOException io){
                this.open = false;
            }

            if (message != ""){
                action = new StringTokenizer(message);
                switch(action.nextToken()){
                    case "operation":
                        String operation = action.nextToken();
                        System.out.println("Solve: " + operation);
                        String postfix = this.calculator.getPostfix(operation);
                        System.out.println("Postfix: " + postfix);
                        tree.create(postfix);
                        int result = tree.getResult();
                        try {
                            this.output.writeUTF(String.valueOf(result));
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            this.filer.write(this.name, operation, dtf.format(LocalDateTime.now()), String.valueOf(result));
                            this.output.writeUTF("table" + " " + this.name + " " + operation + " " +
                                    dtf.format(LocalDateTime.now()) + " " + String.valueOf(result));
                        } catch (IOException io) {
                            io.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
