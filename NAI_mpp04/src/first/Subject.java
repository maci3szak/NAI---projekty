package first;

public class Subject {

    private String nameOfSubject;
    private double[] vector;
    private int numberOfGroup;

    public Subject(String nameOfSubject, double[] vector, int numberOfGroup) {
        this.nameOfSubject = nameOfSubject;
        this.vector = vector;
        this.numberOfGroup = numberOfGroup;
    }

    public String getNameOfSubject() {
        return nameOfSubject;
    }

    public void setNameOfSubject(String nameOfSubject) {
        this.nameOfSubject = nameOfSubject;
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    public int getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(int numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vector.length; i++) {
            sb.append(vector[i] + ", ");
        }
        return nameOfSubject + " -> " + sb + " group:" + numberOfGroup;
    }
}
