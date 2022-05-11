package drugiepodejscie;

import java.util.*;

public class KMeans {

    List<Record> data = new ArrayList<>();
    List<Cluster> clusters = new ArrayList<>();
    Map<Cluster, List<Record>> clusterRecords = new LinkedHashMap<>();
    LinkedHashMap<Cluster, ArrayList<Record>> tempMap;
    List<Record> tempListOfRecords;

    public static void main(String[] args) {

        KMeans kMeans = new KMeans();
        kMeans.generateRecord();
        kMeans.normalizationOfVectors();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the parameter k: ");
        int clusterNumber = scanner.nextInt();

        // 1 wersja
        //kMeans.first(clusterNumber);                          //biorę pierwsze k rekordów i ustawiam je jako centroidy

        // 2 wersja
        kMeans.setClusterNumber(clusterNumber);                 //metoda która losowo ustawia centroid dla każdego rekordu
        kMeans.calculateAndSetFirstCentroids(clusterNumber);    //metoda która za pierwszym razem oblicza centroidy losowo dodanych rekordów

        for (int i = 1; i <= 10; i++) {
            kMeans.initiateClusterAndCentroid();

            System.out.println("Iteration number: " + i);
            for (Map.Entry<Cluster, ArrayList<Record>> entry : kMeans.tempMap.entrySet()) {
                double sumOfTheDistances = 0;
                for (int j = 0; j < entry.getValue().size(); j++)
                    //sumOfTheDistances += entry.getKey().calculateDistance(entry.getValue().get(j));
                    //sumOfTheDistances += Math.pow(entry.getKey().calculateDistanceWithoutSqrt(entry.getValue().get(j)), 2);
                    sumOfTheDistances += entry.getKey().calculateDistanceWithoutSqrt(entry.getValue().get(j));
                System.out.println("Distance from the centroid nr: " + entry.getKey().getClusterNumber() + " = " + sumOfTheDistances);
            }

        }

        kMeans.cleanliness(kMeans.tempMap);


    }

    private void generateRecord() {
        //data = ReadClass.readFile("/Users/maciejmaksymiuk/PJATKsemestrIV/NAI/NAI---projekty/NAI_mpp04/mpp4/danetestowewlasne.csv");
        data = ReadClass.readFile("/Users/maciejmaksymiuk/PJATKsemestrIV/NAI/NAI---projekty/NAI_mpp04/mpp4/iris_all.csv");
        //data = ReadClass.readFile("/Users/maciejmaksymiuk/PJATKsemestrIV/NAI/NAI---projekty/NAI_mpp04/mpp4/dane_testowe.csv");
    }

    private void normalizationOfVectors() {
        for (int i = 0; i < data.size(); i++) {
            double vectorLength = 0;
            for (int j = 0; j < data.get(i).getParameters().length; j++)
                vectorLength += Math.pow(data.get(i).getParameters()[j], 2);
            vectorLength = Math.sqrt(vectorLength);
            double[] normalizedVector = new double[data.get(i).getParameters().length];
            for (int j = 0; j < normalizedVector.length; j++)
                normalizedVector[j] = data.get(i).getParameters()[j] / vectorLength;

            data.get(i).setParameters(normalizedVector);
        }
    }

    //==================================================================================================================
    //wersja z 3 pierwszymi rekordami jako centroidy
    private void first(int k) {
        for (int i = 0; i < k; i++) {
            Cluster cluster = new Cluster(i + 1, data.get(i).getParameters());
            clusters.add(cluster);
            List<Record> clusterRecord = new ArrayList<>();
            //clusterRecord.add(data.get(i));
            clusterRecords.put(cluster, clusterRecord);
        }
    }

    // wersja z losowo przyporządkowanymi centroidami dla rekordów
    public void setClusterNumber(int k) {
        Random random = new Random();
        for (int i = 0; i < data.size(); i++)
            data.get(i).setClusterNumber(random.nextInt(k) + 1);
    }

    public void calculateAndSetFirstCentroids(int clusterNumber) {
        double[] parameters;
        Cluster cluster;
        for (int i = 1; i <= clusterNumber; i++) {
            parameters = new double[data.get(i).getParameters().length];
            clusters.add(cluster = new Cluster(i, parameters));
            ArrayList<Record> list = new ArrayList<>();
            clusterRecords.put(cluster, list);
        }

        for (int i = 0; i < data.size(); i++)
            for (Map.Entry<Cluster, List<Record>> entry : clusterRecords.entrySet()) {
                if (entry.getKey().getClusterNumber() == data.get(i).getClusterNumber()) {
                    entry.getKey().updateCentroid(data.get(i));
                    entry.getValue().add(data.get(i));
                    break;
                }
            }
    }
    //==================================================================================================================

