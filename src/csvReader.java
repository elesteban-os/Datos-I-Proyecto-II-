import tree.StackList;

import java.io.*;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.Stack;

public class csvReader {
    private BufferedReader reader;
    private String path = "src\\record.csv";

    public String[] readLine(String line){
        String[] characters = line.split("");
        String[] result = new String[3];
        String temp = "";
        int k = 0;
        for(int i = 0; i < characters.length; i++){
            switch (characters[i]){
                case ";":
                    if (!temp.equals("")) {
                        result[k] = temp;
                        temp = "";
                        k++;
                    }
                    break;
                default:
                    temp = temp + characters[i];
            }
        }
        return result;
    }

    public ArrayList read() throws IOException{
        this.reader = new BufferedReader(new FileReader(this.path));

        ArrayList<String> data = new ArrayList<>();
        String line = this.reader.readLine();

        while (line != null){
            System.out.println(line);
            String[] thisLine = readLine(line);
            for (int i = 0; i < 3; i++){
                //System.out.println(thisLine[i]);
                data.add(thisLine[i]);
            }
            line = this.reader.readLine();
        }

        return data;
    }

    public void write(String id, String operation, String date, String result) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src\\record.csv"));
        StringBuilder data = new StringBuilder();
        ArrayList<String> Data = new ArrayList<>();
        String line = reader.readLine();
        System.out.println(line);
        while (line != null){
            System.out.println(line);
            Data.add(line);
            line = reader.readLine();
        }

        FileWriter writer = new FileWriter(this.path);

        for (int i = 0; i < Data.size(); i++){
            System.out.println(Data.get(i));
            writer.append(Data.get(i));
            writer.append("\n");
        }

        data.append(id);
        data.append(";");
        data.append(operation);
        data.append(";");
        data.append(date);
        data.append(";");
        data.append(result);

        writer.append(data.toString());
        writer.flush();
        writer.close();
    }



}
