package org.example;

import java.util.List;
import java.util.Random;

public class Team {

    private int id;
    private String name;
    private List<Player> players;
    private int points;
    private int difference;


    public Team(int id, String name, List<Player> players) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.points=0;
        this.difference=0;

    }

    public Player getRandomPlayer(){
        Random random=new Random();
        int numPlayer=random.nextInt(1,15);
        return players.get(numPlayer);
    }
    public void updatePoints(int points){
        this.points+=points;
    }
    public void updateGoalDifference(int num){
        this.difference+=num;

    }

    public Integer getPoints() {
        return points;
    }

    public Integer getDifference() {
        return difference;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", players="+ players+
                '}';
    }
}
