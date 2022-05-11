import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) {

        ArrayList<Language> listOfLanguage = Tools.readFile("/Users/maciejmaksymiuk/PJATKsemestrIV/NAI/NAI---projekty/NAI_mpp03/mpp3/trening");
        ArrayList<Perceptron> perceptrons = new ArrayList<>();

        long start = System.currentTimeMillis();
        for (Language lang : listOfLanguage) {
            Perceptron perceptron = new Perceptron(lang.getNameOfLanguage());
            perceptron.learn(listOfLanguage);
            perceptrons.add(perceptron);
        }
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Trained in: " + elapsedTime / 1000F);


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        String[] tokens;
        StringBuilder stringBuilder = new StringBuilder();
        String finaltext = "";
        boolean stop = true;
        try {
            while ((line = br.readLine()) != null && stop){
                if (line.equals("xxx"))
                    stop = false;
                stringBuilder.append(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finaltext = stringBuilder.toString();
        String finalText = finaltext.replaceAll("[^a-zA-Z]", "");




    }
}
