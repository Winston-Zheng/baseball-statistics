/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseballstatistics;

/**
 *
 * @author raybu
 */
public class Pitcher {
    // BRENDEN RAYBURN
    //Create a Pitcher class for the batting statistics for a player on the team.
    // Statistics to be tracked:
    
    // Uniform Number
    private int number;
    // First and Last name
    private String fName;
    private String lName;
    // AB
    private int atBat;
    // R
    private int runs;
    // H
    private int hits;
    // RBI
    private int runsBattedIn;
    // BB
    private int basesOnBalls;
    // SO
    private int strikeOuts;
    // PO
    private int putOuts;
    // A 
    private int assists;
    // LOB
    private int leftOnBase;
    
    public Pitcher(int number, String fName, String lName, int atBat, int runs, int hits, int runsBattedIn, int basesOnBalls, int strikeOuts, int putOuts, int assists, int leftOnBase) {
        this.number = number;
        this.fName = fName;
        this.lName = lName;
        this.atBat = atBat;
        this.runs = runs;
        this.hits = hits;
        this.runsBattedIn = runsBattedIn;
        this.basesOnBalls = basesOnBalls;
        this.strikeOuts = strikeOuts;
        this.putOuts = putOuts;
        this.assists = assists;
        this.leftOnBase = leftOnBase;
    }
    
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
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
    
    
    public int getAtBat() {
        return atBat;
    }
    public void setAtBat(int atBat) {
        this.atBat = atBat;
    }
    
    
    public int getRuns() {
        return runs;
    }
    public void setRuns(int runs) {
        this.runs = runs;
    }
    
    
    public int getHits() {
        return hits;
    }
    public void setHits(int hits) {
        this.hits = hits;
    }
    
    
    public int getRunsBattedIn() {
        return runsBattedIn;
    }
    public void setRunsBattedIn(int runsBattedIn) {
        this.runsBattedIn = runsBattedIn;
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
    
    
    public int getPutOuts() {
        return putOuts;
    }
    public void setPutOuts(int putOuts) {
        this.putOuts = putOuts;
    }
    
    
    public int getAssists() {
        return assists;
    }
    public void setAssists(int assists) {
        this.assists = assists;
    }
    
    
    public int getLeftOnBase() {
        return leftOnBase;
    }
    public void setLeftOnBase(int leftOnBase) {
        this.leftOnBase = leftOnBase;
    }
    
}
