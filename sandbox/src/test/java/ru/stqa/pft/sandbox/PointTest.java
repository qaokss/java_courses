package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testCountDistance() {
        Point testFirstPoint1 = new Point(999, 200);
        Point testSecondPoint1 = new Point(999, 999);
        Assert.assertEquals(testFirstPoint1.countDistance(testSecondPoint1), 799.0);

        Point testFirstPoint2 = new Point(-20, -50);
        Point testSecondPoint2 = new Point(15, 13);
        Assert.assertEquals(testFirstPoint2.countDistance(testSecondPoint2), 72.069410986909);

        Point testFirstPoint3 = new Point(-1, -1);
        Point testSecondPoint3 = new Point(-1, -1);
        Assert.assertEquals(testFirstPoint3.countDistance(testSecondPoint3), 0.0);

    }
}
