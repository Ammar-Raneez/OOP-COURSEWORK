package lk.oop.courseworkcli;

public class Test {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        LeagueManager premierLeagueManager = new PremierLeagueManager();
//        premierLeagueManager.addClub();
//        premierLeagueManager.addClub();
//        premierLeagueManager.displaySelectedClub();

//        System.out.println(premierLeagueManager.deleteClub());
        premierLeagueManager.saveData();
        premierLeagueManager.loadData();
    }
}
