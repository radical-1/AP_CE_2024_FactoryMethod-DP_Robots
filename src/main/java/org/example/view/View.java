package org.example.view;

import org.example.controller.Controller;

import java.util.Collection;
import java.util.Scanner;

public class View {
    public static void run(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (Regex.END.isMatch(input)) {
                break;
            }
            if (Regex.CREATE_SECURITY_ROBOT.isMatch(input)) {
                Controller.createSecurityRobot(input);
            } else if (Regex.CREATE_CLEANING_ROBOT.isMatch(input)) {
                Controller.createCleaningRobot(input);
            } else if (Regex.CREATE_DELIVERY_ROBOT.isMatch(input)) {
                Controller.createDeliveryRobot(input);
            } else if (Regex.DELIVER.isMatch(input)) {
                System.out.println(Controller.deliver(input));
            } else if (Regex.CLEAN.isMatch(input)) {
                System.out.println(Controller.clean(input));
            } else if (Regex.PERFORM.isMatch(input)) {
                System.out.println(Controller.performTask(input));
            } else if (Regex.ATTACK.isMatch(input)) {
                System.out.println(Controller.attack(input));
            } else if (Regex.GET_ALL.isMatch(input)) {
                System.out.println(Controller.getAll());
            } else
                System.out.println("unknown command");
        }
    }
}
