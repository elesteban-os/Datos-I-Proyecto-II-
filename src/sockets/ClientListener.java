package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientListener implements Runnable {
    public Socket client;
    DataOutputStream output;
    DataInputStream input;

    public ClientListener(Socket client) throws IOException {
        this.client = client;
        this.output = new DataOutputStream(client.getOutputStream());
        this.input = new DataInputStream(client.getInputStream());
    }


    public void run() {
        while (this.client.isConnected()) {
            try {
                String message = this.input.readUTF();
                System.out.println(message);
                this.output.writeUTF(message);
            } catch (IOException io){

            }

        }
    }

}
