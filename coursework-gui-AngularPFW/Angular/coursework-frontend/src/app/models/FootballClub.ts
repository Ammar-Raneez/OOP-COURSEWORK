import { FootballClubTotalStatistics } from './FootballClubTotalStatistics';
import { Player } from './Player';
import { SportsClubKit } from './SportsClubKit';

export class FootballClub {
    amountOfPlayers : number;
    kit : SportsClubKit;
    clubLocation : string;
    clubName : string;
    clubOwner : string;
    clubNetWorth : string;
    footballClubTotalStatistics : FootballClubTotalStatistics;
    allPlayers : Player[];
}