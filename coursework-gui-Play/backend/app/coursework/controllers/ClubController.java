package coursework.controllers;

/*
 * ClubController
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import coursework.utils.GoalsForComparator;
import coursework.services.PremierLeagueManager;
import coursework.utils.SeasonRetriever;
import coursework.utils.WinComparator;
import coursework.models.FootballClub;
import play.mvc.*;
import play.libs.Json;
import java.util.*;

/**
 * ConsoleController class, will handle serving data related to Football clubs
 * @version 1.x December 10th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class ClubController extends Controller {
    private final PremierLeagueManager PREMIER_LEAGUE_MANAGER = new PremierLeagueManager();

    /**
     * Serves all the football clubs in the Premier League
     * @return - Json format of all the football clubs
     */
    public Result returnAllClubs() {
        //*Fetches the season input from the file, parameters weren't being received by Play!*//
        //*From the Console*//
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(Collections.reverseOrder());
        ConsoleController.saveData(season);
        return ok(Json.toJson(allClubs));
    }

    /**
     * Serves a selected football club in the Premier League
     * @param clubName - parameter passed in the path URL, will be an index to obtain the football club
     * at that appropriate index, through array-based indexing (Each club in at an index)
     * @return - Json format of the selected club
     */
    public Result returnSelectedClub(String clubName) {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        //*whitespaces are passed through the url containing "%20"*//
        //*so if it was the case that a club name has two words, separated by a whitespace*//
        //*in order to obtain the actual name, the string has to be separated at the "%20"*//
        if (clubName.contains("%")) {
            String[] whitespaceParamSplit = clubName.split("%20");
            clubName = whitespaceParamSplit[0] + " " + whitespaceParamSplit[1];
        }
        FootballClub selectedClub = PREMIER_LEAGUE_MANAGER.displaySelectedClub(clubName);
        ConsoleController.saveData(season);
        return ok(Json.toJson(selectedClub));
    }

    /**
     * Serves all the clubs sorted by wins in descending order
     * @return - Json format of the sorted clubs
     */
    public Result winSortClubFilter() {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(new WinComparator().reversed());
        ConsoleController.saveData(season);
        return ok(Json.toJson(allClubs));
    }

    /**
     * Serves all the clubs sorted by goalsFor in descending order
     * @return - Json format of the sorted clubs
     */
    public Result goalSortClubFilter() {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(new GoalsForComparator().reversed());
        ConsoleController.saveData(season);
        return ok(Json.toJson(allClubs));
    }
}