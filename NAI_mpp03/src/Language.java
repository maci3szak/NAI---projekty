public class Language {

    private String nameOfLanguage;
    private String text;
    private double[] vector;

    public Language(String nameOfLanguage, String text) {
        this.nameOfLanguage = nameOfLanguage;
        this.text = text;
        this.vector = countLetters(text);
        this.vector = vectorNormalization(vector);
    }

    public Language() {

    }

    public String getNameOfLanguage() {
        return nameOfLanguage;
    }

    public void setNameOfLanguage(String nameOfLanguage) {
        this.nameOfLanguage = nameOfLanguage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    @Override
    public String toString() {
        return "language: " + nameOfLanguage + " \n" + text;
    }

    public double[] countLetters(String text) {
        double[] ratioOfLetters = new double[26]; // an array the stores the number of occurrences of the letters of the alphabet
        int count;
        int placeInTheArray = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            count = 0;
            for (int j = 0; j < text.length(); j++)
                if ((char) i == text.charAt(j))
                    count++;
            ratioOfLetters[placeInTheArray++] = count;
        }
        return ratioOfLetters;
    }
    private double[] vectorNormalization(double[] vector) {
        double vectorLength = 0;
        for (int i = 0; i < vector.length; i++)
            vectorLength += Math.pow(vector[i], 2);
        vectorLength = Math.sqrt(vectorLength);

        double[] normalizedVector = new double[vector.length];
        for (int i = 0; i < normalizedVector.length; i++)
            normalizedVector[i] = vector[i]/vectorLength;

        return normalizedVector;
    }


    Language addData(String data) {
        this.text = data;
        return this;
    }


}
