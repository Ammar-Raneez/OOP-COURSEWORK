/*
 * SchoolFootballClub
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.io.Serializable;
import java.util.Objects;

/**
 * SchoolFootballClub class, which will be used to represent any school football club (subclass of FootballClub)
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class SchoolFootballClub implements Serializable, Comparable<SchoolFootballClub> {
    private String teacherInCharge;
    private static final int NUMBER_OF_PLAYERS = 11;
    private FootballClubTotalStatistics footballClubTotalStatistics;
    private int amountOfPlayers;
    private SportsClubKit kit;
    private String clubLocation;
    private String clubName;
    private String clubOwner;
    private String clubNetWorth;

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
     * @return number of players in a football team
     */
    public static int getNumberOfPlayers() {
        return NUMBER_OF_PLAYERS;
    }

    /**
     * @return this clubs total statistic
     */
    public FootballClubTotalStatistics getFootballClubTotalStatistics() {
        return footballClubTotalStatistics;
    }

    /**
     * sets the total statistics for this club
     * @param footballClubTotalStatistics - this clubs total statistics
     */
    public void setFootballClubTotalStatistics(FootballClubTotalStatistics footballClubTotalStatistics) {
        this.footballClubTotalStatistics = footballClubTotalStatistics;
    }

    /**
     * @return kit of club
     */
    public SportsClubKit getKit() {
        return kit;
    }

    /**
     * sets a new club kit  of a club
     * @param kit - club kit of a club
     */
    public void setKit(SportsClubKit kit) {
        this.kit = kit;
    }

    /**
     * @return location of club
     */
    public String getClubLocation() {
        return clubLocation;
    }

    /**
     * sets location of a club
     * @param clubLocation - location of club
     */
    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    /**
     * @return name of club
     */
    public String getClubName() {
        return clubName;
    }

    /**
     * sets name of a club
     * @param clubName - name of club
     */
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    /**
     * @return owner of club
     */
    public String getClubOwner() {
        return clubOwner;
    }

    /**
     * sets owner of a club
     * @param clubOwner - owner of club
     */
    public void setClubOwner(String clubOwner) {
        this.clubOwner = clubOwner;
    }

    /**
     * @return club net worth
     */
    public String getClubNetWorth() {
        return clubNetWorth;
    }

    /**
     * sets net worth of a club
     * @param clubNetWorth - net worth of club
     */
    public void setClubNetWorth(String clubNetWorth) {
        this.clubNetWorth = clubNetWorth;
    }

    /**
     * @return overrun toString() method
     */
    //TODO, improve toString() methods
    @Override
    public String toString() {
        return "SchoolFootballClub{" +
                "amountOfPlayers=" + amountOfPlayers +
                ", kit=" + kit +
                ", clubLocation='" + clubLocation + '\'' +
                ", clubName='" + clubName + '\'' +
                ", clubOwner='" + clubOwner + '\'' +
                ", clubNetWorth=" + clubNetWorth +
		"footballClubTotalStatistics=" + footballClubTotalStatistics +
                ", teacherInCharge='" + teacherInCharge + '\'' +
                '}';
    }

    /**
     * Overridden compareTo() method - to sort the clubs based on points
     * @param o - compare this SchoolFootballClub with o
     * @return - +ve value if this clubs points > o's points, -ve value if vice-versa, 0 if equal
     */
    @Override
    public int compareTo(SchoolFootballClub o) {
        return this.getFootballClubTotalStatistics().getPoints() - o.getFootballClubTotalStatistics().getPoints();
    }

    /**
     * Overridden equals() method, to check for equality between this SchoolFootballClub and o
     * Not all attributes are to be checked for equality, since Kit, amountOfPlayers, teacherInCharge and club net worth
     * can be same for other clubs
     * @param o - compare this SchoolFootballClub with o
     * @return - true/false whether or not they are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolFootballClub that = (SchoolFootballClub) o;
        return
                Objects.equals(clubLocation, that.clubLocation) &&
                Objects.equals(clubName, that.clubName) &&
                Objects.equals(clubOwner, that.clubOwner);
    }

    /**
     * If the above equals method returns true, the objects must have the same hashcode as well
     * @return - a hash value for the objects
     */
    @Override
    public int hashCode() {
        return Objects.hash(teacherInCharge, clubLocation, clubName, clubOwner);
    }
}
