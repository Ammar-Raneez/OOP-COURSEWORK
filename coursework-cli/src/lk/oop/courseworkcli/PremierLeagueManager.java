/*
 * PremierLeagueManager
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

package lk.oop.courseworkcli;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    //************************************************ADD METHOD******************************************************//
    @Override
    public void addClub() throws ClassNotFoundException, IllegalAccessException {
        System.out.println("Please enter the type of club (University /School /League level)");
        String clubTypeInput = sc.nextLine().toLowerCase();

        while (true) {
            if (clubTypeInput.equals("university") || clubTypeInput.equals("school") || clubTypeInput.equals("league")) {
                break;
            } else {
                System.out.println("Please specify appropriately! (university/school/league)");
                clubTypeInput = sc.nextLine().toLowerCase();
            }
        }

        System.out.println("Enter Club's name");
        String clubNameInput = sc.nextLine().toLowerCase();
        boolean clubExists = false;

        while (true) {
            for (FootballClub footballClub : allFootballClubs) {
                if (footballClub.getClubName().equals(clubNameInput)) {
                    System.out.println("[ERROR] ==> " + clubNameInput + " already exists! Please try again");
                    clubExists = true;
                    System.out.println("Enter Club's name");
                    clubNameInput = sc.nextLine().toLowerCase();
                    break;
                }
            }

            if (!clubExists) {
                break;
            } else {
                clubExists = false;
            }
        }

        System.out.println("Enter club location");
        String clubLocationInput = sc.nextLine().toLowerCase();
        System.out.println("Enter club owner");
        String clubOwnerInput = sc.nextLine().toLowerCase();
        System.out.println("Enter club sponsor");
        String clubSponsorInput = sc.nextLine().toLowerCase();

        Color colorTop;
        Color colorShort;
        Field fieldTop;
        Field fieldBottom;

        while (true) {
            try {
                System.out.println("Enter club kit top color");
                String topColorInput = sc.nextLine();
                fieldTop = Class.forName("java.awt.Color").getField(topColorInput.toLowerCase());
                colorTop = (Color) fieldTop.get(null);
                break;
            } catch (NoSuchFieldException e) {
                System.out.println("[ERROR] ==> Please choose one of the valid colors! [black, blue, cyan, gray, green, "
                        + "magenta, orange, pink, red, white, yellow]");
            }
        }

        while (true) {
            try {
                System.out.println("Enter club kit short color");
                String shortColorInput = sc.nextLine();
                fieldBottom = Class.forName("java.awt.Color").getField(shortColorInput.toLowerCase());
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
                System.out.println("Please enter the lecturer in charge");
                String lecturerInChargeInput = sc.nextLine();
                footballClub = new UniversityFootballClub(clubNameInput, clubLocationInput, clubOwnerInput,
                                                          new SportsClubKit(clubSponsorInput, colorTop, colorShort),
                                                          lecturerInChargeInput);
                break;
            case "school":
                System.out.println("Please enter the teacher in charge");
                String teacherInChargeInput = sc.nextLine();
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

        System.out.println("Enter Club Name you wish to delete");
        String ClubNameInput = sc.nextLine().toLowerCase();

        for (FootballClub footballClub : allFootballClubs) {
            if(footballClub.getClubName().equals(ClubNameInput)) {
                removedClub = allFootballClubs.remove(allFootballClubs.indexOf(footballClub));
                foundFlag = true;
                break;
            }
        }

        if(!foundFlag) {
            System.out.println("[ERROR] ==> No Such Club Exists");
            return null;
        } else {
            System.out.println(allFootballClubs.size());
            return removedClub;
        }
    }
    //*********************************************END OF DELETE METHOD***********************************************//



    //******************************************DISPLAY SELECTED CLUB*************************************************//
    @Override
    public void displaySelectedClub() {
        boolean foundFlag = false;
        FootballClub foundClub = null;

        System.out.println("Enter club name to display");
        String clubNameInput = sc.nextLine().toLowerCase();

        for(FootballClub footballClub : allFootballClubs) {
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



    @Override
    public void addPlayedMatch() {

    }

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

            for (int i=0; i<2; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);

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

            for (int i=0; i<2; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);

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
