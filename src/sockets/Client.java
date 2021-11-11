package src.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import src.ui.interfac;

/**
 * Clase que se conecta a un servidor y funciona como comunicador de mensajes y como implementación para la
 * interfaz gráfica.
 */
public class Client {
    private static final Client Client = null;
    Socket socket;
    String id;
    reader Reader;
    sender Sender;
    interfac window;
    String name;

    /**
     * Constructor de la clase cliente.
     * @param ui interfaz gráfica del cliente.
     * @param name nombre del usuario.
     * @throws IOException
     */
    public Client(interfac ui, String name) throws IOException {
        this.window = ui;
        this.name = name;
        joinServer();
        startClient();
        setWindowOnReader();
        getID();
    }

    /**
     * Función que se une al servidor de sockets.
     * @throws IOException
     */
    public void joinServer() throws IOException {
        this.socket = new Socket("localhost",2121);

    }

    /**
     * Función que inicializa el lector y el escritor para los sockets.
     * @throws IOException
     */
    public void startClient() throws IOException {
        this.Reader = new reader(new DataInputStream(this.socket.getInputStream()), this.window);
        this.Sender = new sender(new DataOutputStream(this.socket.getOutputStream()));
    }

    /**
     * Función que obtiene una identificación para el cliente.
     * @throws IOException
     */
    public void getID() throws IOException {
        DataInputStream input = new DataInputStream(this.socket.getInputStream());
        this.id = input.readUTF();
        new Thread(this.Reader).start();
        //this.Sender.startSender("Soy cliente " + this.id);
        this.Sender.startSender(this.name);
        System.out.println("ID de cliente: " +this.id);
    }

    /**
     * Función que envía un mensaje hacia el socket del servidor.
     * @param message mensaje.
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.Sender.startSender(message);
    }

    /**
     * Función que coloca la interfaz gráfica en la clase del lector.
     */
    public void setWindowOnReader(){
        this.Reader.setWindow(this.window);
    }
}
