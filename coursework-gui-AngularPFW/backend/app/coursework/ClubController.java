package coursework;

import play.mvc.*;
import play.libs.Json;
import java.util.*;

public class ClubController extends Controller {
    private final PremierLeagueManager LEAGUE_MANAGER = new PremierLeagueManager();

    public Result returnAllClubs() {
        ConsoleApplication.loadData();
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(Collections.reverseOrder());
        ConsoleApplication.saveData();
        return ok(Json.toJson(allClubs));
    }

    public Result returnSelectedClub(String clubName) {
        ConsoleApplication.loadData();
        if (clubName.contains("%")) {
            String[] whitespaceParamSplit = clubName.split("%20");
            clubName = whitespaceParamSplit[0] + " " + whitespaceParamSplit[1];
        }
        FootballClub selectedClub = LEAGUE_MANAGER.displaySelectedClub(clubName);
        ConsoleApplication.saveData();
        return ok(Json.toJson(selectedClub));
    }

    public Result winSortClubFilter() {
        ConsoleApplication.loadData();
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(new WinComparator().reversed());
        ConsoleApplication.saveData();
        return ok(Json.toJson(allClubs));
    }

    public Result goalSortClubFilter() {
        ConsoleApplication.loadData();
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(new GoalsForComparator().reversed());
        ConsoleApplication.saveData();
        return ok(Json.toJson(allClubs));
    }
}