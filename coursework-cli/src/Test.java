import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        premierLeagueManager.addClub();
//        premierLeagueManager.displaySelectedClub();

//        System.out.println(premierLeagueManager.deleteClub());
        premierLeagueManager.saveData();
        premierLeagueManager.loadData();
    }
}
