/*
 * FootballMatch
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import { FootballClub } from './FootballClub.model'
import { SingleMatchFootballClubStatistic } from './SingleMatchFootballClubStatistic.model';

/**
 * FootballMatch interface -> To provide a data type for FootballMatch
 * Will be used to hold FootballMatch objects that are retrieved
 * Will hold the exact same Attributes as the FootballClub object of Java
 * @version 1.x December 5th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
export interface FootballMatch {
    secondTeamSingleMatchStats : SingleMatchFootballClubStatistic;
    firstTeamSingleMatchStats : SingleMatchFootballClubStatistic;
    matchDate : string;
    secondTeam : FootballClub;
    firstTeam : FootballClub;
}
