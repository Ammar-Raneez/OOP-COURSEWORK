/*
 * SingleMatchFootballClubStatistic
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

/**
 * SingleMatchFootballClubStatistic interface -> To provide a data type for SingleMatchFootballClubStatistic
 * Will be used to hold SingleMatchFootballClubStatistic objects that are retrieved, that are a part of a FootballMatch
 * Will hold the exact same Attributes as the SingleMatchFootballClubStatistic object of Java
 * @version 1.x December 5th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
export interface SingleMatchFootballClubStatistic {
    corners : number;
    fouls : number;
    goals : number;
    offsides : number;
    passes : number;
    passAccuracy : number;
    possession : number;
    redCards : number;
    shots : number;
    shotsOnTarget : number;
    yellowCards : number;
}
