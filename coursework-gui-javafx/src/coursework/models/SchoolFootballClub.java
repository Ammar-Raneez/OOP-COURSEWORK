package coursework.models;

/*
 * SchoolFootballClub
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.util.Objects;

/**
 * SchoolFootballClub class, which will be used to represent any school football club (subclass of FootballClub)
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class SchoolFootballClub extends FootballClub {
    private final String TEACHER_IN_CHARGE;

    /**
     * initializes a new SchoolFootballClub object
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
        this.TEACHER_IN_CHARGE = teacherInCharge;
    }

    /**
     * @return teacher in-charge of the school club
     */
    public String getTeacherInCharge() {
        return TEACHER_IN_CHARGE;
    }

//    /**
//     * sets teacher in-charge of the school club
//     * @param teacherInCharge - teacher in-charge of the school club
//     */
//    public void setTeacherInCharge(String teacherInCharge) {
//        this.teacherInCharge = teacherInCharge;
//    }

    /**
     * @return overrun toString() method
     */
    @Override
    public String toString() {
        return "SchoolFootballClub{" +
                super.toString() +
                ", teacherInCharge='" + TEACHER_IN_CHARGE + '\'' +
                '}';
    }

    /**
     * Equals() method called from the super class - FootballClub
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
