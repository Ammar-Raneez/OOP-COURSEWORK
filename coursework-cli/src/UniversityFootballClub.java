public class UniversityFootballClub extends FootballClub {
    private String lecturerInCharge;

    public UniversityFootballClub(String clubName, String clubLocation, String clubOwner, ClubKit kit, String lecturerInCharge) {
        super(clubName, clubLocation, clubOwner, kit);
        this.lecturerInCharge = lecturerInCharge;
    }

    public String getLecturerInCharge() {
        return lecturerInCharge;
    }

    public void setLecturerInCharge(String lecturerInCharge) {
        this.lecturerInCharge = lecturerInCharge;
    }

    @Override
    public String toString() {
        return "This UniversityFootballClub Object{" +
                super.toString() +
                ",lecturerInCharge='" + lecturerInCharge + '\'' +
                '}';
    }
}
