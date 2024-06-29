package org.example.model.Robots;

import java.util.ArrayList;

public class SecurityRobot extends Robot {
    private static ArrayList<SecurityRobot> securityRobots = new ArrayList<>();
    private int power;
    private int live;

    public SecurityRobot(int id, int power, int live) {
        super(id);
        this.power = power;
        this.live = live;
        securityRobots.add(this);
    }

    public static SecurityRobot getSecurityRobotByID(int id) {
        for (SecurityRobot sr : securityRobots) {
            if (sr.getId() == id)
                return sr;
        }
        return null;
    }

    public boolean doWork(int enemyPower) {
        if (enemyPower > power) {
            live--;
            if (live == 0) {
                securityRobots.remove(this);
                robots.remove(this);
            }
            return false;
        } else {
            return true;
        }
    }

    public int getPower() {
        return power;
    }

    public int getLive() {
        return live;
    }
}
