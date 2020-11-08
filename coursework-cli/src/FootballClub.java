public class FootballClub extends SportsClub {
    private static final int NUMBER_OF_PLAYERS = 10;
    private ClubStatistics clubStatistics;

    public FootballClub(String clubName, String clubLocation, String clubOwner, ClubKit kit) {
        super(clubName, clubLocation, clubOwner, kit, NUMBER_OF_PLAYERS);
        this.clubStatistics = new ClubStatistics();
    }

    public ClubStatistics getClubStatistics() {
        return clubStatistics;
    }

    public void setClubStatistics(ClubStatistics clubStatistics) {
        this.clubStatistics = clubStatistics;
    }

    public static int getNumberOfPlayers() {
        return NUMBER_OF_PLAYERS;
    }

    @Override
    public String toString() {
        return "FootballClub{" +
                super.toString() +
                ", clubStatistics=" + clubStatistics +
                '}';
    }


}
