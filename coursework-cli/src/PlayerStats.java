import java.io.Serializable;

public class PlayerStats implements Serializable {
    private int accuracy;
    private int control;
    private int crossing;
    private int goalKeeperHandling;
    private int goalKeepingPressure;
    private int goalKeepingReaction;
    private int heading;
    private int passing;
    private int overall;
    private int shooting;
    private int speed;
    private int stamina;
    private int strength;
    private int tackle;

    public PlayerStats(int accuracy, int control, int crossing, int goalKeeperHandling, int goalKeepingPressure,
                       int goalKeepingReaction, int heading, int passing, int shooting, int speed, int stamina,
                       int strength, int tackle) {
        this.accuracy = accuracy;
        this.control = control;
        this.crossing = crossing;
        this.goalKeeperHandling = goalKeeperHandling;
        this.goalKeepingPressure = goalKeepingPressure;
        this.goalKeepingReaction = goalKeepingReaction;
        this.heading = heading;
        this.passing = passing;
        this.shooting = shooting;
        this.speed = speed;
        this.stamina = stamina;
        this.strength = strength;
        this.tackle = tackle;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getControl() {
        return control;
    }

    public int getCrossing() {
        return crossing;
    }

    public int getGoalKeeperHandling() {
        return goalKeeperHandling;
    }

    public int getGoalKeepingPressure() {
        return goalKeepingPressure;
    }

    public int getGoalKeepingReaction() {
        return goalKeepingReaction;
    }

    public int getHeading() {
        return heading;
    }

    public int getPassing() {
        return passing;
    }

    public int getOverall() {
        return overall;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStamina() {
        return stamina;
    }

    public int getStrength() {
        return strength;
    }

    public int getTackle() {
        return tackle;
    }

    public int getShooting() {
        return shooting;
    }

    public void setOverall() {
        this.overall = (this.accuracy + this.control + this.crossing + this.goalKeeperHandling +
                        this.goalKeepingPressure + this.goalKeepingReaction + this.heading + this.passing +
                        this.shooting + this.speed + this.stamina + this.strength + this.tackle) / 13;
    }

    @Override
    public String toString() {
        return "PlayerStats{" +
                "accuracy=" + accuracy +
                ", control=" + control +
                ", crossing=" + crossing +
                ", goalKeeperHandling=" + goalKeeperHandling +
                ", goalKeepingPressure=" + goalKeepingPressure +
                ", goalKeepingReaction=" + goalKeepingReaction +
                ", heading=" + heading +
                ", passing=" + passing +
                ", overall=" + overall +
                ", shooting=" + shooting +
                ", speed=" + speed +
                ", stamina=" + stamina +
                ", strength=" + strength +
                ", tackle=" + tackle +
                '}';
    }
}
