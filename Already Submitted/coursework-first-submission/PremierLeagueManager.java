/*
 * PremierLeagueManager
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * PremierLeagueManager class, the class that will implement all the manipulation methods of a FootballClub
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class PremierLeagueManager implements LeagueManager {
    private static final String SAVE_PATH = "C:\\Users\\Ammuuu\\Downloads\\learning\\UNI\\OOP-Module\\Coursework\\" +
                                            "OOP-COURSEWORK\\saveFile";
    private static List<FootballMatch> allMatches = new ArrayList<>();
    private static List<FootballClub> allFootballClubs = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static Random random = new Random();

    //***************************************PRIVATE COMMON HELPER METHODS********************************************//
    /**
     * Public helper method that displays a sentence and returns user's input
     * Marked static since it's common for any object created
     * @param printSentence - sentence to use as a prompt
     * @return - the input of the user
     */
    public static String getUserInput(String printSentence) {
        System.out.println(printSentence);
        return sc.nextLine().toLowerCase();
    }

    /**
     * Private helper method that generates the popular 3 dot loading
     * @throws InterruptedException - since Threads are used, to avoid any synchronization issues
     */
    private static void threeDotSuspense() throws InterruptedException {
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
    @Override
    public void addClub(String clubTypeInput, String clubNameInput, String clubLocationInput, String clubOwnerInput,
                        String clubSponsorInput, Color colorTop, Color colorShort) {
        FootballClub footballClub = null;
        switch (clubTypeInput) {
            case "university":
                String lecturerInChargeInput = PremierLeagueManager.getUserInput("Please enter the " +
                                                                                 "lecturer in charge");

                footballClub = new UniversityFootballClub(clubNameInput, clubLocationInput, clubOwnerInput,
                                                          new SportsClubKit(clubSponsorInput, colorTop, colorShort),
                                                          lecturerInChargeInput);
                break;
            case "school":
                String teacherInChargeInput = PremierLeagueManager.getUserInput("Please enter the " +
                                                                                "teacher in charge");

                footballClub = new SchoolFootballClub(clubNameInput, clubLocationInput, clubOwnerInput,
                                                      new SportsClubKit(clubSponsorInput, colorTop, colorShort),
                                                      teacherInChargeInput);
                break;
            case "league":
                footballClub = new FootballClub(clubNameInput, clubLocationInput, clubOwnerInput,
                                                new SportsClubKit(clubSponsorInput, colorTop, colorShort));
                break;
        }

        allFootballClubs.add(footballClub);
    }
    //**********************************************END OF ADD METHOD*************************************************//



    //***********************************************DELETE METHOD****************************************************//
    @Override
    public FootballClub deleteClub(String clubNameInput) {
        boolean foundFlag = false;
        FootballClub removedClub = null;

        for (FootballClub footballClub : allFootballClubs) {
            if (footballClub.getClubName().equals(clubNameInput)) {
                removedClub = allFootballClubs.remove(allFootballClubs.indexOf(footballClub));
                foundFlag = true;
                break;
            }
        }

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
            System.out.println(foundClub);
        }
    }
    //******************************************END DISPLAY SELECTED CLUB*********************************************//



    //***************************************ADD PLAYED MATCH BETWEEN TWO CLUB****************************************//
    @Override
    public void addPlayedMatch() {
        FootballClub firstTeam;
        FootballClub secondTeam;

        if (allFootballClubs.size() < 2) {
            System.out.println("There isn't enough teams to play a match!");
            return;
        }

        //loop till two unique teams are selected (two same teams cannot play against each other)
        do {
            firstTeam = allFootballClubs.get(PremierLeagueManager.random.nextInt(allFootballClubs.size()));
            secondTeam = allFootballClubs.get(PremierLeagueManager.random.nextInt(allFootballClubs.size()));
        } while (firstTeam.getClubName().equals(secondTeam.getClubName()));

        //TODO add functionality to prevent same matches being played
//        FootballMatch footballMatch;
//        do {
//            footballMatch = new FootballMatch(firstTeam, secondTeam, new Date());
//        } while (footballMatch.getFirstTeam().getClubName().compareTo(footballMatch.getSecondTeam().getClubName()) == 0);

        FootballMatch footballMatch = new FootballMatch(firstTeam, secondTeam, new Date());
        footballMatch.playMatch();
        allMatches.add(footballMatch);
    }
    //*************************************END ADD PLAYED MATCH BETWEEN TWO CLUB**************************************//



    //*********************************************DISPLAY POINTS TABLE***********************************************//
    @Override
    public void displayPointsTable() {
        allFootballClubs.sort(new GoalDifferenceComparator().reversed());
        allFootballClubs.sort(Collections.reverseOrder());

        System.out.println("=======================================================================");
        System.out.println("PREMIER LEAGUE STANDINGS");
        System.out.println("=======================================================================");
        System.out.format("%-20s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s", "Club", "MP", "W", "D", "L", "GF", "GA",
                          "GD", "Pts");
        System.out.println();
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
    @Override
    public void displayMatchResults() {
        allMatches.sort(Collections.reverseOrder());
        System.out.println("=============================================");
        System.out.format("%7s %5s", "", "PREMIER LEAGUE - ALL MATCHES");
        System.out.println();
        System.out.println("=============================================");

        for (FootballMatch footballMatch : allMatches) {
            System.out.format("%7s %5s", "", footballMatch.getMatchDate());
            System.out.println();
            System.out.format("%-20s %1s %1s %20s", footballMatch.getFirstTeam().getClubName(),
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
            System.out.println("[ERROR] ==> No such Football Match was found!");
        } else {
            System.out.println("===================================================================");
            System.out.format("%-27s %-10s %28s", foundFootballMatch.getFirstTeam().getClubName().toUpperCase(),
                    "TEAM STATS", foundFootballMatch.getSecondTeam().getClubName().toUpperCase());
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
    @Override
    public void saveData() {
        List<Object> allData = new ArrayList<>();
        allData.add(allFootballClubs);
        allData.add(allMatches);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(SAVE_PATH + "\\saveFile.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            System.out.print("Now saving data");

            PremierLeagueManager.threeDotSuspense();
            objectOutputStream.writeObject(allData);

            objectOutputStream.close();
            fileOutputStream.close();
            Thread.sleep(500);
            System.out.println("Data saved successfully!");
        } catch (Exception e) {
            System.out.println("[ERROR] ==> Something went wrong while saving the file!");
            e.printStackTrace();
        }
    }
    //**********************************************END SAVE DATA*****************************************************//



    //************************************************LOAD DATA*******************************************************//
    @Override
    public void loadData() {
        List<Object> allData;

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(SAVE_PATH + "\\saveFile.txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            System.out.println("saveFile.txt found!");
            System.out.print("Now loading data");
            PremierLeagueManager.threeDotSuspense();

            allData = (List<Object>) objectInputStream.readObject();

            allFootballClubs = (List<FootballClub>) allData.get(0);
            allMatches = (List<FootballMatch>) allData.get(1);

            System.out.println("Data loaded successfully!");
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("[ERROR] ==> There weren't any files to load!");
        } catch (Exception e) {
            System.out.println("[ERROR] ==> Something went wrong in loading the file!");
            e.printStackTrace();
        }
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
