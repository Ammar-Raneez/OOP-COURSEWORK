package coursework.models;

/*
 * FootballClub
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.util.*;

/**
 * FootballClub class, which will be used to represent any football club (sub class of SportsClub)
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class FootballClub extends SportsClub implements Comparable<FootballClub> {
    private static final long serialVersionUID = 2272608864290797607L;
    private static final int NUMBER_OF_PLAYERS = 11;
    private static final Random RANDOM = new Random();
    private final List<Player> ALL_PLAYERS =  new ArrayList<>();
    private FootballClubTotalStatistics footballClubTotalStatistics;

    /**
     * Constructor - takes in values and initializes a Football club object
     * (common attributes initialized through super class)
     * footballClubTotalStatistics - another helper class that contains all club statistics
     * singleMatchFootballClubStatistic - another helper class that will holds statistics of a club of a single match
     * @param clubName - name of club
     * @param clubLocation - location of club
     * @param clubOwner - owner of club
     * @param kit - club kit (a helper class)
     * @param clubWorth - club net worth
\     */
    public FootballClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit,
                        String clubWorth) {
        super(clubName, clubLocation, clubOwner, kit, NUMBER_OF_PLAYERS, clubWorth);
        this.footballClubTotalStatistics = new FootballClubTotalStatistics();
        this.generatePlayers();
    }

    /**
     * @return number of players in a football team
     */
    public static int getNumberOfPlayers() {
        return NUMBER_OF_PLAYERS;
    }

    /**
     * @return this clubs total statistic
     */
    public FootballClubTotalStatistics getFootballClubTotalStatistics() {
        return footballClubTotalStatistics;
    }

    /**
     * sets the total statistics for this club
     * @param footballClubTotalStatistics - this clubs total statistics
     */
    public void setFootballClubTotalStatistics(FootballClubTotalStatistics footballClubTotalStatistics) {
        this.footballClubTotalStatistics = footballClubTotalStatistics;
    }

    /**
     * @return list of all Players
     */
    public List<Player> getAllPlayers() {
        return ALL_PLAYERS;
    }

    /**
     * @return overrun toString() method
     */
    @Override
    public String toString() {
        return "FootballClub{" +
                super.toString() +
                "footballClubTotalStatistics=" + footballClubTotalStatistics +
                ", allPlayers=" + ALL_PLAYERS +
                '}';
    }

    /**
     * Overridden compareTo() method - to sort the clubs based on points
     * Needed for the displayPointsTable() method - to display club with most points on top (descending order of points)
     * @param o - compare this FootballClub with o
     * @return - +ve value if this clubs points > o's points, -ve value if vice-versa, 0 if equal
     */
    @Override
    public int compareTo(FootballClub o) {
        return this.getFootballClubTotalStatistics().getPoints() - o.getFootballClubTotalStatistics().getPoints();
    }

    /**
     * Equals() method called from the super class - SportsClub
     * @param o - compare this club with o
     * @return - t/f on whether the equality is satisfied
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * If the above equals method returns true, the objects must have the same hashcode as well
     * @return - a hash value for the objects
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    /**
     * Private method that sets the randomly generated values provided by playerInformationGeneration()
     * to 11 players (since a football club consists of 11 players) for the club being added
     */
    private void generatePlayers() {
        for (int i=0; i<FootballClub.getNumberOfPlayers(); i++) {
            List<Object> allPlayerInformation = playerInformationGeneration();
            //*each value is casted to their respective data types*//
            ALL_PLAYERS.add(new Player((double) allPlayerInformation.get(0), (String) allPlayerInformation.get(1),
                    (String) allPlayerInformation.get(2), (PlayerStats) allPlayerInformation.get(3),
                    (String) allPlayerInformation.get(4), (String) allPlayerInformation.get(5),
                    (int) allPlayerInformation.get(6)
                    ));
        }
    }

    /**
     * Private helper method that handles the random generation of values for each attribute of a player
     * @return a list of objects consisting of all the necessary values
     */
    private List<Object> playerInformationGeneration() {
        List<String> nationalities = new ArrayList<>(
                Arrays.asList("Germany", "Portugal", "Spain", "Italy", "England", "Argentina", "Brazil",
                              "Netherlands", "Croatia", "Belgium", "Hungary", "Uruguay", "Czech Republic",
                              "Bosnia and Herzegovina", "Denmark", "Ivory Coast", "Sweden", "Paraguay", "Japan",
                              "France", "Cameroon", "Egypt", "Chile", "Colombia", "Wales"
                )
        );

        List<String> commonNames = new ArrayList<>(
                Arrays.asList("James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph",
                              "Thomas", "Charles", "Christopher", "Daniel", "Matthew", "Anthony", "Donald",
                              "Mark", "Paul", "Steven", "Andrew", "Kenneth", "Joshua", "Kevin", "Brian",
                              "George", "Edward", "Ronald", "Timothy", "Jason", "Jeffrey", "Ryan"
                )
        );

        List<String> positions = new ArrayList<>(
                Arrays.asList("GK", "CB", "LB", "RB", "LWB", "RWB", "SW", "DM", "CM", "AM", "LM", "RM",
                              "CF", "S", "SS", "WF"
                )
        );
        List<String> preferredFoot = new ArrayList<>(Arrays.asList("R", "L", "B"));

        double playerHeight = FootballClub.RANDOM.nextInt(250 - 150 + 1) + 150;
        String playerName = commonNames.get(FootballClub.RANDOM.nextInt(commonNames.size()));
        String playerNationality = nationalities.get(FootballClub.RANDOM.nextInt(nationalities.size()));
        PlayerStats playerStats = new PlayerStats(
                FootballClub.RANDOM.nextInt(100 + 1),
                FootballClub.RANDOM.nextInt(100 + 1),
                FootballClub.RANDOM.nextInt(100 + 1),
                FootballClub.RANDOM.nextInt(100 + 1),
                FootballClub.RANDOM.nextInt(100 + 1),
                FootballClub.RANDOM.nextInt(100 + 1),
                FootballClub.RANDOM.nextInt(100 + 1),
                FootballClub.RANDOM.nextInt(100 + 1),
                FootballClub.RANDOM.nextInt(100 + 1),
                FootballClub.RANDOM.nextInt(100 + 1),
                FootballClub.RANDOM.nextInt(100 + 1),
                FootballClub.RANDOM.nextInt(100 + 1),
                FootballClub.RANDOM.nextInt(100 + 1)
        );
        //*call method that calculates and sets overall stat*//
        playerStats.setOverall();

        String playerPosition = positions.get(FootballClub.RANDOM.nextInt(positions.size()));
        String playerPreferredFoot = preferredFoot.get(FootballClub.RANDOM.nextInt(preferredFoot.size()));
        int shirtNumber = FootballClub.RANDOM.nextInt(15 - 1 + 1) + 1;

        //*list consists of all randomly generated values for a specific player*//
        //*values are placed in an Object list, cuz it consists of values of different data types*//
        return Arrays.asList(playerHeight, playerName, playerNationality, playerStats, playerPosition,
                             playerPreferredFoot, shirtNumber);
    }
}
