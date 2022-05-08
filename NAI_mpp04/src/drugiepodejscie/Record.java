package drugiepodejscie;

public class Record {
    private String name;
    private double[] parameters;
    private int clusterNumber;

    public Record(String name, double[] parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return name + " -> " + sb + " group:" + clusterNumber;
    }
}
