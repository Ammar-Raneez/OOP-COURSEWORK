/*
 * UniversityFootballClub
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.io.Serializable;

/**
 * UniversityFootballClub class, which will be used to represent any university football club (subclass of FootballClub)
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class UniversityFootballClub extends FootballClub implements Serializable {
    private String lecturerInCharge;

    /**
     * initializes a new UniversityFootballClub object
     * @param clubName - name of club
     * @param clubLocation - location of club
     * @param clubOwner - owner of club
     * @param kit - club kit (a helper class)
     * @param lecturerInCharge - lecturer in-charge of this university football club
     */
    public UniversityFootballClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit,
                                  String lecturerInCharge) {
        super(clubName, clubLocation, clubOwner, kit);
        this.lecturerInCharge = lecturerInCharge;
    }

    /**
     * @return lecturer in-charge of the university club
     */
    public String getLecturerInCharge() {
        return lecturerInCharge;
    }

    /**
     * sets lecturer in-charge of the university club
     * @param lecturerInCharge - teacher in-charge of the university club
     */
    public void setLecturerInCharge(String lecturerInCharge) {
        this.lecturerInCharge = lecturerInCharge;
    }

    /**
     * @return overrun toString() method
     */
    //TODO, improve toString() methods
    @Override
    public String toString() {
        return "UniversityFootballClub{" +
                super.toString() +
                ",lecturerInCharge='" + lecturerInCharge + '\'' +
                '}';
    }
}
