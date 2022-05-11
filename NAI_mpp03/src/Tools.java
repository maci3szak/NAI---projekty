import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tools {

    //reading all files in a given language directory
    public static ArrayList<Language> readFile(String directoryPath) {
        ArrayList<Language> languages = new ArrayList<>();

        File dirPath = new File(directoryPath);
        File directoryLanguageList[] = dirPath.listFiles();
        Scanner scanner = null;
        String language = "";

        for (File file : directoryLanguageList) {

            if (file.isDirectory()) {
                language = file.getName();
                File textsList[] = file.listFiles();
                StringBuilder stringBuilder = new StringBuilder();
                for (File textFile : textsList) {
                    if (textFile.getName().equals(".DS_Store"))
                        continue;
                    try {
                        scanner = new Scanner(textFile);
                        String line = "";
                        while (scanner.hasNextLine()) {
                            line = scanner.nextLine();
                            //replace all specific letters and characters with lowercase letter (only English letters)
                            stringBuilder.append(line.toLowerCase().replaceAll("[^a-zA-Z]", ""));
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                languages.add(new Language(language, stringBuilder.toString()));
            }
        }
        return languages;
    }


}
