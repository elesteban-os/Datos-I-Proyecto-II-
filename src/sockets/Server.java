package src.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import src.csvReader;

/**
 * Clase que ejecuta un servidor.
 */
public class Server {
    ServerSocket server;
    ArrayList<Socket> clients = new ArrayList<>();
    int clientsIDs;
    csvReader file = new csvReader();

    /**
     * Función que ejecuta un servidor.
     * @throws IOException excepción
     */
    public void runServer() throws IOException {
        this.server = new ServerSocket(2121);
        newClient();
    }

    /**
     * Función que lee un cliente.
     * @param name nombre del cliente.
     * @return mensaje descifrado.
     * @throws IOException excepción.
     */
    public String readClient(String name) throws IOException {
        ArrayList<String> read = file.read();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < read.size(); i++) {
            if (read.get(i).equals(name)) {
                int k = i + 4;
                while (i < k){
                    sb.append(read.get(i));
                    sb.append(";");
                    i++;
                }
                i--;
            }
        }
        return sb.toString();
    }

    /**
     * Función que prepara a un cliente nuevo que se una al servidor.
     * @throws IOException excepción
     */
    public void newClient() throws IOException {
        while(!this.server.isClosed()){
            Socket client = this.server.accept();
            this.clients.add(client);
            assignID(client);
            String name = getName(client);
            System.out.println(name);
            System.out.println(readClient(name));
            DataOutputStream temp = new DataOutputStream(client.getOutputStream());
            temp.writeUTF("record" + " " + readClient(name) + ";");
            ClientListener listener = new ClientListener(client, name);
            new Thread(listener).start();
            System.out.println("Nuevo cliente conectado, es el #" + (this.clientsIDs - 1));
        }
    }

    /**
     * Función que obtiene el nombre del cliente.
     * @param client cliente
     * @return nombre del cliente
     * @throws IOException excepción
     */
    public String getName(Socket client) throws IOException{
        DataInputStream inputClient = new DataInputStream(client.getInputStream());
        String name = inputClient.readUTF();
        return name;
    }

    /**
     * Función que le asigna una identificación a un cliente
     * @param client cliente
     * @throws IOException excepción
     */
    public void assignID(Socket client) throws IOException {
        DataOutputStream outputClient = new DataOutputStream(client.getOutputStream());
        outputClient.writeUTF(String.valueOf(this.clientsIDs));
        this.clientsIDs++;
    }
}