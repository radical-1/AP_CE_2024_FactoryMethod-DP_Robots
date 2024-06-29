package org.example.model.Robots;

import java.util.ArrayList;
import java.util.Collection;

public class CleaningRobot extends Robot {
    private static ArrayList<CleaningRobot> cleaningRobots = new ArrayList<>();
    private int numTask;
    private int[] areas;

    public CleaningRobot(int id, int numTask, int[] areas) {
        super(id);
        this.numTask = numTask;
        this.areas = areas;
        cleaningRobots.add(this);
    }

    public static CleaningRobot getCleaningRobotByID(int id) {
        for (CleaningRobot cr : cleaningRobots) {
            if (cr.getId() == id)
                return cr;
        }
        return null;
    }

    public static ArrayList<CleaningRobot> getCleaningRobots() {
        return cleaningRobots;
    }

    public boolean isRetired() {
        return numTask == 0;
    }

    public boolean isValidArea(int area) {
        for (int a : areas) {
            if (a == area)
                return true;
        }
        return false;
    }

    public void doWork() {
        numTask--;
    }

    public int getNumTasks() {
        return numTask;
    }

    public int[] getAreas() {
        return areas;
    }
}
