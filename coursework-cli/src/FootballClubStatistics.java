import java.io.Serializable;

//helper class that holds statistics for a club
class FootballClubStatistics implements Serializable {
    private int wins;
    private int draws;
    private int defeats;
    private int goalsFor;
    private int goalsAgainst;
    private int points;
    private int matchesPlayed;
    private double overallPossession;
    private int totalRedCards;

    /**
     * initializes a club statistic object
     * all attributes initialized to 0, since a new club will not have any record
     */
    public FootballClubStatistics() {
        this.wins = 0;
        this.draws = 0;
        this.defeats = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
        this.points = 0;
        this.matchesPlayed = 0;
        this.overallPossession = 0;
        this.totalRedCards = 0;
    }

    /**
     * @return total wins of this club
     */
    public int getWins() {
        return wins;
    }

    /**
     * sets wins of this club
     * @param wins - number of wins of this club
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * @return total draws of this club
     */
    public int getDraws() {
        return draws;
    }

    /**
     * sets draws of this club
     * @param draws - number of draws of this club
     */
    public void setDraws(int draws) {
        this.draws = draws;
    }

    /**
     * @return total defeats of this club
     */
    public int getDefeats() {
        return defeats;
    }

    /**
     * sets defeats of this club
     * @param defeats - number of defeats of this club
     */
    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    /**
     * @return total goals scored by this club
     */
    public int getGoalsFor() {
        return goalsFor;
    }

    /**
     * sets goals scored by this club
     * @param goalsFor - number of goals scored by this club
     */
    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    /**
     * @return total goals scored against this club
     */
    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    /**
     * sets goals scored against this club
     * @param goalsAgainst - total number of goals scored against this club
     */
    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    /**
     * @return total points of this club
     */
    public int getPoints() {
        return points;
    }

    /**
     * sets points obtained by this club
     * @param points - total number of points obtained by this club
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return total matches played by this club
     */
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    /**
     * sets matches played by this club
     * @param matchesPlayed - total number of matches played by this club
     */
    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    /**
     * @return average overall possession of this club
     */
    public double getOverallPossession() {
        return overallPossession;
    }

    /**
     * sets overall possession of this club
     * @param overallPossession - average overall possession of this club
     */
    public void setOverallPossession(double overallPossession) {
        this.overallPossession = overallPossession;
    }

    /**
     * @return total red cards obtained by this club
     */
    public int getTotalRedCards() {
        return totalRedCards;
    }

    /**
     * sets red cards obtained by this club
     * @param totalRedCards - total number of red cards obtained by this club
     */
    public void setTotalRedCards(int totalRedCards) {
        this.totalRedCards = totalRedCards;
    }

    /**
     * @return overrun toString() method
     */
    @Override
    public String toString() {
        return "FootballClubStatistics{" +
                "wins=" + wins +
                ", draws=" + draws +
                ", defeats=" + defeats +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                ", points=" + points +
                ", matchesPlayed=" + matchesPlayed +
                ", overallPossession=" + overallPossession +
                ", totalRedCards=" + totalRedCards +
                '}';
    }
}
