package controllers;

import play.mvc.*;
import play.libs.Json;
import java.util.*;

public class TestController extends Controller {
    public Result test() {
        coursework.ConsoleApplication.loadData();
        List<coursework.FootballClub> allData = coursework.PremierLeagueManager.getAllFootballClubs();
        return ok(Json.toJson(allData));
    }
}