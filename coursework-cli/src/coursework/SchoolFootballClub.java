package coursework;/*
 * oop.cw.SchoolFootballClub
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.util.Objects;

/**
 * oop.cw.SchoolFootballClub class, which will be used to represent any school football club (subclass of oop.cw.FootballClub)
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class SchoolFootballClub extends FootballClub {
    private String teacherInCharge;

    /**
     * initializes a new oop.cw.SchoolFootballClub object
     * @param clubName - name of club
     * @param clubLocation - location of club
     * @param clubOwner - owner of club
     * @param kit - club kit (a helper class)
     * @param clubWorth - club net worth
     * @param teacherInCharge - teacher in-charge of this school football club
     */
    public SchoolFootballClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit,
                              String teacherInCharge, String clubWorth) {
        super(clubName, clubLocation, clubOwner, kit, clubWorth);
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
        return "oop.cw.SchoolFootballClub{" +
                super.toString() +
                ", teacherInCharge='" + teacherInCharge + '\'' +
                '}';
    }

    /**
     * Equals() method called from the super class - oop.cw.FootballClub
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
