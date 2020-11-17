public class Player {
    private double height;
    private String name;
    private String nationality;
    private PlayerStats playerStats;
    private String position;
    private String preferredFoot;
    private int shirtNumber;

    public Player(double height, String name, String nationality, PlayerStats playerStats, String position,
                  String preferredFoot, int shirtNumber) {
        this.height = height;
        this.name = name;
        this.nationality = nationality;
        this.playerStats = playerStats;
        this.position = position;
        this.preferredFoot = preferredFoot;
        this.shirtNumber = shirtNumber;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(PlayerStats playerStats) {
        this.playerStats = playerStats;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPreferredFoot() {
        return preferredFoot;
    }

    public void setPreferredFoot(String preferredFoot) {
        this.preferredFoot = preferredFoot;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    @Override
    public String toString() {
        return "Player{" +
                "height=" + height +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", playerStats=" + playerStats +
                ", position='" + position + '\'' +
                ", preferredFoot='" + preferredFoot + '\'' +
                ", shirtNumber=" + shirtNumber +
                '}';
    }
}
