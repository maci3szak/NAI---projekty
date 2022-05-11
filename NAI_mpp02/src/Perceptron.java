import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Perceptron {

    private ArrayList<SubjectOfStudy> trainingList = SubjectOfStudy.readFile("/Users/maciejmaksymiuk/PJATKsemestrIV/NAI/NAI---projekty/NAI_mpp02/mpp2/iristrain.csv");;
    private ArrayList<SubjectOfStudy> testList;
    public double[] weights;
    private double theta; // próg theta
    private double alpha; // stała uczenia się
    static int MAX_ITERATION = 100;

    public Perceptron(double alpha) {
        trainingList = SubjectOfStudy.readFile("/Users/maciejmaksymiuk/PJATKsemestrIV/NAI/NAI---projekty/NAI_mpp02/mpp2/iristrain.csv");
        testList = SubjectOfStudy.readFile("/Users/maciejmaksymiuk/PJATKsemestrIV/NAI/NAI---projekty/NAI_mpp02/mpp2/iristest.csv");
        this.theta = 0;
        this.alpha = alpha;
        this.createWeights(); // zainicjowanie losowcyh wartości wag
    }

    public void createWeights() {
        int length = trainingList.get(1).getParameters().length;
        weights = new double[length];
        for (int i = 0; i < length; i++)
            weights[i] = (Math.random() * 2) - 1; //inicjowanie losowych wag
    }

    public int calculateOutput(SubjectOfStudy subject) {
        int out = 0;
        double[] parameters = subject.getParameters();
        for (int i = 0; i < parameters.length; i++)
            out += parameters[i] * weights[i]; //Xi*Wi
        if (out >= theta)
            out = 1;
        else
            out = 0;
        return out;
    }

    public void learningProcess() {
        int iteration = 0, output;
        double globalError, localError, predcitedDecision = 0;
        do {
            iteration++;
            globalError = 0;

            for (int i = 0; i < testList.size(); i++) {
                //faktyczna decyzja
                output = calculateOutput(testList.get(i));
                //różnica między prawidłową a faktyczną decyzją
                for (Map.Entry<String, Integer> s : SubjectOfStudy.speciesMap.entrySet()) {
                    if (s.getKey().equals(testList.get(i).getSpecies())) {
                        predcitedDecision = s.getValue();
                    }
                }
                localError = predcitedDecision - output;
                theta -= localError * alpha;

                for (int j = 0; j < weights.length; j++) {
                    weights[j] += alpha * localError * testList.get(i).getParameters()[j];

                }

            }


        } while (iteration <= MAX_ITERATION);
    }


    public void run() {
        for (Map.Entry<String, Integer> map : SubjectOfStudy.speciesMap.entrySet()) {
            System.out.println(map);
        }

        String species = "";
        double total = 0, found = 0;
        for (int j = 0; j < testList.size(); j++) {
            int output = 0;
            output = calculateOutput(testList.get(j));
            for (Map.Entry<String, Integer> entry : SubjectOfStudy.speciesMap.entrySet()) {
                if (output == entry.getValue()) {
                    species = entry.getKey();
                    found++;
                }
            }
            total++;
            System.out.println(testList.get(j).toString() + "-----> " + species);
            System.out.println(testList.get(j).toString() + "-----> " + output);
        }
        System.out.println("dokładność: " + total / found);
        System.out.println("liczba prawidłowo zaklasyfikowanych przykładów: " + found);

    }

    public void setNewData() {
        boolean isRunning = true;
        Scanner scan = new Scanner(System.in);
        while (isRunning) {
            System.out.println("Czy chcesz podać własny wektor?\nJeśli tak naciśnij --> y\nJeśli nie naciśnij --> n");
            String answer = scan.next();
            if (answer.equals("y")) {
                String line = "";
                System.out.println("Podaj parametry: ");
                line = scan.next();
                String[] s = line.split(",");
                double[] data = new double[s.length];
                for (int i = 0; i < data.length; i++)
                    data[i] = Double.parseDouble(s[i]);
                ArrayList<SubjectOfStudy> listOfNewObject = new ArrayList<>();
                SubjectOfStudy subject = new SubjectOfStudy(null, data);
                listOfNewObject.add(subject);
                setTestList(listOfNewObject);
                run();

            } else {
                System.out.println("KONIEC.");
                isRunning = false;
            }
        }

    }

    public void setTestList(ArrayList<SubjectOfStudy> testList) {
        this.testList = testList;
    }
}

