/*
 * Player
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import { PlayerStats } from './PlayerStats.model';

/**
 * Player interface -> To provide a data type for Player
 * Will be used to hold Player objects that are retrieved, that are a part of a FootballClub
 * Will hold the exact same Attributes as the Player object of Java
 */
export interface Player {
    name : string;
    position : string;
    height : number;
    nationality : string;
    shirtNumber : number;
    preferredFoot : string;
    playerStats : PlayerStats;
}
