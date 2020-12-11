package coursework.controllers;

import coursework.utils.GoalsForComparator;
import coursework.services.PremierLeagueManager;
import coursework.utils.WinComparator;
import coursework.models.FootballClub;
import play.mvc.*;
import play.libs.Json;
import java.util.*;

public class ClubController extends Controller {
    private final PremierLeagueManager LEAGUE_MANAGER = new PremierLeagueManager();

    public Result returnAllClubs() {
        ConsoleController.loadData();
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(Collections.reverseOrder());
        ConsoleController.saveData();
        return ok(Json.toJson(allClubs));
    }

    public Result returnSelectedClub(String clubName) {
        ConsoleController.loadData();
        if (clubName.contains("%")) {
            String[] whitespaceParamSplit = clubName.split("%20");
            clubName = whitespaceParamSplit[0] + " " + whitespaceParamSplit[1];
        }
        FootballClub selectedClub = LEAGUE_MANAGER.displaySelectedClub(clubName);
        ConsoleController.saveData();
        return ok(Json.toJson(selectedClub));
    }

    public Result winSortClubFilter() {
        ConsoleController.loadData();
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(new WinComparator().reversed());
        ConsoleController.saveData();
        return ok(Json.toJson(allClubs));
    }

    public Result goalSortClubFilter() {
        ConsoleController.loadData();
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(new GoalsForComparator().reversed());
        ConsoleController.saveData();
        return ok(Json.toJson(allClubs));
    }
}