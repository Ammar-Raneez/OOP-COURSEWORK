package lk.oop.courseworkcli;

import java.io.Serializable;

//super class that can hold any sport club
public class SportsClub implements Serializable {
    private int amountOfPlayers;
    private SportsClubKit kit;
    private String clubLocation;
    private String clubName;
    private String clubOwner;

    /**
     * initializes a new sports club
     * @param clubName - name of club
     * @param clubLocation - location of club
     * @param clubOwner - owner of club
     * @param kit - club kit (a helper class)
     * @param amountOfPlayers - number of players in a team of a specific sports club
     */
    public SportsClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit, int amountOfPlayers) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.clubOwner = clubOwner;
        this.kit = kit;
        this.amountOfPlayers = amountOfPlayers;
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
     * @return overrun toString() method
     */
    @Override
    public String toString() {
        return "SportsClub{" +
                "clubName='" + clubName + '\'' +
                ", clubLocation='" + clubLocation + '\'' +
                ", clubOwner='" + clubOwner + '\'' +
                ", kit=" + kit +
                ", amountOfPlayers=" + amountOfPlayers +
                '}';
    }
}
