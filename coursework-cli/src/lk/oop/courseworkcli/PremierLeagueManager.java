/*
 * PremierLeagueManager
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

package lk.oop.courseworkcli;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

/**
 * PremierLeagueManager class, the main class that will implement all the manipulation methods of a FootballClub
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class PremierLeagueManager implements LeagueManager {
    private static final String SAVE_PATH = "C:\\Users\\Ammuuu\\Downloads\\learning\\UNI\\OOP-Module\\Coursework\\" +
                                            "OOP-COURSEWORK\\saveFile";
    private static List<FootballClub> allFootballClubs = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static Random random = new Random();

    //***************************************PRIVATE COMMON HELPER METHODS********************************************//
    private static String getUserInput(String printSentence) {
        System.out.println(printSentence);
        return sc.nextLine().toLowerCase();
    }

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
    public void addClub() throws ClassNotFoundException, IllegalAccessException {
        String clubTypeInput = PremierLeagueManager.getUserInput("Please enter the type of club " +
                                                                "(University /School /" + "League level)");

        while (true) {
            if (clubTypeInput.equals("university") || clubTypeInput.equals("school") || clubTypeInput.equals("league")) {
                break;
            } else {
                clubTypeInput = PremierLeagueManager.getUserInput("Please specify appropriately! " +
                                                                 "(university/school/league)");
            }
        }

        String clubNameInput = PremierLeagueManager.getUserInput("Enter Club's name");
        boolean clubExists = false;

        while (true) {
            for (FootballClub footballClub : allFootballClubs) {
                if (footballClub.getClubName().equals(clubNameInput)) {
                    clubExists = true;
                    clubNameInput = PremierLeagueManager.getUserInput("[ERROR] ==> " + clubNameInput +
                                                                     " already exists! Please try again");
                    break;
                }
            }
            if (!clubExists) {
                break;
            } else {
                clubExists = false;
            }
        }

        String clubLocationInput = PremierLeagueManager.getUserInput("Enter club location");
        String clubOwnerInput = PremierLeagueManager.getUserInput("Enter club owner");
        String clubSponsorInput = PremierLeagueManager.getUserInput("Enter club sponsor");

        Color colorTop;
        Color colorShort;
        Field fieldTop;
        Field fieldBottom;

        while (true) {
            try {
                String topColorInput = PremierLeagueManager.getUserInput("Enter club kit top color");
                fieldTop = Class.forName("java.awt.Color").getField(topColorInput);
                colorTop = (Color) fieldTop.get(null);
                break;
            } catch (NoSuchFieldException e) {
                System.out.println("[ERROR] ==> Please choose one of the valid colors! [black, blue, cyan, gray, green, "
                                  + "magenta, orange, pink, red, white, yellow]");
            }
        }

        while (true) {
            try {
                String shortColorInput = PremierLeagueManager.getUserInput("Enter club kit short color");
                fieldBottom = Class.forName("java.awt.Color").getField(shortColorInput);
                colorShort = (Color) fieldBottom.get(null);
                break;
            } catch (NoSuchFieldException e) {
                System.out.println("[ERROR] ==> Please choose one of the valid colors! [black, blue, cyan, gray, green, "
                                  + "magenta, orange, pink, red, white, yellow]");
            }
        }

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
        System.out.println(footballClub);
        System.out.println(allFootballClubs.size());
    }
    //**********************************************END OF ADD METHOD*************************************************//



    //***********************************************DELETE METHOD****************************************************//
    @Override
    public SportsClub deleteClub() {
        boolean foundFlag = false;
        SportsClub removedClub = null;
        String clubNameInput = PremierLeagueManager.getUserInput("Enter Club Name you wish to delete");

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
    public void displaySelectedClub() {
        boolean foundFlag = false;
        FootballClub foundClub = null;
        String clubNameInput = PremierLeagueManager.getUserInput("Enter club name to display");

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

        FootballMatch footballMatch = new FootballMatch(firstTeam, secondTeam, new Date());
        footballMatch.playMatch();

        System.out.println(firstTeam);
        System.out.println(secondTeam);
        System.out.println(footballMatch);
    }
    //*************************************END ADD PLAYED MATCH BETWEEN TWO CLUB**************************************//



    @Override
    public void displayPointsTable() {

    }



    //**************************************************SAVE DATA*****************************************************//
    @Override
    public void saveData() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(SAVE_PATH + "\\saveFile.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            System.out.print("Now saving data");

            PremierLeagueManager.threeDotSuspense();

            objectOutputStream.writeObject(allFootballClubs);
            objectOutputStream.close();
            fileOutputStream.close();
            Thread.sleep(500);
            System.out.println("Data saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //**********************************************END SAVE DATA*****************************************************//



    //**************************************************LOAD DATA*****************************************************//
    @Override
    public void loadData() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(SAVE_PATH + "\\saveFile.txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            System.out.print("Now loading data");

            PremierLeagueManager.threeDotSuspense();

            allFootballClubs = (List<FootballClub>) objectInputStream.readObject();
            System.out.println("Data loaded successfully!");
            fileInputStream.close();
            objectInputStream.close();
            System.out.println(allFootballClubs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
