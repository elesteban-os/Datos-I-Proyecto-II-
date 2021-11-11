package src;

import src.ui.interfac;
import java.io.IOException;

/**
 * Clase que abre un nuevo cliente
 */
public class openClient {
    public static void main(String[] args) throws IOException {
        interfac interfaz = new interfac();
        interfaz.setThisInterfaz(interfaz);
    }
}
