import { FootballClubTotalStatistics } from './FootballClubTotalStatistics.model';
import { Player } from './Player.model';
import { SportsClubKit } from './SportsClubKit.model';

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