package coursework.controllers;

import coursework.utils.GoalsForComparator;
import coursework.services.PremierLeagueManager;
import coursework.utils.SeasonRetriever;
import coursework.utils.WinComparator;
import coursework.models.FootballClub;
import play.mvc.*;
import play.libs.Json;
import java.util.*;

public class ClubController extends Controller {
    private final PremierLeagueManager PREMIER_LEAGUE_MANAGER = new PremierLeagueManager();

    public Result returnAllClubs() {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(Collections.reverseOrder());
        ConsoleController.saveData(season);
        return ok(Json.toJson(allClubs));
    }

    public Result returnSelectedClub(String clubName) {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        if (clubName.contains("%")) {
            String[] whitespaceParamSplit = clubName.split("%20");
            clubName = whitespaceParamSplit[0] + " " + whitespaceParamSplit[1];
        }
        FootballClub selectedClub = PREMIER_LEAGUE_MANAGER.displaySelectedClub(clubName);
        ConsoleController.saveData(season);
        return ok(Json.toJson(selectedClub));
    }

    public Result winSortClubFilter() {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(new WinComparator().reversed());
        ConsoleController.saveData(season);
        return ok(Json.toJson(allClubs));
    }

    public Result goalSortClubFilter() {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(new GoalsForComparator().reversed());
        ConsoleController.saveData(season);
        return ok(Json.toJson(allClubs));
    }
}