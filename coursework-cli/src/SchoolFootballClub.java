public class SchoolFootballClub extends FootballClub {
    private String teacherInCharge;

    public SchoolFootballClub(String clubName, String clubLocation, String clubOwner, ClubKit kit, String teacherInCharge) {
        super(clubName, clubLocation, clubOwner, kit);
        this.teacherInCharge = teacherInCharge;
    }

    public String getTeacherInCharge() {
        return teacherInCharge;
    }

    public void setTeacherInCharge(String teacherInCharge) {
        this.teacherInCharge = teacherInCharge;
    }

    @Override
    public String toString() {
        return "SchoolFootballClub{" +
                super.toString() +
                ", teacherInCharge='" + teacherInCharge + '\'' +
                '}';
    }
}
