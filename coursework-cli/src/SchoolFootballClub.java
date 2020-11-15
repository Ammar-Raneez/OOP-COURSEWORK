/*
 * SchoolFootballClub
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.io.Serializable;

/**
 * SchoolFootballClub class, which will be used to represent any school football club (subclass of FootballClub)
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class SchoolFootballClub extends FootballClub implements Serializable {
    private String teacherInCharge;

    /**
     * initializes a new SchoolFootballClub object
     * @param clubName - name of club
     * @param clubLocation - location of club
     * @param clubOwner - owner of club
     * @param kit - club kit (a helper class)
     * @param teacherInCharge - teacher in-charge of this school football club
     */
    public SchoolFootballClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit,
                              String teacherInCharge) {
        super(clubName, clubLocation, clubOwner, kit);
        this.teacherInCharge = teacherInCharge;
    }

    /**
     * @return teacher in-charge of the school club
     */
    public String getTeacherInCharge() {
        return teacherInCharge;
    }

    /**
     * sets teacher in-charge of the school club
     * @param teacherInCharge - teacher in-charge of the school club
     */
    public void setTeacherInCharge(String teacherInCharge) {
        this.teacherInCharge = teacherInCharge;
    }

    /**
     * @return overrun toString() method
     */
    //TODO, improve toString() methods
    @Override
    public String toString() {
        return "SchoolFootballClub{" +
                super.toString() +
                ", teacherInCharge='" + teacherInCharge + '\'' +
                '}';
    }
}
