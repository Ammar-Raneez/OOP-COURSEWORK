package oop.cw.guifx;

/*
 * SportsClubKit
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.awt.*;
import java.io.Serializable;

/**
 * SportsClubKit class, which will be used to represent any generic sports club kit
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
class SportsClubKit implements Serializable {
    private Color bottomColor;
    private String sponsor;
    private Color topColor;

    /**
     * initializes a sports club kit
     * @param sponsor - sponsor of the club
     * @param topColor - t-shirt color
     * @param bottomColor - short color
     */
    public SportsClubKit(String sponsor, Color topColor, Color bottomColor) {
        this.sponsor = sponsor;
        this.topColor = topColor;
        this.bottomColor = bottomColor;
    }

    /**
     * @return bottom color
     */
    public Color getBottomColor() {
        return bottomColor;
    }

    /**
     * @return sponsor
     */
    public String getSponsor() {
        return sponsor;
    }

    /**
     * @return top color
     */
    public Color getTopColor() {
        return topColor;
    }

    /**
     * @return overrun toString() method
     */
    //TODO, improve toString() methods
    @Override
    public String toString() {
        return "ClubKit{" +
                "sponsor='" + sponsor + '\'' +
                ", topColor=" + topColor +
                ", bottomColor=" + bottomColor +
                '}';
    }
}
