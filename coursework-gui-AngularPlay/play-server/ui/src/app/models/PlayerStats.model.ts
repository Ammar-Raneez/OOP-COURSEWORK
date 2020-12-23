/*
 * PlayerStats
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

/**
 * PlayerStats interface -> To provide a data type for PlayerStats
 * Will be used to hold PlayerStats objects that are retrieved, that are a part of a Player
 * Will hold the exact same Attributes as the PlayerStats object of Java
 */
export interface PlayerStats {
    overall : number;
    tackle : number;
    passing : number;
    stamina : number;
    heading : number;
    shooting : number;
    crossing : number;
    accuracy : number;
    strength : number;
    control : number;
    goalKeeperHandling : number;
    goalKeepingPressure : number;
    goalKeepingReaction : number;
    speed : number;
}
