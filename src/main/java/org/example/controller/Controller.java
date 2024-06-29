package org.example.controller;

import org.example.model.Factories.*;
import org.example.model.Robots.CleaningRobot;
import org.example.model.Robots.DeliveryRobot;
import org.example.model.Robots.Robot;
import org.example.model.Robots.SecurityRobot;
import org.example.view.Regex;

import java.util.Arrays;

public class Controller {
    public static void createSecurityRobot(String input) {
        int id = Integer.parseInt(Regex.CREATE_SECURITY_ROBOT.getGroup(input, "id"));
        int power = Integer.parseInt(Regex.CREATE_SECURITY_ROBOT.getGroup(input, "power"));
        int live = Integer.parseInt(Regex.CREATE_SECURITY_ROBOT.getGroup(input, "live"));
        SecurityRobotFactory securityRobotFactory = new SecurityRobotFactory();
        securityRobotFactory.createRobot(id, power, live);
    }

    public static void createDeliveryRobot(String input) {
        int id = Integer.parseInt(Regex.CREATE_DELIVERY_ROBOT.getGroup(input, "id"));
        String vehicle = Regex.CREATE_DELIVERY_ROBOT.getGroup(input, "vehicle");
        DeliveryRobotFactory deliveryRobotFactory = new DeliveryRobotFactory();
        deliveryRobotFactory.createRobot(id, vehicle);
    }

    public static void createCleaningRobot(String input) {
        int id = Integer.parseInt(Regex.CREATE_CLEANING_ROBOT.getGroup(input, "id"));
        int numTasks = Integer.parseInt(Regex.CREATE_CLEANING_ROBOT.getGroup(input, "numTask"));
        String[] areas = Regex.CREATE_CLEANING_ROBOT.getGroup(input, "areas").split("\\s+");
        int[] areasInt = new int[areas.length];
        for (int i = 0; i < areas.length; i++) {
            areasInt[i] = Integer.parseInt(areas[i]);
        }
        CleaningRobotFactory cleaningRobotFactory = new CleaningRobotFactory();
        cleaningRobotFactory.createRobot(id, numTasks, areasInt);
    }

    public static String deliver(String input) {
        int id = Integer.parseInt(Regex.DELIVER.getGroup(input, "id"));
        DeliveryRobot dr = DeliveryRobot.getDeliveryRobotByID(id);
        if (dr == null) return "invalid robot id";

        return "delivery robot " + dr.getId() + " sent the pocket by " + dr.getVehicle();
    }

    public static String clean(String input) {
        int id = Integer.parseInt(Regex.CLEAN.getGroup(input, "id"));
        int area = Integer.parseInt(Regex.CLEAN.getGroup(input, "area"));
        CleaningRobot cr = CleaningRobot.getCleaningRobotByID(id);
        if (cr == null) return "invalid robot id";
        if (!cr.isValidArea(area)) return "invalid area";
        if (cr.isRetired()) return "this robot is retired";
        cr.doWork();
        return "cleaning robot " + cr.getId() + " cleaned the area";
    }

    public static String performTask(String input) {
        int id = Integer.parseInt(Regex.PERFORM.getGroup(input, "id"));
        SecurityRobot sr = SecurityRobot.getSecurityRobotByID(id);

        if (sr == null) return "invalid robot id";

        return "security robot is monitoring";
    }

    public static String attack(String input) {
        int id = Integer.parseInt(Regex.ATTACK.getGroup(input, "id"));
        int enemyPower = Integer.parseInt(Regex.ATTACK.getGroup(input, "enemyPower"));
        SecurityRobot sr = SecurityRobot.getSecurityRobotByID(id);
        if (sr == null) return "invalid robot id";
        if (sr.doWork(enemyPower)) return "attack was successful";
        else return "attack was unsuccessful";
    }

    public static String getAll() {
        StringBuilder sb = new StringBuilder();
        for (Robot r : Robot.getRobots()) {
            sb.append("robot ").append(r.getId()).append(" ");
            switch (r.getClass().getSimpleName()) {
                case "SecurityRobot":
                    sb.append("security");
                case "DeliveryRobot":
                    sb.append("delivery");
                    break;
                case "CleaningRobot":
                    sb.append("cleaning");
                    break;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
