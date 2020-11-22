/*
 * SportsClub
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.io.Serializable;
import java.util.Objects;

/**
 * SportsClub class, which will be used to represent any generic sport club (super class of FootballClub)
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public abstract class SportsClub implements Serializable {
    private static final long serialVersionUID = -1367205871791753063L;
    private int amountOfPlayers;
    private SportsClubKit kit;
    private String clubLocation;
    private String clubName;
    private String clubOwner;
    private String clubNetWorth;

    /**
     * initializes a new sports club
     * @param clubName - name of club
     * @param clubLocation - location of club
     * @param clubOwner - owner of club
     * @param kit - club kit (a helper class)
     * @param amountOfPlayers - number of players in a team of a specific sports club
     * @param clubNetWorth - net worth of this club
     */
    public SportsClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit, int amountOfPlayers,
                      String clubNetWorth) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.clubOwner = clubOwner;
        this.kit = kit;
        this.amountOfPlayers = amountOfPlayers;
        this.clubNetWorth = clubNetWorth;
    }

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
     * Overrun equals method to check for any SportsClub equality
     * Not all attributes are checked against, since they can be duplicated
     * This is placed in the super class, so every class that inherits this, get the equals() method
     * @param o - compare this SportsClub with o
     * @return - t/f on whether the equality is satisfied
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub that = (SportsClub) o;
        return Objects.equals(clubLocation, that.clubLocation) &&
                Objects.equals(clubName, that.clubName) &&
                Objects.equals(clubOwner, that.clubOwner);
    }

    /**
     * If the above equals method returns true, the objects must have the same hashcode as well
     * @return - a hash value for the objects
     */
    @Override
    public int hashCode() {
        return Objects.hash(clubLocation, clubName, clubOwner);
    }
}
