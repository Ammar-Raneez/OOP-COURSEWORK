import java.awt.*;
import java.io.Serializable;

//helper class to create kits for clubs
class SportsClubKit implements Serializable {
    private String sponsor;
    private Color topColor;
    private Color bottomColor;

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
     * @return overrun toString() method
     */
    @Override
    public String toString() {
        return "ClubKit{" +
                "sponsor='" + sponsor + '\'' +
                ", topColor=" + topColor +
                ", bottomColor=" + bottomColor +
                '}';
    }
}
