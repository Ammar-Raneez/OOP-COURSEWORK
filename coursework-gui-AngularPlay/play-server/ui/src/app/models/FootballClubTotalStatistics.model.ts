/*
 * FootballClubTotalStatistics
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

/**
 * FootballClubTotalStatistics interface -> To provide a data type for FootballClubTotalStatistics
 * Will be used to hold FootballClubTotalStatistics objects that are retrieved, that are a part of the FootballCLub interface
 * Will hold the exact same Attributes as the FootballClubTotalStatistics object of Java
 */
export interface FootballClubTotalStatistics {
    defeats : number;
    draws : number;
    goalsAgainst : number;
    goalsFor : number;
    matchesPlayed : number;
    points : number;
    totalRedCards : number;
    totalYellowCards : number;
    wins : number;
    goalDifference : number;
}
