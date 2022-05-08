package drugiepodejscie;

public class Cluster {
    private int clusterNumber;
    private double[] parameters;

    public Cluster(int clusterNumber, double[] parameters) {
        this.clusterNumber = clusterNumber;
        this.parameters = parameters;
    }

    public double[] getParameters() {
        return parameters;
    }

    public void setParameters(double[] parameters) {
        this.parameters = parameters;
    }

    public int getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            sb.append(parameters[i] + " ");
        }
        return clusterNumber + " values: " + sb;
    }

    public double calculateDistance(Record record) {
        double distance = 0;
        for (int i = 0; i < parameters.length; i++) {
            distance += Math.pow(getParameters()[i] - record.getParameters()[i], 2);
        }
        return Math.sqrt(distance);
    }

    public double calculateDistanceWithoutSqrt(Record record) {
        double distance = 0;
        for (int i = 0; i < parameters.length; i++) {
            distance += Math.pow(getParameters()[i] - record.getParameters()[i], 2);
        }
        return distance;
    }

    public void updateCentroid(Record record) {
        double[] values = new double[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            values[i] = (getParameters()[i] + record.getParameters()[i]) / 2;
        }
        setParameters(values);
    }


}
