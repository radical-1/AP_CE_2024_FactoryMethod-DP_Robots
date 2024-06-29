package org.example.model.Factories;

import org.example.model.Robots.DeliveryRobot;
import org.example.model.Robots.Robot;

public class DeliveryRobotFactory implements Factory {
    public Robot createRobot(Object... args) {
        int id = (Integer) args[0];
        String vehicle = (String) args[1];
        return new DeliveryRobot(id, vehicle);
    }
}