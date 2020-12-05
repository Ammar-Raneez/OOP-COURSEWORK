import { FootballClubTotalStatistics } from './FootballClubTotalStatistics';
import { Player } from './Player';
import { SportsClubKit } from './SportsClubKit';

export class FootballClub {
    allPlayers : Player[];
    amountOfPlayers : number;
    clubLocation : string;
    clubName : string;
    clubOwner : string;
    netWorth : string;
    footballClubTotalStatistics : FootballClubTotalStatistics;
    kit : SportsClubKit;
    lecOrTeachInCharge: string;
}