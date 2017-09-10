package lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Alexey on 11.09.2017.
 */
public class OutputUtils {

    public static void printFile(String filename, List<List<Double>> data){
        try{
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            for (int i = 0; i < data.get(0).size(); i++){
                for (int j =0; j < data.size(); j++){
                    writer.print(data.get(j).get(i));
                    writer.print(" ");
                }
                writer.println();
            }
            writer.close();
        } catch (IOException e) {
            // do something
        }
    }
}
