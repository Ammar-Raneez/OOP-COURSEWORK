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
 */
export interface FootballMatch {
    secondTeamSingleMatchStats : SingleMatchFootballClubStatistic;
    firstTeamSingleMatchStats : SingleMatchFootballClubStatistic;
    matchDate : string;
    secondTeam : FootballClub;
    firstTeam : FootballClub;
}
