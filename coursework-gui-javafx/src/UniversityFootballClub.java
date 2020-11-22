/*
 * oop.cw.guifx.UniversityFootballClub
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.util.Objects;

/**
 * oop.cw.guifx.UniversityFootballClub class, which will be used to represent any university football club (subclass of oop.cw.guifx.FootballClub)
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class UniversityFootballClub extends FootballClub {
    private String lecturerInCharge;

    /**
     * initializes a new oop.cw.guifx.UniversityFootballClub object
     * @param clubName - name of club
     * @param clubLocation - location of club
     * @param clubOwner - owner of club
     * @param kit - club kit (a helper class)
     * @param clubWorth - club net worth
     * @param lecturerInCharge - lecturer in-charge of this university football club
     */
    public UniversityFootballClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit,
                                  String lecturerInCharge, String clubWorth) {
        super(clubName, clubLocation, clubOwner, kit, clubWorth);
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
        return "oop.cw.guifx.UniversityFootballClub{" +
                super.toString() +
                ",lecturerInCharge='" + lecturerInCharge + '\'' +
                '}';
    }

    /**
     * Equals() method called from the super class - oop.cw.guifx.FootballClub
     * @param o - compare this club with o
     * @return - t/f on whether the equality is satisfied
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * If the above equals method returns true, the objects must have the same hashcode as well
     * @return - a hash value for the objects
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
