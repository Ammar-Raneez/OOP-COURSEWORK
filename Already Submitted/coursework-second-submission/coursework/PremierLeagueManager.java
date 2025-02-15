package coursework;/*
 * oop.cw.PremierLeagueManager
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

/**
 * oop.cw.PremierLeagueManager class, the class that will implement all the manipulation methods of a oop.cw.FootballClub
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class PremierLeagueManager implements LeagueManager {
    private static final String SAVE_PATH = "C:\\Users\\Ammuuu\\Downloads\\learning\\UNI\\OOP-Module\\Coursework\\" +
                                            "OOP-COURSEWORK\\saveFile-cli";
    private static List<FootballMatch> allMatches = new ArrayList<>();
    private static List<FootballClub> allFootballClubs = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static Random random = new Random();

    //***************************************PRIVATE COMMON HELPER METHODS********************************************//
    /**
     * Public helper method that displays a sentence and returns user's input transformed to lowercase and whitespaces
     * trimmed
     * Marked static since it's common for any object created
     * @param printSentence - sentence to use as a prompt
     * @return - the input of the user
     */
    public static String getUserInput(String printSentence) {
        System.out.println(printSentence);
        return sc.nextLine().toLowerCase().trim();
    }

    /**
     * Public helper method that generates the popular 3 dot loading
     * @throws InterruptedException - since Threads are used, to avoid any synchronization issues
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
    //**************************************END PRIVATE COMMON HELPER METHODS*****************************************//



    //************************************************ADD METHOD******************************************************//

    /**
     * Method implementation of addClub(), overrun from the oop.cw.LeagueManager interface
     * This method handles the functionality of adding/promoting a club to the PremierLeague
     * @param clubTypeInput - type of club input (university, school or league)
     * @param clubNameInput - name of the club
     * @param clubLocationInput - location of the club
     * @param clubOwnerInput - owner of the club
     * @param clubSponsorInput  clubs sponsor
     * @param colorTop - t-shirt color
     * @param colorShort - short color
     * @param netWorth - clubs net worth
     */
    @Override
    public void addClub(String clubTypeInput, String clubNameInput, String clubLocationInput, String clubOwnerInput,
                        String clubSponsorInput, Color colorTop, Color colorShort, String netWorth) {
        FootballClub footballClub = null;
        //*creates a club based on the input club type*//
        switch (clubTypeInput) {
            case "university":
                String lecturerInChargeInput = PremierLeagueManager.getUserInput("Please enter the " +
                                                                                 "lecturer in charge");

                footballClub = new UniversityFootballClub(clubNameInput, clubLocationInput, clubOwnerInput,
                                                          new SportsClubKit(clubSponsorInput, colorTop, colorShort),
                                                          lecturerInChargeInput, netWorth);
                break;
            case "school":
                String teacherInChargeInput = PremierLeagueManager.getUserInput("Please enter the " +
                                                                                "teacher in charge");

                footballClub = new SchoolFootballClub(clubNameInput, clubLocationInput, clubOwnerInput,
                                                      new SportsClubKit(clubSponsorInput, colorTop, colorShort),
                                                      teacherInChargeInput, netWorth);
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
     * Method implementation of deleteClub(), overrun from the oop.cw.LeagueManager interface
     * This method handles the functionality of deleting/relegating a club from the PremierLeague
     * Deletes based on club name input
     * @param clubNameInput - name of club wished to relegate
     * @return - the deleted club
     */
    @Override
    public FootballClub deleteClub(String clubNameInput) {
        boolean foundFlag = false;
        FootballClub removedClub = null;

        for (FootballClub footballClub : allFootballClubs) {
            if (footballClub.getClubName().equals(clubNameInput)) {
                removedClub = allFootballClubs.remove(allFootballClubs.indexOf(footballClub));
                foundFlag = true;
                break; //*improve efficiency ==> Once found, there's no need to keep looking*//
            }
        }

        //*flag used, so that only once the following error will get printed, else, for every for loop it would*//
        if (!foundFlag) {
            System.out.println("[ERROR] ==> No Such Club Exists");
            return null;
        } else {
            System.out.println(allFootballClubs.size());
            return removedClub;
        }
    }
    //*********************************************END OF DELETE METHOD***********************************************//



    //*********************************************DISPLAY SELECTED CLUB**********************************************//

    /**
     * Method implementation of displaySelectedClub(), overrun from the oop.cw.LeagueManager interface
     * This method handles the functionality of displaying a club in the PremierLeague
     * Identifies based on club name
     * @param clubNameInput - name of club wished to display
     */
    @Override
    public void displaySelectedClub(String clubNameInput) {
        boolean foundFlag = false;
        FootballClub foundClub = null;

        for (FootballClub footballClub : allFootballClubs) {
            if (footballClub.getClubName().equals(clubNameInput)) {
                foundClub = footballClub;
                foundFlag = true;
                break;
            }
        }

        if (!foundFlag) {
            System.out.println("[ERROR] ==> No Such Club Exists");
        } else {
            System.out.println(
                    "Club: " + foundClub.getClubName() + " | Location: " + foundClub.getClubLocation() + " | Owner: " +
                    foundClub.getClubOwner() + " | Net Worth: " + foundClub.getClubNetWorth() + "\nPoints: " +
                    foundClub.getFootballClubTotalStatistics().getPoints() + " | Matches Played: " +
                    foundClub.getFootballClubTotalStatistics().getMatchesPlayed() + " | Wins: " +
                    foundClub.getFootballClubTotalStatistics().getWins() + " | Draws: " +
                    foundClub.getFootballClubTotalStatistics().getDraws() + " | Defeats: " +
                    foundClub.getFootballClubTotalStatistics().getDefeats()
            );
        }
    }
    //******************************************END DISPLAY SELECTED CLUB*********************************************//



    //***************************************ADD PLAYED MATCH BETWEEN TWO CLUB****************************************//

    /**
     * Method implementation of addPlayedMatch(), overrun from the oop.cw.LeagueManager interface
     * This method handles the functionality of playing a match in the PremierLeague
     */
    @Override
    public void addPlayedMatch() {
//        FootballClub firstTeam;
//        FootballClub secondTeam;
//
//        //*this if condition checks whether there are enough teams to play a match in the first place*//
//        if (allFootballClubs.size() < 2) {
//            System.out.println("There isn't enough teams to play a match!");
//            return;
//        }
//
//        //*loop till two unique teams are selected (two same teams cannot play against each other)*//
//        do {
//            firstTeam = allFootballClubs.get(PremierLeagueManager.random.nextInt(allFootballClubs.size()));
//            secondTeam = allFootballClubs.get(PremierLeagueManager.random.nextInt(allFootballClubs.size()));
//        } while (firstTeam.getClubName().equals(secondTeam.getClubName()));
//
//
//        FootballMatch footballMatch;
//        boolean hasMatch;
//        //*the logic here is to loop infinitely, till a unique match has been generated*//
//        while (true){
//            LocalDate localDate = generateRandomDate(20);
//            hasMatch = false;
//            footballMatch = new FootballMatch(firstTeam, secondTeam, localDate);
//
//            //*check the above generated match against all the matches already played*//
//            for (FootballMatch match : allMatches) {
//                if (match.equals(footballMatch)) {
//                    hasMatch = true;
//                    break;
//                }
//            }
//
//            //*if and only if the hasMatch flag had not turned true (the generated match is unique)*//
//            //*the match is played and added into the list of matches, and the entire while loop can be broken*//
//            //*since its reason has been fulfilled*//
//            if(!hasMatch) {
//                footballMatch.playMatch();
//                allMatches.add(footballMatch);
//                break;
//            }
//
//            //*if it was the case that the match generated was already played (hasMatch turned true during for loop)*//
//            //*another two teams are picked at random*//
//            do {
//                firstTeam = allFootballClubs.get(PremierLeagueManager.random.nextInt(allFootballClubs.size()));
//                secondTeam = allFootballClubs.get(PremierLeagueManager.random.nextInt(allFootballClubs.size()));
//            } while (firstTeam.getClubName().equals(secondTeam.getClubName()));
//
//            //*based on a common pattern identified, the if condition was created (max number of matches playable)*//
//            //*second condition is if you delete a football club after it has already played a match*//
//            if ((allMatches.size() == (allFootballClubs.size() * (allFootballClubs.size() - 1) / 2)) ||
//                    (allMatches.size() > (allFootballClubs.size() * (allFootballClubs.size() - 1) / 2))) {
//                System.out.println("All Possible Matches have already been played!");
//                break;
//            }
//        }
    }
    /**
     * Private helper method that generates a random date
     * If generated value is less than 10, appends a 0 at the beginning so that it can be parsed into a LocalDate
     * @param year - takes year parameter (to be used when multiple season functionality has been added)
     * @return - a LocalDate object containing the random date created
     */
    private static LocalDate generateRandomDate(int year) {
        String randomDay = String.valueOf(random.nextInt((31)) + 1);
        if (Integer.parseInt(randomDay) < 10) randomDay = "0" + randomDay;
        String randomMonth = String.valueOf(random.nextInt((12)) + 1);
        if (Integer.parseInt(randomMonth) < 10) randomMonth = "0" + randomMonth;
        String dateString = randomDay + "/" + randomMonth + "/" + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return LocalDate.parse(dateString, formatter);
    }
    //*************************************END ADD PLAYED MATCH BETWEEN TWO CLUB**************************************//



    //*********************************************DISPLAY POINTS TABLE***********************************************//

    /**
     * Method implementation of displayPointsTable(), overrun from the oop.cw.LeagueManager interface
     * This method handles the functionality of displaying the standings of the Premier League
     */
    @Override
    public void displayPointsTable() {
        //*sort based on the oop.cw.GoalDifferenceComparator created, in descending order*//
        allFootballClubs.sort(new GoalDifferenceComparator().reversed());
        //*then use the compareTo() method of oop.cw.FootballClub to sort based on points
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
     * Method implementation of displayMatchResults(), overrun from the oop.cw.LeagueManager interface
     * This method handles the functionality of displaying all the match results in the Premier League
     */
    @Override
    public void displayMatchResults() {
        //*oop.cw.FootballMatch compareTo() method is used to sort (Date sorting), in descending order*//
        //*So it's ordered from the most recent to the least*//
        allMatches.sort(Collections.reverseOrder());
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
    }
    //******************************************END DISPLAY MATCH SCORES**********************************************//



    //*********************************************DISPLAY SELECTED DATE**********************************************//

    /**
     * Method implementation of displaySelectedMatchStatistics(), overrun from the oop.cw.LeagueManager interface
     * This method handles the functionality of displaying a selected match in the Premier League
     * Identifies based on the club name inputs that are involved in the match
     * @param firstTeamInput - club name
     * @param secondTeamInput - club vs name
     */
    @Override
    public void displaySelectedMatchStatistics(String firstTeamInput, String secondTeamInput) {
        boolean foundMatch = false;
        FootballMatch foundFootballMatch = null;
        for (FootballMatch footballMatch: allMatches) {
            //*if condition that checks both possibilities, so that if user enters football club 2 as the first input*//
            //*and football club 1 as the second output, the same match is considered*//
            if ((footballMatch.getFirstTeam().getClubName().equals(firstTeamInput) &&
                    footballMatch.getSecondTeam().getClubName().equals(secondTeamInput)) ||
                    (footballMatch.getSecondTeam().getClubName().equals(firstTeamInput) &&
                        footballMatch.getFirstTeam().getClubName().equals(secondTeamInput))) {
                            foundFootballMatch = footballMatch;
                            foundMatch = true;
                            break;
            }
        }

        if (!foundMatch) {
            System.out.println("[ERROR] ==> No such Football Match exists!");
        } else {
            //*display all stats of the match, in a simple formatted string*//
            System.out.println("===================================================================");
            System.out.format("%-27s %-10s %28s", foundFootballMatch.getFirstTeam().getClubName().toUpperCase(),
                    "TEAM STATS", foundFootballMatch.getSecondTeam().getClubName().toUpperCase());
            System.out.println();
            System.out.format("%-30s %-5s %30s", foundFootballMatch.getFirstTeamSingleMatchStats().getGoals(),
                    "Goals", foundFootballMatch.getSecondTeamSingleMatchStats().getGoals());
            System.out.println();
            System.out.format("%-30s %-5s %30s", foundFootballMatch.getFirstTeamSingleMatchStats().getShots(),
                    "Shots", foundFootballMatch.getSecondTeamSingleMatchStats().getShots());
            System.out.println();
            System.out.format("%-25s %-15s %25s", foundFootballMatch.getFirstTeamSingleMatchStats().getShotsOnTarget(),
                    "Shots on target", foundFootballMatch.getSecondTeamSingleMatchStats().getShotsOnTarget());
            System.out.println();
            System.out.format("%-27s %-10s %28s", foundFootballMatch.getFirstTeamSingleMatchStats().getPossession(),
                    "Possession", foundFootballMatch.getSecondTeamSingleMatchStats().getPossession());
            System.out.println();
            System.out.format("%-29s %-6s %30s", foundFootballMatch.getFirstTeamSingleMatchStats().getPasses(),
                    "Passes", foundFootballMatch.getSecondTeamSingleMatchStats().getPasses());
            System.out.println();
            System.out.format("%-26s %-13s %26s", foundFootballMatch.getFirstTeamSingleMatchStats().getPassAccuracy(),
                    "Pass accuracy", foundFootballMatch.getSecondTeamSingleMatchStats().getPassAccuracy());
            System.out.println();
            System.out.format("%-30s %-5s %30s", foundFootballMatch.getFirstTeamSingleMatchStats().getFouls(),
                    "Fouls", foundFootballMatch.getSecondTeamSingleMatchStats().getFouls());
            System.out.println();
            System.out.format("%-26s %-12s %27s", foundFootballMatch.getFirstTeamSingleMatchStats().getYellowCards(),
                    "Yellow cards", foundFootballMatch.getSecondTeamSingleMatchStats().getYellowCards());
            System.out.println();
            System.out.format("%-28s %-9s %28s", foundFootballMatch.getFirstTeamSingleMatchStats().getRedCards(),
                    "Red cards", foundFootballMatch.getSecondTeamSingleMatchStats().getRedCards());
            System.out.println();
            System.out.format("%-28s %-8s %29s", foundFootballMatch.getFirstTeamSingleMatchStats().getOffsides(),
                    "Offsides", foundFootballMatch.getSecondTeamSingleMatchStats().getOffsides());
            System.out.println();
            System.out.format("%-29s %-7s %29s", foundFootballMatch.getFirstTeamSingleMatchStats().getCorners(),
                    "Corners", foundFootballMatch.getSecondTeamSingleMatchStats().getCorners());
            System.out.println("\n===================================================================");
        }
    }
    //********************************************END DISPLAY SELECTED MATCH******************************************//



    //**************************************************SAVE DATA*****************************************************//

    /**
     * Method implementation of saveData(), overrun from the oop.cw.LeagueManager interface
     * This method handles the functionality of saving all required data
     */
    @Override
    public void saveData() {
//        //*list of type Object, to store both the Clubs and Matches*//
//        List<Object> allData = new ArrayList<>();
//        allData.add(allFootballClubs);
//        allData.add(allMatches);
//
//        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(SAVE_PATH + "\\saveFile.txt"));
//             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
//            System.out.print("Now saving data");
//            PremierLeagueManager.threeDotSuspense();
//            objectOutputStream.writeObject(allData);
//            Thread.sleep(500);
//            System.out.println("Data saved successfully!");
//        } catch (Exception e) {
//            System.out.println("[ERROR] ==> Something went wrong while saving the file! " + e.getMessage());
//        }
    }
    //**********************************************END SAVE DATA*****************************************************//



    //************************************************LOAD DATA*******************************************************//

    /**
     * Method implementation of loadData(), overrun from the oop.cw.LeagueManager interface
     * This method handles the functionality of loading all the data that had been saved
     */
    @Override
    public void loadData() {
//        List<Object> allData;
//
//        try (FileInputStream fileInputStream = new FileInputStream(new File(SAVE_PATH + "\\saveFile.txt"));
//             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
//            System.out.println("saveFile.txt found!");
//            System.out.print("Now loading data");
//            PremierLeagueManager.threeDotSuspense();
//            allData = (List<Object>) objectInputStream.readObject();
//            allFootballClubs = (List<FootballClub>) allData.get(0);
//            allMatches = (List<FootballMatch>) allData.get(1);
//            Thread.sleep(500);
//            System.out.println("Data loaded successfully!");
//        } catch (FileNotFoundException ex) {
//            System.out.println("[ERROR] ==> There weren't any files to load!");
//        } catch (Exception e) {
//            System.out.println("[ERROR] ==> Something went wrong in loading the file! " + e.getMessage());
//        }
    }
    //**********************************************END LOAD DATA*****************************************************//

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
}
