package controllers;

import play.mvc.*;
import play.libs.Json;
import java.util.*;

public class MatchController extends Controller {
    public Result returnAllMatches() {
//        coursework.ConsoleApplication.loadData();
        List<coursework.FootballMatch> allData = coursework.PremierLeagueManager.getAllMatches();
        return ok(Json.toJson(allData));
    }
}