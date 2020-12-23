/*
 * FootballClub
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import { FootballClubTotalStatistics } from './FootballClubTotalStatistics.model';
import { Player } from './Player.model';
import { SportsClubKit } from './SportsClubKit.model';

/**
 * FootballClub interface -> To provide a data type for FootballClub
 * Will be used to hold FootballClub objects that are retrieved
 * Will hold the exact same Attributes as the FootballClub object of Java
 * Also holds the lecturer and teacher in charge attributes, if in case the club is a university of school club
 * @version 1.x December 5th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
export interface FootballClub {
    allPlayers : Player[];
    amountOfPlayers : number;
    clubLocation : string;
    clubName : string;
    clubOwner : string;
    clubNetWorth : string;
    footballClubTotalStatistics : FootballClubTotalStatistics;
    kit : SportsClubKit;
    lecturerInCharge : string;
    teacherInCharge : string;
}
