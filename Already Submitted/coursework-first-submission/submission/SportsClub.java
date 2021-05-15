/*
 * SportsClub
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.io.Serializable;
import java.util.Objects;

/**
 * SportsClub class, which will be used to represent any generic sport club
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class SportsClub implements Serializable {
    private int amountOfPlayers;
    private SportsClubKit kit;
    private String clubLocation;
    private String clubName;
    private String clubOwner;
    private String clubNetWorth;

    /**
     * @return amount of players in a team of a sports club
     */
    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    /**
     * sets number of players of a sports club
     * @param amountOfPlayers - number of players of a sports club
     */
    public void setAmountOfPlayers(int amountOfPlayers) {
        this.amountOfPlayers = amountOfPlayers;
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
        return "SportsClub{" +
                "amountOfPlayers=" + amountOfPlayers +
                ", kit=" + kit +
                ", clubLocation='" + clubLocation + '\'' +
                ", clubName='" + clubName + '\'' +
                ", clubOwner='" + clubOwner + '\'' +
                ", clubNetWorth=" + clubNetWorth +
                '}';
    }

    /**
     * Overridden equals() method, to check for equality between this SportsClub and o
     * Not all attributes are to be checked for equality, since Kit, amountOfPlayers and club net worth 
     * can be same for other clubs
     * @param o - compare this SportsClub with o
     * @return - true/false whether or not they are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub compareWith = (SportsClub) o;
        return
                Objects.equals(clubLocation, compareWith.clubLocation) &&
                Objects.equals(clubName, compareWith.clubName) &&
                Objects.equals(clubOwner, compareWith.clubOwner);
    }

    /**
     * If the above equals method returns true, the objects must have the same hashcode as well
     * @return - a hash value for the objects
     */
    @Override
    public int hashCode() {
        return Objects.hash(amountOfPlayers, kit, clubLocation, clubName, clubOwner, clubNetWorth);
    }
}
