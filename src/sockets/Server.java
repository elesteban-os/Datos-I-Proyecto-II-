package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
    ServerSocket server;
    Socket serverListener;
    ArrayList<Socket> clients = new ArrayList<>();
    int clientsIDs;
    reader Reader;
    sender Sender;
    DataInputStream input;
    DataOutputStream output;
    //Calculator calculate;
    //FileManager file;


    public void runServer() throws IOException {
        this.server = new ServerSocket(2121);
        newClient();
    }

    public void newClient() throws IOException {
        while(!this.server.isClosed()){
            Socket client = this.server.accept();
            this.clients.add(client);
            ClientListener listener = new ClientListener(client);
            new Thread(listener).start();
            assignID(client);
            System.out.println("Nuevo cliente conectado, es el #" + (this.clientsIDs - 1));
        }
    }

    public void assignID(Socket client) throws IOException {
        DataOutputStream outputClient = new DataOutputStream(client.getOutputStream());
        outputClient.writeUTF(String.valueOf(this.clientsIDs));
        this.clientsIDs++;
    }

    public void sendMessageTo(String message, String id) throws IOException{
        Socket client = this.clients.get(Integer.parseInt(id));
        DataOutputStream clientDO = new DataOutputStream(client.getOutputStream());
        this.Sender.startSender(message, clientDO);
    }


    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.runServer();
    }

}