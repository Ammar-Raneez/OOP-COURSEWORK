package coursework.controllers;

/*
 * MatchController
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import coursework.services.PremierLeagueManager;
import coursework.models.FootballClub;
import coursework.models.FootballMatch;
import coursework.utils.SeasonRetriever;
import play.mvc.*;
import play.libs.Json;
import java.util.*;

/**
 * MatchController, will handle serving related to football matches
 * @version 1.x December 10th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class MatchController extends Controller {
    /**
     * Serves all the football matches played in the Premier League
     * The same logic is used for all methods -> Matches obtained, any filters are applied, then sorted
     * Then
     * @return - Json format of all the matches
     */
    public Result returnAllMatches() {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        List<FootballMatch> allMatches = PremierLeagueManager.getAllMatches();
        Collections.sort(allMatches);
        ConsoleController.saveData(season);
        return ok(Json.toJson(allMatches));
    }

    /**
     * Serves the selected football match
     * @param arrIndex - parameter passed in the path URL, will be an index to obtain the football match
     * at that appropriate index, through array-based indexing (Each match in at an index)
     * @return - Json format of the selected football match
     */
    public Result returnSelectedMatch(Integer arrIndex) {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        FootballMatch selectedMatch = PremierLeagueManager.getAllMatches().get(arrIndex);
        ConsoleController.saveData(season);
        return ok(Json.toJson(selectedMatch));
    }

    /**
     * Serves all matches of at a specific date
     * @param obtainedDate - date parameter passed in URL
     * @return - Json format of the filtered matches
     */
    public Result returnMatchesOnDate(String obtainedDate) {
        String season = SeasonRetriever.getSeason();
        ConsoleController.loadData(season);
        List<FootballMatch> allMatches = PremierLeagueManager.getAllMatches();
        List<FootballMatch> filteredMatches = new ArrayList<>();

        //*get all the matches and then filter based on date*//
        for (FootballMatch footballMatch : allMatches) {
            if (String.valueOf(footballMatch.getMatchDate()).equals(obtainedDate)) {
                filteredMatches.add(footballMatch);
            }
        }

        ConsoleController.saveData(season);
        return ok(Json.toJson(filteredMatches));
    }

    /**
     * Method that handles playing of a match (clicking on play match button triggers this)
     * @return - Json format of the updatedClubs (The clubs are sent here and not the matches cuz
     * the play match button is in the leaderboard tab, therefore the clubs are what must be
     * updated on the spot)
     */
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