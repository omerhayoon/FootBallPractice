package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Match {

    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private List<Goal> goals;
    private int goalHomaTeam;
    private int goalAwayTeam;
    private String endGame;



    public Match(Team homeTeam, Team awayTeam) {
        this.id=Utils.idMatch();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.goals = new ArrayList<>();
    }
    public void setGoals(int min){
        Random random= new Random();
        int gol=random.nextInt(2);
        if(gol==1){
            int team=random.nextInt(2);
            if(team==0){
                goals.add(new Goal(min,homeTeam.getRandomPlayer()));
                goalHomaTeam++;
            }else{
                goals.add(new Goal(min,awayTeam.getRandomPlayer()));
                goalAwayTeam++;
            }
        }
    }
    public String startMatch(){

            System.out.println("Id Match: "+this.id+ " Home Team: "+ this.homeTeam.getName()+ " VS "+ "Away Team: " +this.awayTeam.getName());
        IntStream.range(0,10).forEach(value -> { // סופר עד 10
            try {
                Thread.sleep(2); // עתירה של שניה בספירה לאחור
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(10-value);
            setGoals(value); //מכניס גולים
        });

                System.out.println(" Home Team: "+ this.goalHomaTeam+ " VS "+ "Away Team: " +this.goalAwayTeam);
                this.endGame= " Home Team: "+ this.goalHomaTeam+ " VS "+ "Away Team: " +this.goalAwayTeam;

                if(this.goalHomaTeam>this.goalAwayTeam){
                    this.homeTeam.updatePoints(3);

                }else if (this.goalHomaTeam<this.goalAwayTeam){
                    this.awayTeam.updatePoints(3);
                }else {
                    this.homeTeam.updatePoints(0);
                    this.awayTeam.updatePoints(0);
                }
                this.homeTeam.updateGoalDifference(this.goalHomaTeam-this.goalAwayTeam);
                this.awayTeam.updateGoalDifference(this.goalAwayTeam-this.goalHomaTeam);



            return " Home Team: "+ this.goalAwayTeam+ " VS "+ "Away Team: " +this.goalAwayTeam;

    }

    public List<Goal> getGoals() {
        return goals;
    }

    public String getEndGame() {
        return endGame;
    }

    public int getId() {
        return id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", goals=" + goals +
                ", goalHomaTeam=" + goalHomaTeam +
                ", goalAwayTeam=" + goalAwayTeam +
                ", endGame='" + endGame + '\'' +
                '}';
    }
}
