package org.example.model.Factories;

import org.example.model.Robots.Robot;

public interface Factory {
    Robot createRobot(Object... args);
}
