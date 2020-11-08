public interface LeagueManager {
    void addClub() throws ClassNotFoundException, IllegalAccessException;
    SportsClub deleteClub();
    void displaySelectedClub();
    void addPlayedMatch();
    void displayPointsTable();
    void saveData();
    void loadData();
}
