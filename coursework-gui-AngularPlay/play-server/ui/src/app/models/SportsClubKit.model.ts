/*
 * SportsClubKit
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import { Color } from './Color.model';

/**
 * SportsClubKit interface -> To provide a data type for SportsClubKit
 * Will be used to hold SportsClubKit objects that are retrieved, that are a part of a FootballClub
 * Will hold the exact same Attributes as the SportsClubKit object of Java
 * @version 1.x December 5th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
export interface SportsClubKit {
    sponsor : string;
    topColor : Color;
    bottomColor : Color;
}
