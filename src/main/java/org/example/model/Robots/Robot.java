package org.example.model.Robots;

import java.util.ArrayList;

public abstract class Robot {
    private int id;
    protected static ArrayList<Robot> robots = new ArrayList<>();

    public Robot(int id) {
        this.id = id;
        robots.add(this);
    }

    public static ArrayList<Robot> getRobots() {
        return robots;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
