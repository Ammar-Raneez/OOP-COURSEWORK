import { FootballClub } from './FootballClub'
import { SingleMatchFootballClubStatistic } from './SingleMatchFootballClubStatistic';

export class FootballMatch {
    secondTeamSingleMatchStats : SingleMatchFootballClubStatistic;
    firstTeamSingleMatchStats : SingleMatchFootballClubStatistic;
    matchDate : string;
    secondTeam : FootballClub;
    firstTeam : FootballClub;
}