package coursework.models;

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
    private final int AMOUNT_OF_PLAYERS;
    private final SportsClubKit KIT;
    private final String CLUB_LOCATION;
    private final String CLUB_NAME;
    private final String CLUB_OWNER;
    private final String CLUB_NET_WORTH;

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
        this.CLUB_NAME = clubName;
        this.CLUB_LOCATION = clubLocation;
        this.CLUB_OWNER = clubOwner;
        this.KIT = kit;
        this.AMOUNT_OF_PLAYERS = amountOfPlayers;
        this.CLUB_NET_WORTH = clubNetWorth;
    }

    /**
     * @return amount of players in a team of a sports club
     */
    public int getAmountOfPlayers() {
        return AMOUNT_OF_PLAYERS;
    }

//    /**
//     * sets number of players of a sports club
//     * @param amountOfPlayers - number of players of a sports club
//     */
//    public void setAmountOfPlayers(int amountOfPlayers) {
//        this.amountOfPlayers = amountOfPlayers;
//    }

    /**
     * @return kit of club
     */
    public SportsClubKit getKit() {
        return KIT;
    }

//    /**
//     * sets a new club kit  of a club
//     * @param kit - club kit of a club
//     */
//    public void setKit(SportsClubKit kit) {
//        this.kit = kit;
//    }

    /**
     * @return location of club
     */
    public String getClubLocation() {
        return CLUB_LOCATION;
    }

//    /**
//     * sets location of a club
//     * @param clubLocation - location of club
//     */
//    public void setClubLocation(String clubLocation) {
//        this.clubLocation = clubLocation;
//    }

    /**
     * @return name of club
     */
    public String getClubName() {
        return CLUB_NAME;
    }

//    /**
//     * sets name of a club
//     * @param clubName - name of club
//     */
//    public void setClubName(String clubName) {
//        this.clubName = clubName;
//    }

    /**
     * @return owner of club
     */
    public String getClubOwner() {
        return CLUB_OWNER;
    }

//    /**
//     * sets owner of a club
//     * @param clubOwner - owner of club
//     */
//    public void setClubOwner(String clubOwner) {
//        this.clubOwner = clubOwner;
//    }

    /**
     * @return club net worth
     */
    public String getClubNetWorth() {
        return CLUB_NET_WORTH;
    }

//    /**
//     * sets net worth of a club
//     * @param clubNetWorth - net worth of club
//     */
//    public void setClubNetWorth(String clubNetWorth) {
//        this.clubNetWorth = clubNetWorth;
//    }

    /**
     * @return overrun toString() method
     */
    //TODO, improve toString() methods
    @Override
    public String toString() {
        return "SportsClub{" +
                "amountOfPlayers=" + AMOUNT_OF_PLAYERS +
                ", kit=" + KIT +
                ", clubLocation='" + CLUB_LOCATION + '\'' +
                ", clubName='" + CLUB_NAME + '\'' +
                ", clubOwner='" + CLUB_OWNER + '\'' +
                ", clubNetWorth=" + CLUB_NET_WORTH +
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
        return Objects.equals(CLUB_LOCATION, that.CLUB_LOCATION) &&
                Objects.equals(CLUB_NAME, that.CLUB_NAME) &&
                Objects.equals(CLUB_OWNER, that.CLUB_OWNER);
    }

    /**
     * If the above equals method returns true, the objects must have the same hashcode as well
     * @return - a hash value for the objects
     */
    @Override
    public int hashCode() {
        return Objects.hash(CLUB_LOCATION, CLUB_NAME, CLUB_OWNER);
    }
}
