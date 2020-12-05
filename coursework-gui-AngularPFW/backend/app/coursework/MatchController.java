package coursework;

import coursework.*;
import play.mvc.*;
import play.libs.Json;
import java.util.*;

public class MatchController extends Controller {
    public Result returnAllMatches() {
        ConsoleApplication.loadData();

        List<FootballMatch> allMatches = PremierLeagueManager.getAllMatches();
        Collections.sort(allMatches);
        ConsoleApplication.saveData();
        return ok(Json.toJson(allMatches));
    }

    public Result returnSelectedMatch(Integer arrIndex) {
        ConsoleApplication.loadData();

        FootballMatch selectedMatch = PremierLeagueManager.getAllMatches().get(arrIndex);
        ConsoleApplication.saveData();
        return ok(Json.toJson(selectedMatch));
    }

    public Result returnMatchesOnDate(String obtainedDate) {
        ConsoleApplication.loadData();

        List<FootballMatch> allMatches = PremierLeagueManager.getAllMatches();
        List<FootballMatch> filteredMatches = new ArrayList<>();

        for (FootballMatch footballMatch : allMatches) {
            if (String.valueOf(footballMatch.getMatchDate()).equals(obtainedDate)) {
                filteredMatches.add(footballMatch);
            }
        }
        ConsoleApplication.saveData();
        return ok(Json.toJson(filteredMatches));
    }

    public Result playMatch() {
        ConsoleApplication.loadData();

        try {
            ConsoleApplication.addPlayedMatch();
        } catch (Exception ignored) {}

        ConsoleApplication.saveData();
        List<FootballClub> updatedClubs = PremierLeagueManager.getAllFootballClubs();
        updatedClubs.sort(Collections.reverseOrder());
        return ok(Json.toJson(updatedClubs));
    }
}