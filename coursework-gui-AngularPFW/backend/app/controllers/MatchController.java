package controllers;

import play.mvc.*;
import play.libs.Json;
import java.util.*;
import java.util.regex.Pattern;

public class MatchController extends Controller {
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
//    public coursework.PremierLeagueManager premierLeagueManager = new coursework.PremierLeagueManager();
    public Result returnAllMatches() {
//        coursework.ConsoleApplication.loadData();
        List<coursework.FootballMatch> allMatches = coursework.PremierLeagueManager.getAllMatches();
        Collections.sort(allMatches);
        return ok(Json.toJson(allMatches));
    }

    public Result returnSelectedMatch(Integer arrIndex) {
//        coursework.ConsoleApplication.loadData();
        coursework.FootballMatch selectedMatch = coursework.PremierLeagueManager.getAllMatches().get(arrIndex);
        return ok(Json.toJson(selectedMatch));
    }

    public Result returnMatchesOnDate(String obtainedDate) {
        coursework.ConsoleApplication.loadData();
        List<coursework.FootballMatch> allMatches = coursework.PremierLeagueManager.getAllMatches();
        List<coursework.FootballMatch> filteredMatches = new ArrayList<>();

        for (coursework.FootballMatch footballMatch : allMatches) {
            if (String.valueOf(footballMatch.getMatchDate()).equals(obtainedDate)) {
                filteredMatches.add(footballMatch);
            }
        }

        return ok(Json.toJson(filteredMatches));
    }
}