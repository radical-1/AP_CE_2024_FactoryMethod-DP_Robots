package org.example.model.Factories;

import org.example.model.Robots.CleaningRobot;
import org.example.model.Robots.Robot;

public class CleaningRobotFactory implements Factory {
    public Robot createRobot(Object... args) {
        int id = (Integer) args[0];
        int numTasks = (Integer) args[1];
        int[] areas = (int[]) args[2];
        return new CleaningRobot(id, numTasks, areas);
    }
}