import java.io.Serializable;
import java.util.Date;

class FootballMatch implements Serializable {
    private FootballClub footballClub1;
    private FootballClub footballClub2;
    private Date matchDate;

    public FootballMatch(FootballClub footballClub1, FootballClub footballClub2, Date matchDate) {
        this.footballClub1 = footballClub1;
        this.footballClub2 = footballClub2;
        this.matchDate = matchDate;
    }

    public int getFootballClub1Score() {
        return this.footballClub1.getMatchScore();
    }

    public void setFootballClub1Score(int footballClub1Score) {
        this.footballClub1.setMatchScore(footballClub1Score);
    }

    public int getFootballClub2Score() {
        return this.footballClub2.getMatchScore();
    }

    public void setFootballClub2Score(int footballClub2Score) {
        this.footballClub2.setMatchScore(footballClub2Score);
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    @Override
    public String toString() {
        return "FootballMatch{" +
                "footballClub1=[" + footballClub1.getClubName() + " Score=" + footballClub1.getMatchScore() +
                "], footballClub2=[" + footballClub2.getClubName() + " Score=" + footballClub2.getMatchScore() +
                "], matchDate=" + matchDate +
                '}';
    }
}
