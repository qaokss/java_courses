package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testCountDistance() {
        Point i1 = new Point(999, 200);
        Point i2 = new Point(999, 999);
        Assert.assertEquals(Point.countDistance(i1, i2), 799.0);

        Point j1 = new Point(-20, -50);
        Point j2 = new Point(15, 13);
        Assert.assertEquals(Point.countDistance(j1, j2), 72.069410986909);

        Point q1 = new Point(-1, -1);
        Point q2 = new Point(-1, -1);
        Assert.assertEquals(Point.countDistance(q1, q2), 0.0);

    }
}
