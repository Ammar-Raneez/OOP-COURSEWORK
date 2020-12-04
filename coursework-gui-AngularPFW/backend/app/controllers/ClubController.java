package controllers;

import play.mvc.*;
import play.libs.Json;
import java.util.*;

public class ClubController extends Controller {
    coursework.PremierLeagueManager leagueManager = new coursework.PremierLeagueManager();

    public Result returnAllClubs() {
        coursework.ConsoleApplication.loadData();
        List<coursework.FootballClub> allClubs = coursework.PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(Collections.reverseOrder());
        return ok(Json.toJson(allClubs));
    }

    public Result returnSelectedClub(String clubName) {
        if (clubName.contains("%")) {
            String[] whitespaceParamSplit = clubName.split("%20");
            clubName = whitespaceParamSplit[0] + " " + whitespaceParamSplit[1];
        }
        coursework.FootballClub selectedClub = leagueManager.displaySelectedClub(clubName);
        return ok(Json.toJson(selectedClub));
    }

    public Result winSortClubFilter() {
//        coursework.ConsoleApplication.loadData();

        List<coursework.FootballClub> allClubs = coursework.PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(new coursework.WinComparator().reversed());
        return ok(Json.toJson(allClubs));
    }

    public Result goalSortClubFilter() {
//        coursework.ConsoleApplication.loadData();

        List<coursework.FootballClub> allClubs = coursework.PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(new coursework.GoalsForComparator().reversed());
        return ok(Json.toJson(allClubs));
    }
}