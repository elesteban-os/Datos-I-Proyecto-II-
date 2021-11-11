import sockets.Server;
import java.io.IOException;

/**
 * Clase que abre un servidor.
 */
public class openServer {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.runServer();
    }
}
