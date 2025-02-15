package coursework;

/*
 * PremierLeagueManager
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

/**
 * PremierLeagueManager class, the class that will implement all the manipulation of a FootballClub
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class PremierLeagueManager implements LeagueManager {
    private static final String SAVE_PATH = "C:\\Users\\Ammuuu\\Downloads\\learning\\UNI\\OOP-Module\\Coursework\\" +
            "OOP-COURSEWORK\\saveFile-cli";
    private static final int MAX_SIZE = 20;
    private static final Random RANDOM = new Random();
    private static List<FootballMatch> allMatches = new ArrayList<>();
    private static List<FootballClub> allFootballClubs = new ArrayList<>();

    //************************************************HELPER METHODS**************************************************//
    /**
     * Public helper method that generates the popular 3 dot loading
     * @throws InterruptedException - Threads are used, to avoid any synchronization issues
     */
    public static void threeDotSuspense() throws InterruptedException {
        for (int i=0; i<2; i++) {
            Thread.sleep(500);
            System.out.print(".");
        }
        Thread.sleep(500);
        System.out.println(".");
        Thread.sleep(500);
    }
    //*************************************************END HELPER METHODS*********************************************//



    //************************************************ADD METHOD******************************************************//
    /**
     * Method implementation of addClub(), overrun from the LeagueManager interface
     * This method handles the functionality of adding/promoting a club to the PremierLeague
     * @param clubTypeInput - type of club input (university, school or league)
     * @param lecOrTeachInput - if chosen club type was school or university, holds the name of the teacher or lecturer
     * @param clubNameInput - name of the club
     * @param clubLocationInput - location of the club
     * @param clubOwnerInput - owner of the club
     * @param clubSponsorInput  clubs sponsor
     * @param colorTop - t-shirt color
     * @param colorShort - short color
     * @param netWorth - clubs net worth
     */
    @Override
    public void addClub(String clubTypeInput, String lecOrTeachInput, String clubNameInput, String clubLocationInput,
                        String clubOwnerInput, String clubSponsorInput, Color colorTop, Color colorShort, String netWorth) {
        FootballClub footballClub = null;

        //*creates a FootballClub based on the input club type*//
        switch (clubTypeInput) {
            case "university":
                footballClub = new UniversityFootballClub(clubNameInput, clubLocationInput, clubOwnerInput,
                        new SportsClubKit(clubSponsorInput, colorTop, colorShort),
                        lecOrTeachInput, netWorth);
                break;
            case "school":
                footballClub = new SchoolFootballClub(clubNameInput, clubLocationInput, clubOwnerInput,
                        new SportsClubKit(clubSponsorInput, colorTop, colorShort),
                        lecOrTeachInput, netWorth);
                break;
            case "league":
                footballClub = new FootballClub(clubNameInput, clubLocationInput, clubOwnerInput,
                        new SportsClubKit(clubSponsorInput, colorTop, colorShort), netWorth);
                break;
        }

        allFootballClubs.add(footballClub);
    }
    //**********************************************END OF ADD METHOD*************************************************//



    //***********************************************DELETE METHOD****************************************************//
    /**
     * Method implementation of deleteClub(), overrun from the LeagueManager interface
     * This method handles the functionality of deleting/relegating a club from the PremierLeague
     * Deletes based on club name input
     * @param clubNameInput - name of club wished to relegate
     * @return - the deleted club if found, else returns null
     */
    @Override
    public FootballClub deleteClub(String clubNameInput) {
        FootballClub removedClub = null;

        for (FootballClub footballClub : allFootballClubs) {
            if (footballClub.getClubName().equals(clubNameInput)) {
                removedClub = allFootballClubs.remove(allFootballClubs.indexOf(footballClub));
                break; //*improve efficiency ==> Once found, there's no need to keep looking*//
            }
        }

        return removedClub;
    }
    //*********************************************END OF DELETE METHOD***********************************************//



    //*********************************************DISPLAY SELECTED CLUB**********************************************//
    /**
     * Method implementation of displaySelectedClub(), overrun from the LeagueManager interface
     * This method handles the functionality of displaying a club in the PremierLeague
     * Identifies based on club name, follows same logic as the deleteClub() method
     * @param clubNameInput - name of club wished to display
     * @return - the found club, if found, else returns null
     */
    @Override
    public FootballClub displaySelectedClub(String clubNameInput) {
        FootballClub foundClub = null;

        for (FootballClub footballClub : allFootballClubs) {
            if (footballClub.getClubName().equals(clubNameInput)) {
                foundClub = footballClub;
                break;
            }
        }

        return foundClub;
    }
    //******************************************END DISPLAY SELECTED CLUB*********************************************//



    //********************************************DISPLAY SELECTED MATCH**********************************************//
    /**
     * Method implementation of displaySelectedMatch(), overrun from the LeagueManager interface
     * This method handles the functionality of displaying statistics of a selected match in the Premier League
     * Identifies based on the club name inputs that are involved in the match
     * @param firstTeamInput - club 1/2
     * @param secondTeamInput - club 1/2
     * @return  - the found match, if found, else returns null
     */
    @Override
    public FootballMatch displaySelectedMatch(String firstTeamInput, String secondTeamInput) {
        FootballMatch foundFootballMatch = null;
        for (FootballMatch footballMatch: allMatches) {
            //*if condition that checks both possibilities, so that if user enters football club 2 as the first input*//
            //*and football club 1 as the second output, the same match is considered*//
            if ((footballMatch.getFirstTeam().getClubName().equals(firstTeamInput) &&
                    footballMatch.getSecondTeam().getClubName().equals(secondTeamInput)) ||
                    (footballMatch.getSecondTeam().getClubName().equals(firstTeamInput) &&
                            footballMatch.getFirstTeam().getClubName().equals(secondTeamInput))) {
                foundFootballMatch = footballMatch;
                break;
            }
        }

        return foundFootballMatch;
    }
    //********************************************END DISPLAY SELECTED MATCH******************************************//



    //***************************************ADD PLAYED MATCH BETWEEN TWO CLUB****************************************//
    /**
     * Method implementation of addPlayedMatch(), overrun from the LeagueManager interface
     * This method handles the functionality of playing a match in the PremierLeague
     */
    @Override
    public void addPlayedMatch() throws InterruptedException {
        FootballClub firstTeam;
        FootballClub secondTeam;

        //*this if condition checks whether there are enough teams to play a match in the first place*//
        if (allFootballClubs.size() < 2) {
            System.out.println("[ERROR] ==> There isn't enough teams to play a match!");
            return;
        }

        //*loop till two unique teams are selected (two same teams cannot play against each other)*//
        do {
            firstTeam = allFootballClubs.get(PremierLeagueManager.RANDOM.nextInt(allFootballClubs.size()));
            secondTeam = allFootballClubs.get(PremierLeagueManager.RANDOM.nextInt(allFootballClubs.size()));
        } while (firstTeam.getClubName().equals(secondTeam.getClubName()));


        FootballMatch footballMatch;
        boolean hasMatch;
        //*the logic here is to loop infinitely, till a unique match has been generated*//
        while (true){
            LocalDate localDate = generateRandomDate(20);
            hasMatch = false;
            footballMatch = new FootballMatch(firstTeam, secondTeam, localDate);

            //*check the above generated match against all the matches already played*//
            for (FootballMatch match : allMatches) {
                //*overridden equals() method*//
                if (match.equals(footballMatch)) {
                    hasMatch = true;
                    break;
                }
            }

            //*if and only if the hasMatch flag had not turned true (the generated match is unique)*//
            //*the match is played and added into the list of matches, and the entire while loop is broken*//
            //*since its reason has been fulfilled*//
            if(!hasMatch) {
                System.out.print("Now playing match between " + footballMatch.getFirstTeam().getClubName() + " and " +
                        footballMatch.getSecondTeam().getClubName()
                );
                PremierLeagueManager.threeDotSuspense();
                System.out.println();
                footballMatch.playMatch();
                allMatches.add(footballMatch);
                break;
            }

            //*if it was the case that the match generated was already played (hasMatch turned true during for loop)*//
            //*another two teams are picked at random*//
            do {
                firstTeam = allFootballClubs.get(PremierLeagueManager.RANDOM.nextInt(allFootballClubs.size()));
                secondTeam = allFootballClubs.get(PremierLeagueManager.RANDOM.nextInt(allFootballClubs.size()));
            } while (firstTeam.getClubName().equals(secondTeam.getClubName()));

            //*based on a pattern identified, the if condition was created (max number of matches playable)*//
            //*second condition is if you delete a football club after it has already played a match*//
            if ((allMatches.size() == (allFootballClubs.size() * (allFootballClubs.size() - 1) / 2)) ||
                    (allMatches.size() > (allFootballClubs.size() * (allFootballClubs.size() - 1) / 2))) {
                System.out.println("[ERROR] ==> All Possible Matches have already been played!");
                break;
            }
        }
    }
    /**
     * Private helper method that generates a random date
     * If generated value is less than 10, appends a 0 at the beginning so that it can be parsed into a LocalDate
     * @param year - takes year parameter (to be used when multiple season functionality has been added)
     * @return - a LocalDate object containing the random date created
     */
    //TODO multi season functionality
    private static LocalDate generateRandomDate(int year) {
        String randomDay = String.valueOf(RANDOM.nextInt((31)) + 1);
        if (Integer.parseInt(randomDay) < 10) {
            randomDay = "0" + randomDay;
        }

        String randomMonth = String.valueOf(RANDOM.nextInt((12)) + 1);
        if (Integer.parseInt(randomMonth) < 10) {
            randomMonth = "0" + randomMonth;
        }
        String dateString = randomDay + "/" + randomMonth + "/" + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return LocalDate.parse(dateString, formatter);
    }
    //*************************************END ADD PLAYED MATCH BETWEEN TWO CLUB**************************************//



    //*********************************************DISPLAY POINTS TABLE***********************************************//
    /**
     * Method implementation of displayPointsTable(), overrun from the LeagueManager interface
     * This method handles the functionality of displaying the standings of the Premier League
     */
    @Override
    public void displayPointsTable() {
        //*sort based on the GoalDifferenceComparator created, in descending order*//
        allFootballClubs.sort(new GoalDifferenceComparator().reversed());
        //*then use the compareTo() method of FootballClub to sort based on points
        //*(GD sorted first so the GD order is maintained)*//
        allFootballClubs.sort(Collections.reverseOrder());

        System.out.println("=======================================================================");
        System.out.println("PREMIER LEAGUE STANDINGS");
        System.out.println("=======================================================================");
        System.out.format("%-20s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s", "Club", "MP", "W", "D", "L", "GF", "GA",
                "GD", "Pts");
        System.out.println();
        //*A simple formatted string is used to print*//
        for (FootballClub footballClub : allFootballClubs) {
            System.out.format("%-20s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s", footballClub.getClubName(),
                    footballClub.getFootballClubTotalStatistics().getMatchesPlayed(),
                    footballClub.getFootballClubTotalStatistics().getWins(),
                    footballClub.getFootballClubTotalStatistics().getDraws(),
                    footballClub.getFootballClubTotalStatistics().getDefeats(),
                    footballClub.getFootballClubTotalStatistics().getGoalsFor(),
                    footballClub.getFootballClubTotalStatistics().getGoalsAgainst(),
                    footballClub.getFootballClubTotalStatistics().getGoalDifference(),
                    footballClub.getFootballClubTotalStatistics().getPoints());
            System.out.println();
        }
        System.out.println("=======================================================================");
    }
    //******************************************END DISPLAY POINTS TABLE**********************************************//



    //*********************************************DISPLAY MATCH SCORES***********************************************//
    /**
     * Method implementation of displayMatchResults(), overrun from the LeagueManager interface
     * This method handles the functionality of displaying all the match results in the Premier League
     */
    @Override
    public void displayMatchResults() {
        //*FootballMatch compareTo() method is used to sort (Date sorting), in descending order*//
        //*So it's ordered from the most recent to the least*//
        allMatches.sort(Collections.reverseOrder());
        if (allMatches.size()>0) {
            System.out.println("=============================================");
            System.out.format("%7s %5s", "", "PREMIER LEAGUE - ALL MATCHES");
            System.out.println();
            System.out.println("=============================================");

            for (FootballMatch footballMatch : allMatches) {
                System.out.format("%17s %5s", "", footballMatch.getMatchDate());
                System.out.println();
                System.out.format("%-19s %2s %2s %19s", footballMatch.getFirstTeam().getClubName(),
                        footballMatch.getFirstTeamSingleMatchStats().getGoals(),
                        footballMatch.getSecondTeamSingleMatchStats().getGoals(),
                        footballMatch.getSecondTeam().getClubName()
                );
                System.out.println();
                System.out.println("---------------------------------------------");
            }
        } else {
            System.out.println("[ERROR] ==> No matches have been played yet!");
        }
    }
    //******************************************END DISPLAY MATCH SCORES**********************************************//



    //**************************************************SAVE DATA*****************************************************//
    /**
     * Method implementation of saveData(), overrun from the LeagueManager interface
     * This method handles the functionality of saving all required data
     */
    @Override
    public void saveData() {
        //*list of type Object, to store both the Clubs and Matches*//
        List<Object> allData = new ArrayList<>();
        allData.add(allFootballClubs);
        allData.add(allMatches);

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(SAVE_PATH + "\\saveFile.txt"));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            System.out.print("Now saving data");
            PremierLeagueManager.threeDotSuspense();
            objectOutputStream.writeObject(allData);
            Thread.sleep(500);
            System.out.println("Data saved successfully!");
        } catch (Exception e) {
            System.out.println("[ERROR] ==> Something went wrong while saving the file! " + e.getMessage());
        }
    }
    //**********************************************END SAVE DATA*****************************************************//



    //************************************************LOAD DATA*******************************************************//
    /**
     * Method implementation of loadData(), overrun from the LeagueManager interface
     * This method handles the functionality of loading all the data that had been saved
     */
    @Override
    public void loadData() {
        List<Object> allData;

        try (FileInputStream fileInputStream = new FileInputStream(new File(SAVE_PATH + "\\saveFile.txt"));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            System.out.println("saveFile.txt found!");
            System.out.print("Now loading data");
            PremierLeagueManager.threeDotSuspense();
            allData = (List<Object>) objectInputStream.readObject();
            //*load based on the index it was saved (allData is a list of two lists)*//
            allFootballClubs = (List<FootballClub>) allData.get(0);
            allMatches = (List<FootballMatch>) allData.get(1);
            Thread.sleep(500);
            System.out.println("Data loaded successfully!");
        } catch (FileNotFoundException ex) {
            System.out.println("[ERROR] ==> There weren't any files to load!");
        } catch (Exception e) {
            System.out.println("[ERROR] ==> Something went wrong in loading the file! " + e.getMessage());
        }
    }
    //**********************************************END LOAD DATA*****************************************************//

    //********************************GETTER METHODS TO ACCESS THROUGHOUT THE PROJECT*********************************//
    /**
     * @return list containing all the matches
     */
    public static List<FootballMatch> getAllMatches() {
        return allMatches;
    }

    /**
     * @return list containing all the football clubs
     */
    public static List<FootballClub> getAllFootballClubs() {
        return allFootballClubs;
    }

    /**
     * @return max number of clubs in premier league
     */
    public static int getMaxSize() {
        return MAX_SIZE;
    }
}