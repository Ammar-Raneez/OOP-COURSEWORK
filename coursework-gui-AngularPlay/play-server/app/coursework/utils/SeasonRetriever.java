package coursework.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SeasonRetriever {
    private static final String SEASON_INPUT = "C:\\Users\\Ammuuu\\Downloads\\learning\\UNI\\OOP-Module\\Coursework\\" +
            "OOP-COURSEWORK\\SeasonInputPlay";
    private static String season = "";

    public static String getSeason() {
        try {
            File myObj = new File(SEASON_INPUT + "\\season.txt");
            Scanner sc = new Scanner(myObj);
            while (sc.hasNextLine()) {
                season = sc.nextLine().trim();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] ==> File could not be found!");
        }
        return season;
    }
}
