import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SubjectOfStudy {

    private String species;
    private double[] parameters;//lista parametrów
    public static Map<String, Integer> speciesMap = new HashMap<>();



    public SubjectOfStudy(String species, double[] parameters) {
        this.species = species;
        this.parameters = parameters;

    }

    public String getSpecies() {
        return species;
    }

    public double[] getParameters() {
        return parameters;
    }

    public static ArrayList<SubjectOfStudy> readFile(String path) {
        ArrayList<SubjectOfStudy> objectList = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] data = line.split(",");
                double[] values = new double[data.length - 1];
                for (int i = 0; i < values.length; i++) {
                    values[i] = Double.parseDouble(data[i].trim());
                }
                objectList.add(new SubjectOfStudy(data[data.length - 1], values));
                speciesMap.put(data[data.length - 1], null);
            }
            int i = 0;
            for (Map.Entry<String, Integer> species : speciesMap.entrySet())
                species.setValue(i++);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.parameters.length; i++) {
            sb.append(parameters[i] + " ");
        }
        return species + " " + sb;
    }

}
