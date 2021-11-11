import java.io.IOException;
import java.util.ArrayList;

public class maincsv {
    public static void main(String[] args){
        try {
            csvReader cc = new csvReader();
            //ArrayList<String> prueba = cc.read();
           // System.out.println(prueba.get(6));
            cc.write("cxv", "xcv", "tvc", "xcv");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
