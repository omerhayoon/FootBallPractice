package org.example;

import java.util.Random;

public class Player {

    private int id;
    private String firstName;
    private String lastName;

    public Player() {
        this.id = Utils.getIdPlayer();
        this.firstName = Utils.FIRST_NAMES.get(new Random().nextInt(Utils.FIRST_NAMES.size()));
        this.lastName = Utils.LAST_NAMES.get(new Random().nextInt(Utils.LAST_NAMES.size()));
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
