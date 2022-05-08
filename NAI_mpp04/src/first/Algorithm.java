package first;

import java.util.*;

public class Algorithm {

    private int k;
    private ArrayList<Subject> list;

    public Algorithm(int k, ArrayList<Subject> list) {
        this.k = k;
        this.list = list;
    }

    public void drawAGroup() {
        Random random = new Random();
        int randomGroup = 0;
        for (int i = 0; i < list.size(); i++) {
            randomGroup = random.nextInt(k) + 1;
            list.get(i).setNumberOfGroup(randomGroup);
        }
    }

    //method that returns the centroid map
    public HashMap<Integer, ArrayList<Subject>> getMapOfCentroids() {
        HashMap<Integer, ArrayList<Subject>> mapOfCentroids = new HashMap<>();
        ArrayList<Subject> listOfTheSameObjects;
        for (int i = 1; i <= this.k; i++) {
            listOfTheSameObjects = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getNumberOfGroup() == i)
                    listOfTheSameObjects.add(list.get(j));
            }
            mapOfCentroids.put(i, listOfTheSameObjects);
        }
        return mapOfCentroids;
    }

    public HashMap<Integer, ArrayList<Subject>> getMapOfCentroids(ArrayList<Subject> list, int k) {
        HashMap<Integer, ArrayList<Subject>> mapOfCentroids = new HashMap<>();
        ArrayList<Subject> listOfTheSameObjects;
        for (int i = 1; i <= k; i++) {
            listOfTheSameObjects = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getNumberOfGroup() == i)
                    listOfTheSameObjects.add(list.get(j));
            }
            mapOfCentroids.put(i, listOfTheSameObjects);
        }
        return mapOfCentroids;
    }

    public HashMap<Integer, Subject> getCentroids(HashMap<Integer, ArrayList<Subject>> mapOf) {
        HashMap<Integer, Subject> mapOfCentroids = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<Subject>> entry : mapOf.entrySet()) {
            ArrayList<Subject> list = entry.getValue();
            double[] values = new double[4];
            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < list.size(); j++) {
                    values[i] += list.get(j).getVector()[i];
                }
                values[i] = values[i] / list.size();
            }
            Subject centroid = new Subject(null, values, entry.getKey());
            mapOfCentroids.put(entry.getKey(), centroid);
        }
        return mapOfCentroids;
    }

    public ArrayList<Subject> checkLengthToCentroid(HashMap<Integer, Subject> centroids, HashMap<Integer, ArrayList<Subject>> vectors) {
        ArrayList<Subject> finalListOfRecords = new ArrayList<>();
        double length, min = Integer.MAX_VALUE;
        int centroidKey = 0;
        for (Map.Entry<Integer, ArrayList<Subject>> vector : vectors.entrySet()) {
            for (int j = 0; j < vector.getValue().size(); j++) {
                for (Map.Entry<Integer, Subject> centroid : centroids.entrySet()) {
                    length = euclidesMeasure(vector.getValue().get(j), centroid.getValue());
                    if (length < min) {
                        min = length;
                        vector.getValue().get(j).setNumberOfGroup(centroid.getValue().getNumberOfGroup());
                        centroidKey = centroid.getValue().getNumberOfGroup();
                    }
                }
                finalListOfRecords.add(new Subject(vector.getValue().get(j).getNameOfSubject(),
                        vector.getValue().get(j).getVector(), centroidKey));
            }
        }
        return finalListOfRecords;
    }

    public double euclidesMeasure(Subject s1, Subject s2) {
        double distance = 0;
        for (int i = 0; i < s1.getVector().length; i++) {
            distance += Math.pow(s1.getVector()[i] - s2.getVector()[i], 2);
        }
        return Math.sqrt(distance);
    }

}
