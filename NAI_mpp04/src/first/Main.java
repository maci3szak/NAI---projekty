package first;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<Subject> listOfSubject = Reader.
                readFile("/Users/maciejmaksymiuk/PJATKsemestrIV/NAI/NAI---projekty/NAI_mpp04/mpp4/iris_all.csv");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj parametr k: ");
        int k = scanner.nextInt();
        System.out.println("Podaj liczbę iteracji: ");
        int iteration = scanner.nextInt();

        Algorithm algorithm = new Algorithm(k, listOfSubject);
        //losowy przydział centroidów
        algorithm.drawAGroup();
        //mapa = (centroid --> lista rekordów z danym centroidem)
        HashMap<Integer, ArrayList<Subject>> mapOfSubjectsInCentroids = algorithm.getMapOfCentroids();
        //mapa = (centroid --> jego punkt(współrzędne))
        HashMap<Integer, Subject> mapOfCentroids = algorithm.getCentroids(mapOfSubjectsInCentroids);
        //sprawdzanie odległości i zamiana centroidów na najbliższe
        ArrayList<Subject> list = algorithm.checkLengthToCentroid(mapOfCentroids,mapOfSubjectsInCentroids);
        //mapa = (centroid -> lista nowych wektorów po pierwszym sprawdzeniu)
        HashMap<Integer, ArrayList<Subject>> finalMap = algorithm.getMapOfCentroids(list,k);


        for (int i = 0; i < iteration; i++) {
            mapOfCentroids = algorithm.getCentroids(finalMap);
            list = algorithm.checkLengthToCentroid(mapOfCentroids,finalMap);
            finalMap = algorithm.getMapOfCentroids(list,k);
        }

        for (Map.Entry<Integer,ArrayList<Subject>> entry: finalMap.entrySet()) {
            System.out.println("centroid: " + entry.getKey());
            for (int i = 0; i < entry.getValue().size(); i++) {
                System.out.println(entry.getValue().get(i));
            }
            System.out.println("\n\n");
        }


    }
}
