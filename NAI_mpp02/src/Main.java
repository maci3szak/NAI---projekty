public class Main {
    public static void main(String[] args) {

        Perceptron perceptron = new Perceptron(0.1);
        perceptron.learningProcess();
        perceptron.run();
        perceptron.setNewData();


    }
}
