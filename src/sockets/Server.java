package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;

public class Server {
    ServerSocket server;
    Socket serverListener;
    ArrayList<Socket> clients;
    int clientsIDs;
    //Reader reader;
    //Sender sender;
    //Calculator calculate;
    //FileManager file;


    public void runServer() throws IOException{
        this.server = new ServerSocket(2121);
        this.serverListener = new Socket("localhost", 2121);

        //this.reader = new Reader(serverListener);
        //this.sender = new sender(serverListener);

    }

    public void newClient() throws IOException {
        while(!this.server.isClosed()){
            Socket client = this.server.accept();
            this.clients.add(client);
            System.out.println("Nuevo cliente conectado, es el #" + "");
            assignID(client);
        }
    }

    public void assignID(Socket client) throws IOException {
        DataOutputStream outputClient = new DataOutputStream(client.getOutputStream());
        outputClient.writeInt(this.clientsIDs);
        this.clientsIDs++;
    }
}