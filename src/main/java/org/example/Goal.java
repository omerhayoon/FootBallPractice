package org.example;

public class Goal {

    private int id;
    private int minute;
    private Player scorer;

    public Goal(int minute, Player scorer) {
        this.id = Utils.getIdGoal();
        this.minute = minute;
        this.scorer = scorer;
    }

    public int getId() {
        return id;
    }

    public int getMinute() {
        return minute;
    }

    public Player getScorer() {
        return scorer;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", minute=" + minute +
                ", scorer=" + scorer +
                '}';
    }
}
