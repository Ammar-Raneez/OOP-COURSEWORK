public class PlayerStats {
    private int accuracy;
    private int control;
    private int crossing;
    private int goalKeeperHandling;
    private int goalKeepingPressure;
    private int goalKeepingReaction;
    private int heading;
    private int passing;
    private int overall;
    private int speed;
    private int stamina;
    private int strength;
    private int tackle;
    private int shooting;

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    public int getCrossing() {
        return crossing;
    }

    public void setCrossing(int crossing) {
        this.crossing = crossing;
    }

    public int getGoalKeeperHandling() {
        return goalKeeperHandling;
    }

    public void setGoalKeeperHandling(int goalKeeperHandling) {
        this.goalKeeperHandling = goalKeeperHandling;
    }

    public int getGoalKeepingPressure() {
        return goalKeepingPressure;
    }

    public void setGoalKeepingPressure(int goalKeepingPressure) {
        this.goalKeepingPressure = goalKeepingPressure;
    }

    public int getGoalKeepingReaction() {
        return goalKeepingReaction;
    }

    public void setGoalKeepingReaction(int goalKeepingReaction) {
        this.goalKeepingReaction = goalKeepingReaction;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public int getPassing() {
        return passing;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getTackle() {
        return tackle;
    }

    public void setTackle(int tackle) {
        this.tackle = tackle;
    }

    public int getShooting() {
        return shooting;
    }

    public void setShooting(int shooting) {
        this.shooting = shooting;
    }

    @Override
    public String toString() {
        return "playerStats{" +
                "accuracy=" + accuracy +
                ", control=" + control +
                ", crossing=" + crossing +
                ", goalKeeperHandling=" + goalKeeperHandling +
                ", goalKeepingPressure=" + goalKeepingPressure +
                ", goalKeepingReaction=" + goalKeepingReaction +
                ", heading=" + heading +
                ", passing=" + passing +
                ", overall=" + overall +
                ", speed=" + speed +
                ", stamina=" + stamina +
                ", strength=" + strength +
                ", tackle=" + tackle +
                ", shooting=" + shooting +
                '}';
    }
}
