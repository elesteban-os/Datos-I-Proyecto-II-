package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import ui.interfac;

public class Client {
    private static final Client Client = null;
    Socket socket;
    String id;
    reader Reader;
    sender Sender;
    interfac window;

    public interfac getWindow() {
        return window;
    }

    public void setWindow(interfac window) {
        this.window = window;
        this.Reader.setWindow(window);
    }

    public Client(interfac ui) throws IOException{
        joinServer();
        startClient();
        getID();
        this.window = ui;
    }

    public void joinServer() throws IOException {
        this.socket = new Socket("localhost",2121);

    }

    public void startClient() throws IOException {
        this.Reader = new reader(new DataInputStream(this.socket.getInputStream()), this.window);
        this.Sender = new sender(new DataOutputStream(this.socket.getOutputStream()));
    }

    public void getID() throws IOException{
        DataInputStream input = new DataInputStream(this.socket.getInputStream());
        this.id = input.readUTF();
        new Thread(this.Reader).start();
        this.Sender.startSender("Soy cliente " + this.id);
        System.out.println("ID de cliente: " +this.id);
    }

    public void sendMessage(String message) throws IOException{
        this.Sender.startSender(message);
    }

    public static void main(String[] args) throws IOException{
        //Client cliente = new Client();
    }

    public void setWindowOnReader(){
        this.Reader.setWindow(window);
    }
}