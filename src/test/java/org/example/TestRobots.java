package org.example;

import org.example.model.Factories.CleaningRobotFactory;
import org.example.model.Factories.DeliveryRobotFactory;
import org.example.model.Robots.CleaningRobot;
import org.example.model.Robots.DeliveryRobot;
import org.example.model.Robots.Robot;
import org.example.model.Robots.SecurityRobot;
import org.example.view.Regex;
import org.junit.Assert;
import org.junit.Test;

public class TestRobots {
    @Test
    public void testSecurityRobot() {
        SecurityRobot sr = new SecurityRobot(1, 10, 3);
        Assert.assertEquals(1, sr.getId());
        Assert.assertEquals(10, sr.getPower());
        Assert.assertEquals(3, sr.getLive());
        Assert.assertSame(SecurityRobot.getSecurityRobotByID(1), sr);
        Assert.assertNotSame(SecurityRobot.getSecurityRobotByID(2), sr);
        Assert.assertTrue(sr.doWork(5));
        Assert.assertFalse(sr.doWork(15));
        Assert.assertEquals(2, sr.getLive());
        Assert.assertFalse(sr.doWork(15));
        Assert.assertEquals(1, sr.getLive());
        Assert.assertFalse(sr.doWork(15));
        Assert.assertEquals(0, sr.getLive());
        Assert.assertNull(SecurityRobot.getSecurityRobotByID(1));
    }

    @Test
    public void testDeliveryRobot() {
        DeliveryRobotFactory drf = new DeliveryRobotFactory();
        DeliveryRobot dr = (DeliveryRobot) drf.createRobot(1, "car");
        Assert.assertEquals(1, dr.getId());
        Assert.assertEquals("car", dr.getVehicle());
        Assert.assertTrue(DeliveryRobot.getDeliveryRobotByID(1) == dr);
        Assert.assertFalse(DeliveryRobot.getDeliveryRobotByID(2) == dr);
    }

    @Test
    public void testCleaningRobotFactory() {
        CleaningRobotFactory crf = new CleaningRobotFactory();
        Robot cr = crf.createRobot(1, 3, new int[]{1, 2, 3});
        Assert.assertTrue(cr instanceof CleaningRobot);
        Assert.assertEquals(1, cr.getId());
        Assert.assertEquals(3, ((CleaningRobot) cr).getNumTasks());
        Assert.assertArrayEquals(new int[]{1, 2, 3}, ((CleaningRobot) cr).getAreas());
    }

    @Test
    public void testCleaningRobot() {
        CleaningRobot cr = new CleaningRobot(1, 3, new int[]{1, 2, 3});
        Assert.assertEquals(1, cr.getId());
        Assert.assertEquals(3, cr.getNumTasks());
        Assert.assertArrayEquals(new int[]{1, 2, 3}, cr.getAreas());
        Assert.assertFalse(cr.isRetired());
        Assert.assertTrue(cr.isValidArea(1));
        Assert.assertFalse(cr.isValidArea(4));
        cr.doWork();
        Assert.assertEquals(2, cr.getNumTasks());
        cr.doWork();
        cr.doWork();
        Assert.assertTrue(cr.isRetired());
    }

    @Test
    public void testRegex() {
        String input = "create security robot 1 10 3";
        Assert.assertTrue(Regex.CREATE_SECURITY_ROBOT.isMatch(input));
        Assert.assertFalse(Regex.CREATE_CLEANING_ROBOT.isMatch(input));
        Assert.assertEquals("1", Regex.CREATE_SECURITY_ROBOT.getGroup(input, "id"));
        Assert.assertEquals("10", Regex.CREATE_SECURITY_ROBOT.getGroup(input, "power"));
        Assert.assertEquals("3", Regex.CREATE_SECURITY_ROBOT.getGroup(input, "live"));
    }

    @Test
    public void testCleaningRobotFactoryCreatesRobot() {
        int initialSize = CleaningRobot.getCleaningRobots().size();
        CleaningRobotFactory crf = new CleaningRobotFactory();
        Robot cr = crf.createRobot(2, 3, new int[]{1, 2, 3});
        Assert.assertTrue(cr instanceof CleaningRobot);
        Assert.assertEquals(2, cr.getId());
        Assert.assertEquals(3, ((CleaningRobot) cr).getNumTasks());
        Assert.assertArrayEquals(new int[]{1, 2, 3}, ((CleaningRobot) cr).getAreas());
        Assert.assertEquals(initialSize + 1, CleaningRobot.getCleaningRobots().size());
    }

    @Test
    public void testCleaningRobotInvalidArea() {
        CleaningRobot cr = new CleaningRobot(1, 3, new int[]{1, 2, 3});
        Assert.assertFalse(cr.isValidArea(4));
    }

    @Test
    public void testCleaningRobotDoWorkWhenRetired() {
        CleaningRobot cr = new CleaningRobot(1, 1, new int[]{1, 2, 3});
        cr.doWork();
        Assert.assertEquals(0, cr.getNumTasks());
    }

    @Test
    public void testSecurityRobotDoWorkWhenDead() {
        SecurityRobot sr = new SecurityRobot(1, 10, 1);
        Assert.assertTrue(sr.doWork(5));
    }

    @Test
    public void testRegexNoMatch() {
        String input = "invalid command";
        Assert.assertFalse(Regex.CREATE_SECURITY_ROBOT.isMatch(input));
        Assert.assertFalse(Regex.CREATE_CLEANING_ROBOT.isMatch(input));
        Assert.assertFalse(Regex.CREATE_DELIVERY_ROBOT.isMatch(input));
        Assert.assertFalse(Regex.DELIVER.isMatch(input));
        Assert.assertFalse(Regex.CLEAN.isMatch(input));
        Assert.assertFalse(Regex.PERFORM.isMatch(input));
        Assert.assertFalse(Regex.ATTACK.isMatch(input));
        Assert.assertFalse(Regex.GET_ALL.isMatch(input));
        Assert.assertFalse(Regex.END.isMatch(input));
    }
}
