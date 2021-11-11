import sockets.Client;
import ui.interfac;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        interfac interfaz = new interfac();
        Client client = new Client(interfaz);
        //while
        interfaz.setClient(client);
        client.setWindow(interfaz);
        client.setWindowOnReader();
    }
}
