
package baseballstatistics;


public class Pitcher {
    // BRENDEN RAYBURN
    // Create a Pitcher class for the batting statistics for a player on the team.
    // Statistics to be tracked:
    
    private String gameDate;
    // Uniform Number
    private int uniformNumber;
    // First and Last name
    private String fName;
    private String lName;
    // IP
    private double inningsPitched;
    // H
    private int hits;
    // R
    private int runs;
    // ER
    private int earnedRuns;
    // BB
    private int basesOnBalls;
    // SO
    private int strikeOuts;
    // BF
    private int battersFaced;
    // PC/NP
    private int numOfPitches;
    // ERA
    private double earnedRunAverage;
    
    public Pitcher(String gameDate, int uniformNumber, String fName, 
            String lName, double inningsPitched, int hits, int runs, int earnedRuns,
            int basesOnBalls, int strikeOuts, int battersFaced, int numOfPitches, 
            double earnedRunAverage) {
        this.gameDate = gameDate;
        this.uniformNumber = uniformNumber;
        this.fName = fName;
        this.lName = lName;
        this.inningsPitched = inningsPitched;
        this.hits = hits;
        this.runs = runs;
        this.earnedRuns = earnedRuns;
        this.basesOnBalls = basesOnBalls;
        this.strikeOuts = strikeOuts;
        this.battersFaced = battersFaced;
        this.numOfPitches = numOfPitches;
        this.earnedRunAverage = earnedRunAverage;
    }
    
    public String getGameDate() {
        return gameDate;
    }
    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }
    
    public int getNumber() {
        return uniformNumber;
    }
    public void setNumber(int number) {
        this.uniformNumber = number;
    }
    
    public String getFName() {
        return fName;
    }
    public void setFName(String fName) {
        this.fName = fName;
    }
    
    public String getLName() {
        return lName;
    }
    public void setLName(String lName) {
        this.lName = lName;
    }
    
    public double getInningsPitched() {
        return inningsPitched;
    }
    public void setInningsPitched(double inningsPitched) {
        this.inningsPitched = inningsPitched;
    }
    
    public int getHits() {
        return hits;
    }
    public void setHits(int hits) {
        this.hits = hits;
    }
    
    public int getRuns() {
        return runs;
    }
    public void setRuns(int runs) {
        this.runs = runs;
    }
    
    public int getEarnedRuns() {
        return earnedRuns;
    }
    public void setEarnedRuns(int earnedRuns) {
        this.earnedRuns = earnedRuns;
    }
        
    public int getBasesOnBalls() {
        return basesOnBalls;
    }
    public void setBasesOnBalls(int basesOnBalls) {
        this.basesOnBalls = basesOnBalls;
    }
    
    public int getStrikeOuts() {
        return strikeOuts;
    }
    public void setStrikeOuts(int strikeOuts) {
        this.strikeOuts = strikeOuts;
    }

    public int getBattersFaced() {
        return battersFaced;
    }
    public void setBattersFaced(int battersFaced) {
        this.battersFaced = battersFaced;
    }
    
    public int getNumOfPitches() {
        return numOfPitches;
    }
    public void setNumOfPitches(int numOfPitches) {
        this.numOfPitches = numOfPitches;
    }
    
    public double getEarnedRunAverage() {
        return earnedRunAverage;
    }
    public void setEarnedRunAverage(double earnedRunAverage) {
        this.earnedRunAverage = earnedRunAverage;
    }
}
