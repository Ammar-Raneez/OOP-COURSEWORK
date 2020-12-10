import { FootballClub } from './FootballClub.model'
import { SingleMatchFootballClubStatistic } from './SingleMatchFootballClubStatistic.model';

export interface FootballMatch {
    secondTeamSingleMatchStats : SingleMatchFootballClubStatistic;
    firstTeamSingleMatchStats : SingleMatchFootballClubStatistic;
    matchDate : string;
    secondTeam : FootballClub;
    firstTeam : FootballClub;
}