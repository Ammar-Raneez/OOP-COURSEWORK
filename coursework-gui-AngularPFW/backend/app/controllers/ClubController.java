package controllers;

import play.mvc.*;
import play.libs.Json;
import java.util.*;

public class ClubController extends Controller {
    public Result returnAllClubs() {
        coursework.ConsoleApplication.loadData();
        List<coursework.FootballClub> allData = coursework.PremierLeagueManager.getAllFootballClubs();
        return ok(Json.toJson(allData));
    }


}