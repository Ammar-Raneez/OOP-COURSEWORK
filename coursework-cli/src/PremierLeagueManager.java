import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PremierLeagueManager implements LeagueManager {
    private static List<SportsClub> sportsClubs = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    @Override
    public void addClub() throws ClassNotFoundException, IllegalAccessException {
        System.out.println("Enter Club's name");
        String clubName = sc.nextLine().toLowerCase();
        System.out.println("Enter club location");
        String clubLocation = sc.nextLine().toLowerCase();
        System.out.println("Enter club owner");
        String clubOwner = sc.nextLine().toLowerCase();
        System.out.println("Enter club sponsor");
        String clubSponsor = sc.nextLine().toLowerCase();

        Color colorTop;
        Color colorShort;
        Field fieldTop;
        Field fieldBottom;

        while (true) {
            try {
                System.out.println("Enter club kit top color");
                String topColorInput = sc.nextLine();
                fieldTop = Class.forName("java.awt.Color").getField(topColorInput.toLowerCase());
                colorTop = (Color) fieldTop.get(null);
                break;
            } catch (NoSuchFieldException e) {
                System.out.println("[ERROR] ==> Please choose one of the valid colors! [black, blue, cyan, gray, green, magenta, orange, " +
                        "pink, red, white, yellow]");
            }
        }

        while (true) {
            try {
                System.out.println("Enter club kit short color");
                String shortColorInput = sc.nextLine();
                fieldBottom = Class.forName("java.awt.Color").getField(shortColorInput.toLowerCase());
                colorShort = (Color) fieldBottom.get(null);
                break;
            } catch (NoSuchFieldException e) {
                System.out.println("[ERROR] ==> Please choose one of the valid colors! [black, blue, cyan, gray, green, magenta, orange, " +
                        "pink, red, white, yellow]");
            }
        }

        SportsClub footballClub = new FootballClub(clubName, clubLocation, clubOwner, new ClubKit(clubSponsor, colorTop, colorShort));
        sportsClubs.add(footballClub);
        System.out.println(footballClub);
        System.out.println(sportsClubs.size());
    }

    @Override
    public SportsClub deleteClub() {

    }

    @Override
    public void displaySelectedClub() {

    }

    @Override
    public void addPlayedMatch() {

    }

    @Override
    public void displayPointsTable() {

    }

    @Override
    public void saveData() {

    }

    @Override
    public void loadData() {

    }
}
