package src.sockets;

import src.Calculator;
import src.tree.ExpressionTree;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ClientListener implements Runnable {
    public Socket client;
    private Calculator calculator = new Calculator();
    ExpressionTree tree = new ExpressionTree();
    DataOutputStream output;
    DataInputStream input;
    String name;

    public ClientListener(Socket client, String name) throws IOException {
        this.client = client;
        this.output = new DataOutputStream(client.getOutputStream());
        this.input = new DataInputStream(client.getInputStream());
        this.name = name;
    }


    public void run() {
        while (this.client.isConnected()) {
            String message = "";
            StringTokenizer action;
            try {
                message = this.input.readUTF();
                System.out.println("leo " + message);
            } catch (IOException io){
                this.client.close();
                io.printStackTrace();
            }


            if (message != ""){
                action = new StringTokenizer(message);
                switch(action.nextToken()){
                    case "operation":
                        String postfix = this.calculator.getPostfix(action.nextToken());
                        tree.create(postfix);
                        int result = tree.getResult();
                        try {
                            this.output.writeUTF(String.valueOf(result));
                        } catch (IOException io){ }
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
