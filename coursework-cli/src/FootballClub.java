import java.io.Serializable;

public class FootballClub extends SportsClub implements Serializable {
    private static final int NUMBER_OF_PLAYERS = 10;
    private FootballClubStatistics footballClubStatistics;
    private int matchScore;

    public FootballClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit) {
        super(clubName, clubLocation, clubOwner, kit, NUMBER_OF_PLAYERS);
        this.footballClubStatistics = new FootballClubStatistics();
        this.matchScore = 0;
    }

    public FootballClubStatistics getFootballClubStatistics() {
        return footballClubStatistics;
    }

    public void setFootballClubStatistics(FootballClubStatistics footballClubStatistics) {
        this.footballClubStatistics = footballClubStatistics;
    }

    public static int getNumberOfPlayers() {
        return NUMBER_OF_PLAYERS;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    @Override
    public String toString() {
        return "FootballClub{" +
                super.toString() +
                ", clubStatistics=" + footballClubStatistics +
                '}';
    }
}
