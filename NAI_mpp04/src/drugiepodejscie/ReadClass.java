package drugiepodejscie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadClass {

    public static ArrayList<Record> readFile(String path) {
        ArrayList<Record> listOfSubject = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                double[] values = new double[data.length - 1];
                String nameOfSubject = data[data.length - 1];
                for (int i = 0; i < values.length; i++)
                    values[i] = Double.parseDouble(data[i]);
                listOfSubject.add(new Record(nameOfSubject, values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfSubject;
    }

}
