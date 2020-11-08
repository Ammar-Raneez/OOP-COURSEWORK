public class FootballClub extends SportsClub {
    private static final int NUMBER_OF_PLAYERS = 10;
    private FootballClubStatistics footballClubStatistics;

    public FootballClub(String clubName, String clubLocation, String clubOwner, ClubKit kit) {
        super(clubName, clubLocation, clubOwner, kit, NUMBER_OF_PLAYERS);
        this.footballClubStatistics = new FootballClubStatistics();
    }

    public FootballClubStatistics getClubStatistics() {
        return footballClubStatistics;
    }

    public void setClubStatistics(FootballClubStatistics clubStatistics) {
        this.footballClubStatistics = clubStatistics;
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
