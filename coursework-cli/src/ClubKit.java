import java.awt.*;

class ClubKit {
    private String sponsor;
    private Color topColor;
    private Color bottomColor;

    public ClubKit(String sponsor, Color topColor, Color bottomColor) {
        this.sponsor = sponsor;
        this.topColor = topColor;
        this.bottomColor = bottomColor;
    }

    @Override
    public String toString() {
        return "ClubKit{" +
                "sponsor='" + sponsor + '\'' +
                ", topColor=" + topColor +
                ", bottomColor=" + bottomColor +
                '}';
    }
}
