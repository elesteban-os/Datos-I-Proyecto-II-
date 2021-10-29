package sockets;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    Socket socket;
    int id;
    //Reader reader;
    //Sender sender;
    //Window window;

    public Client() throws IOException{
        //this.window = window;
        joinServer();
        startClient();
        getID();
    }

    public void joinServer() throws IOException {
        this.socket = new Socket("localhost",2121);

    }

    public void startClient() throws IOException {
        //this.reader = new Reader(this.socket);
        //this.sender = new Sender(this.socket);
    }

    public void getID() throws IOException{
        DataInputStream input = new DataInputStream(this.socket.getInputStream());
        this.id = input.readInt();
    }
}
