package controllers;

import play.mvc.*;
import play.libs.Json;
import java.util.*;

public class ClubController extends Controller {
    public Result returnAllClubs() {
        coursework.ConsoleApplication.loadData();
        List<coursework.FootballClub> allClubs = coursework.PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(Collections.reverseOrder());
        return ok(Json.toJson(allClubs));
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
        allClubs.sort(new coursework.GoalDifferenceComparator().reversed());
        return ok(Json.toJson(allClubs));
    }
}