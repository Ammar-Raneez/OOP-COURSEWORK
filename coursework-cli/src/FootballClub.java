public class FootballClub extends SportsClub {
    private static final int NUMBER_OF_PLAYERS = 10;
    private FootballClubStatistics footballClubStatistics;

    public FootballClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit) {
        super(clubName, clubLocation, clubOwner, kit, NUMBER_OF_PLAYERS);
        this.footballClubStatistics = new FootballClubStatistics();
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

    @Override
    public String toString() {
        return "FootballClub{" +
                super.toString() +
                ", clubStatistics=" + footballClubStatistics +
                '}';
    }


}
