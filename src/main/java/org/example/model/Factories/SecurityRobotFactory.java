package org.example.model.Factories;

import org.example.model.Robots.Robot;
import org.example.model.Robots.SecurityRobot;

public class SecurityRobotFactory implements Factory {
    public Robot createRobot(Object... args) {
        int id = (Integer) args[0];
        int power = (Integer) args[1];
        int live = (Integer) args[2];
        return new SecurityRobot(id, power, live);
    }
}