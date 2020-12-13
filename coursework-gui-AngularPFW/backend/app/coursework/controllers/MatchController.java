package coursework.controllers;

import coursework.services.PremierLeagueManager;
import coursework.models.FootballClub;
import coursework.models.FootballMatch;
import coursework.utils.SeasonRetriever;
import play.mvc.*;
import play.libs.Json;
import java.util.*;

public class MatchController extends Controller {
    public Result returnAllMatches() {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        List<FootballMatch> allMatches = PremierLeagueManager.getAllMatches();
        Collections.sort(allMatches);
        ConsoleController.saveData(season);
        return ok(Json.toJson(allMatches));
    }

    public Result returnSelectedMatch(Integer arrIndex) {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        FootballMatch selectedMatch = PremierLeagueManager.getAllMatches().get(arrIndex);
        ConsoleController.saveData(season);
        return ok(Json.toJson(selectedMatch));
    }

    public Result returnMatchesOnDate(String obtainedDate) {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        List<FootballMatch> allMatches = PremierLeagueManager.getAllMatches();
        List<FootballMatch> filteredMatches = new ArrayList<>();

        for (FootballMatch footballMatch : allMatches) {
            if (String.valueOf(footballMatch.getMatchDate()).equals(obtainedDate)) {
                filteredMatches.add(footballMatch);
            }
        }

        ConsoleController.saveData(season);
        return ok(Json.toJson(filteredMatches));
    }

    public Result playMatch() {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        ConsoleController.addPlayedMatch(season);

        List<FootballClub> updatedClubs = PremierLeagueManager.getAllFootballClubs();
        updatedClubs.sort(Collections.reverseOrder());
        ConsoleController.saveData(season);
        return ok(Json.toJson(updatedClubs));
    }
}