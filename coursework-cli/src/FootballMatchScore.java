import java.io.Serializable;

class FootballMatchScore implements Serializable {
    private int team1;
    private int team2;

    public FootballMatchScore(int team1, int team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public int getTeam1() {
        return team1;
    }

    public void setTeam1(int team1) {
        this.team1 = team1;
    }

    public int getTeam2() {
        return team2;
    }

    public void setTeam2(int team2) {
        this.team2 = team2;
    }

    @Override
    public String toString() {
        return "MatchScore{" +
                "team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }
}
