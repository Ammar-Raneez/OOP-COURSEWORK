import java.io.Serializable;

public class SportsClub implements Serializable {
    private String clubName;
    private String clubLocation;
    private String clubOwner;
    private SportsClubKit kit;
    private int amountOfPlayers;

    public SportsClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit, int amountOfPlayers) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.clubOwner = clubOwner;
        this.kit = kit;
        this.amountOfPlayers = amountOfPlayers;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    public String getClubOwner() {
        return clubOwner;
    }

    public void setClubOwner(String clubOwner) {
        this.clubOwner = clubOwner;
    }

    public SportsClubKit getKit() {
        return kit;
    }

    public void setKit(SportsClubKit kit) {
        this.kit = kit;
    }

    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public void setAmountOfPlayers(int amountOfPlayers) {
        this.amountOfPlayers = amountOfPlayers;
    }

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
