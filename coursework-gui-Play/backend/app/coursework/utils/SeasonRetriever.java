package coursework.utils;

/*
 * SeasonRetriever
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * SeasonRetriever class, responsible for loading in the season input saved in PremierLeagueManager
 * For Play to get access to the season
 * @version 1.x December 20th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class SeasonRetriever {
    private static final String SEASON_INPUT = "C:\\Users\\Ammuuu\\Downloads\\learning\\UNI\\OOP-Module\\Coursework\\" +
            "OOP-COURSEWORK\\SeasonInputPlay";
    private static String season = "";

    /**
     * Retrieves and returns the season input from the file
     * @return - the season input
     */
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