    int i = 1;
    private void initiateClusterAndCentroid() {

        Iterator<Record> iterator = data.iterator();
        Record record = null;
        tempMap = new LinkedHashMap<>();
        tempListOfRecords = new ArrayList<>();

        while (iterator.hasNext()) {
            record = iterator.next();

            double minDistance = Integer.MAX_VALUE;
            Cluster whichCluster = null;

            for (Cluster cluster : clusters) {
                double distance = cluster.calculateDistance(record);
                if (minDistance > distance) {
                    minDistance = distance;
                    whichCluster = cluster;
                }
            }
            record.setClusterNumber(whichCluster.getClusterNumber()); // ustawiam nowy numer klastra dla rekordu
            //whichCluster.updateCentroid(record);
            clusters.get(whichCluster.getClusterNumber() - 1).updateCentroid(record); //zmieniam wartość danego centroida

            //wrzucam kazdy rekord do tymczasowej mapy
            tempListOfRecords.add(record);
        }
        //ustawianie mapy klastrów
        ArrayList<Record> list;
        for (int i = 0; i < clusters.size(); i++) {
            list = new ArrayList<>();
            tempMap.put(clusters.get(i), list);
        }

        for (int i = 0; i < tempListOfRecords.size(); i++) {
            for (Map.Entry<Cluster, ArrayList<Record>> entry : tempMap.entrySet()) {
                if (entry.getKey().getClusterNumber() == tempListOfRecords.get(i).getClusterNumber()) {
                    entry.getValue().add(tempListOfRecords.get(i));
                    break;
                }
            }
        }


//        System.out.println("Iteration number: " + i++);
//        for (Map.Entry<Cluster, ArrayList<Record>> entry : tempMap.entrySet()) {
//            double sumOfTheDistances = 0;
//            for (int j = 0; j < entry.getValue().size(); j++)
//                sumOfTheDistances += entry.getKey().calculateDistance(entry.getValue().get(j));
//            //sumOfTheDistances += Math.pow(entry.getKey().calculateDistanceWithoutSqrt(entry.getValue().get(j)), 2);
//            //sumOfTheDistances += entry.getKey().calculateDistanceWithoutSqrt(entry.getValue().get(j));
//            System.out.println("Distance from the centroid nr: " + entry.getKey().getClusterNumber() + " = " + sumOfTheDistances);
//        }


    }

    public void cleanliness(LinkedHashMap<Cluster, ArrayList<Record>> centroids) {
        double numberOfSetosa, numberOfVersicolor, numberOfVirginica;

        for (Map.Entry<Cluster, ArrayList<Record>> entry : centroids.entrySet()) {
            System.out.println("Cluster nr: " + entry.getKey().getClusterNumber());
            System.out.println("Number of records: " + entry.getValue().size());
            numberOfSetosa = 0;
            numberOfVersicolor = 0;
            numberOfVirginica = 0;

            for (int i = 0; i < entry.getValue().size(); i++) {
                System.out.println(entry.getValue().get(i));
                if (entry.getValue().get(i).getName().equals("\"setosa\""))
                    numberOfSetosa++;
                if (entry.getValue().get(i).getName().equals("\"versicolor\""))
                    numberOfVersicolor++;
                if (entry.getValue().get(i).getName().equals("\"virginica\""))
                    numberOfVirginica++;
            }

            double measureOfPurityOfSetosa = (numberOfSetosa / entry.getValue().size()) * 100;
            double measureOfPurityOfVersicolor = (numberOfVersicolor / entry.getValue().size()) * 100;
            double measureOfPurityOfVirginica = (numberOfVirginica / entry.getValue().size()) * 100;

            System.out.println("setosa --> " + measureOfPurityOfSetosa + "%");
            System.out.println("versicolor --> " + measureOfPurityOfVersicolor + "%");
            System.out.println("virginica --> " + measureOfPurityOfVirginica + "%");

            System.out.println("========================================================================================================================");
        }
    }


}
