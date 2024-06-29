package org.example.model.Robots;

import java.util.ArrayList;

public class DeliveryRobot extends Robot {
    private static ArrayList<DeliveryRobot> deliveryRobots = new ArrayList<>();
    private String vehicle;

    public DeliveryRobot(int id, String vehicle) {
        super(id);
        this.vehicle = vehicle;
        deliveryRobots.add(this);
    }

    public static DeliveryRobot getDeliveryRobotByID(int id) {
        for (DeliveryRobot dr : deliveryRobots) {
            if (dr.getId() == id)
                return dr;
        }
        return null;
    }

    public String getVehicle() {
        return vehicle;
    }
}
