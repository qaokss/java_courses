package ru.stqa.pft.sandbox;

public class Point {
    public int x;
    public int y;

    public Point(int argX, int argY) {
        this.x = argX;
        this.y = argY;
    }

    public double countDistance(Point point) {
        return Math.sqrt(Math.pow((point.x - this.x), 2) + Math.pow((point.y - this.y), 2));
    }

}

