package src.sockets;

import src.ui.interfac;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class reader implements Runnable {
    private final DataInputStream input;
    private String toReturn;
    private boolean isReading = true;
    private interfac uInterface;
    private boolean ready = false;


    public void setWindow(interfac uInterface) {
        this.uInterface = uInterface;
    }

    /**
     *
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
     * @return retorna un String
     */
    public String getToReturn(){
        return this.toReturn;
    }


    public String read() throws IOException {
        String message = this.input.readUTF();
        System.out.println(message);
        return message;
    }

    public void run(){
        String arg = "";
        String message = "";
        ArrayList<String> tokens;
        while (isReading){
            try { message = read(); } catch (IOException e){ this.isReading = false; }

            StringTokenizer action = new StringTokenizer(message);

            switch(action.nextToken()){
                case "record":
                    this.uInterface.setRecord(action.nextToken());
                    break;
                default:
                    this.uInterface.setResult(message);
                    break;
            }


        }
    }
}
