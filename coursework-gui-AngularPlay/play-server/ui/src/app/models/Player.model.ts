import { PlayerStats } from './PlayerStats.model';

export interface Player {
    name : string;
    position : string;
    height : number;
    nationality : string;
    shirtNumber : number;
    preferredFoot : string;
    playerStats : PlayerStats;
}