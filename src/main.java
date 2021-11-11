package src;

import src.sockets.Client;
import src.ui.interfac;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        interfac interfaz = new interfac();
        interfaz.setThisInterfaz(interfaz);

        //Client client = new Client(interfaz);

        //interfaz.setClient(client);
        //client.setWindow(interfaz);
        //lient.setWindowOnReader();
    }
}
