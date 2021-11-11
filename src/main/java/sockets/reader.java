package sockets;

import ui.interfac;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.StringTokenizer;

public class reader implements Runnable {
    private final DataInputStream input;
    private boolean isReading = true;
    private interfac uInterface;


    public void setWindow(interfac uInterface) {
        this.uInterface = uInterface;
    }

    /**
     * Constructor de la clase.
     * @param input permite recibir contenido de un socket
     */
    public reader(DataInputStream input, interfac ui){
        this.input = input;
        this.uInterface = ui;
    }

    public reader(DataInputStream input){
        this.input = input;
    }

    /**
     * Lee un mensaje del socket del servidor.
     * @return mensaje.
     * @throws IOException
     */
    public String read() throws IOException {
        String message = this.input.readUTF();
        System.out.println(message);
        return message;
    }

    /**
     * Función que ejecuta el thread para la constante escucha del socket del servidor.
     */
    public void run(){
        String message = "";
        while (isReading){
            try { message = read(); } catch (IOException e){ this.isReading = false; }

            StringTokenizer action = new StringTokenizer(message);

            switch(action.nextToken()){
                case "record":
                    if(action.hasMoreTokens()){
                        String record = action.nextToken();
                        System.out.println(record);
                        this.uInterface.setRecord(record);
                    } else {
                        this.uInterface.setRecord("onlyTable");
                    }
                    break;
                case "table":
                    System.out.println("entre a table");
                    String name = action.nextToken();
                    String oper = action.nextToken();
                    String date = action.nextToken();
                    String result = action.nextToken();
                    this.uInterface.appendTable(name, oper, date, result);
                    break;
                default:
                    this.uInterface.setResult(message);
                    break;
            }


        }
    }
}

