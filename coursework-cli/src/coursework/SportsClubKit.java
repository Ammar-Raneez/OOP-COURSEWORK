package coursework;

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
    private final Color BOTTOM_COLOR;
    private final String SPONSOR;
    private final Color TOP_COLOR;

    /**
     * initializes a sports club kit
     * @param sponsor - sponsor of the club
     * @param topColor - t-shirt color
     * @param bottomColor - short color
     */
    public SportsClubKit(String sponsor, Color topColor, Color bottomColor) {
        this.SPONSOR = sponsor;
        this.TOP_COLOR = topColor;
        this.BOTTOM_COLOR = bottomColor;
    }

    /**
     * @return bottom color
     */
    public Color getBottomColor() {
        return BOTTOM_COLOR;
    }

    /**
     * @return sponsor
     */
    public String getSponsor() {
        return SPONSOR;
    }

    /**
     * @return top color
     */
    public Color getTopColor() {
        return TOP_COLOR;
    }

    /**
     * @return overrun toString() method
     */
    //TODO, improve toString() methods
    @Override
    public String toString() {
        return "ClubKit{" +
                "sponsor='" + SPONSOR + '\'' +
                ", topColor=" + TOP_COLOR +
                ", bottomColor=" + BOTTOM_COLOR +
                '}';
    }
}
