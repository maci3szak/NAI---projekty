import java.util.Arrays;
import java.util.List;

public class Perceptron {

    private double alpha = 0.1;
    private double theta = 0;
    private double[] weights;
    private String negativeType;
    private String positiveType;

    public Perceptron(String nameOfLanguage) {
        weights = new double[26];
        Arrays.fill(weights, 0);
        negativeType = "0";
        positiveType = nameOfLanguage;
    }

    double computeOutput(Language language) {
        double res = 0;
        for (int j = 0; j < language.getVector().length; j++) {
            res += language.getVector()[j] * weights[j];
        }
        return res;
    }

    public void learn(List<Language> languages) {
        while (true) {
            for (Language language : languages) {
                double o = computeOutput(language);
                double t = language.getNameOfLanguage().equals(positiveType) ? 1 : 0;
                if (t - o >= 0 && t - o <= 0.01) {
                    System.out.println(positiveType + " trained!");
                    return;
                }
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += alpha * (t - o) * language.getVector()[j];
                }

            }

        }
    }


    public String getNegativeType() {
        return negativeType;
    }

    public void setNegativeType(String negativeType) {
        this.negativeType = negativeType;
    }

    public String getPositiveType() {
        return positiveType;
    }

    public void setPositiveType(String positiveType) {
        this.positiveType = positiveType;
    }



}
