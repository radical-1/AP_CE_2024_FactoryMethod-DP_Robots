package org.example;
import org.example.model.Robots.SecurityRobot;
import org.junit.Test;

public class TestRobots {
    @Test
    public void testSecurityRobot() {
        SecurityRobot sr = new SecurityRobot(1, 10, 3);
        assertEquals(1, sr.getId());
        assertEquals(10, sr.getPower());
        assertEquals(3, sr.getLive());
        assertTrue(SecurityRobot.getSecurityRobotByID(1) == sr);
        assertFalse(SecurityRobot.getSecurityRobotByID(2) == sr);
        assertTrue(sr.doWork(5));
        assertFalse(sr.doWork(15));
        assertEquals(2, sr.getLive());
        assertFalse(sr.doWork(15));
        assertEquals(1, sr.getLive());
        assertFalse(sr.doWork(15));
        assertEquals(0, sr.getLive());
        assertNull(SecurityRobot.getSecurityRobotByID(1));
    }

    @Test
    public void testDeliveryRobot() {
        DeliveryRobot dr = new DeliveryRobot(1, "car");
        assertEquals(1, dr.getId());
        assertEquals("car", dr.getVehicle());
        assertTrue(DeliveryRobot.getDeliveryRobotByID(1) == dr);
        assertFalse(DeliveryRobot.getDeliveryRobotByID(2) == dr);
    }

    @Test
    public void testCleaningRobotFactory() {
        Robot cr = CleaningRobotFactory.createRobot(1, 3, new int[]{1, 2, 3});
        assertTrue(cr instanceof CleaningRobot);
        assertEquals(1, cr.getId());
        assertEquals(3, ((CleaningRobot) cr).getNumTasks());
        assertArrayEquals(new int[]{1, 2, 3}, ((CleaningRobot) cr).getAreas());
    }
}
