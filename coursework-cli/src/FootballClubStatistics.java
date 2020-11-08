class FootballClubStatistics {
    private int wins;
    private int draws;
    private int defeats;
    private int goalsFor;
    private int goalsAgainst;
    private int points;
    private int matchesPlayed;
    private double overallPossession;
    private int totalRedCards;

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

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public double getOverallPossession() {
        return overallPossession;
    }

    public void setOverallPossession(double overallPossession) {
        this.overallPossession = overallPossession;
    }

    public int getTotalRedCards() {
        return totalRedCards;
    }

    public void setTotalRedCards(int totalRedCards) {
        this.totalRedCards = totalRedCards;
    }

    @Override
    public String toString() {
        return "ClubStatistics{" +
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
