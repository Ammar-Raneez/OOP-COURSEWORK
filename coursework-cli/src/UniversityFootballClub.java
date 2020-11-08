import java.io.Serializable;

public class UniversityFootballClub extends FootballClub implements Serializable {
    private String lecturerInCharge;

    public UniversityFootballClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit, String lecturerInCharge) {
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
        return "UniversityFootballClub{" +
                super.toString() +
                ",lecturerInCharge='" + lecturerInCharge + '\'' +
                '}';
    }
}
