package coursework.controllers;

import coursework.services.PremierLeagueManager;
import coursework.models.FootballClub;
import coursework.models.FootballMatch;
import play.mvc.*;
import play.libs.Json;
import java.util.*;

public class MatchController extends Controller {
    public Result returnAllMatches() {
        ConsoleController.loadData();
        List<FootballMatch> allMatches = PremierLeagueManager.getAllMatches();
        Collections.sort(allMatches);
        ConsoleController.saveData();
        return ok(Json.toJson(allMatches));
    }

    public Result returnSelectedMatch(Integer arrIndex) {
        ConsoleController.loadData();
        FootballMatch selectedMatch = PremierLeagueManager.getAllMatches().get(arrIndex);
        ConsoleController.saveData();
        return ok(Json.toJson(selectedMatch));
    }

    public Result returnMatchesOnDate(String obtainedDate) {
        ConsoleController.loadData();
        List<FootballMatch> allMatches = PremierLeagueManager.getAllMatches();
        List<FootballMatch> filteredMatches = new ArrayList<>();

        for (FootballMatch footballMatch : allMatches) {
            if (String.valueOf(footballMatch.getMatchDate()).equals(obtainedDate)) {
                filteredMatches.add(footballMatch);
            }
        }
        ConsoleController.saveData();
        return ok(Json.toJson(filteredMatches));
    }

    public Result playMatch() {
        ConsoleController.loadData();

        try {
            ConsoleController.addPlayedMatch();
        } catch (Exception ignored) {}


        List<FootballClub> updatedClubs = PremierLeagueManager.getAllFootballClubs();
        updatedClubs.sort(Collections.reverseOrder());
        ConsoleController.saveData();
        return ok(Json.toJson(updatedClubs));
    }
}