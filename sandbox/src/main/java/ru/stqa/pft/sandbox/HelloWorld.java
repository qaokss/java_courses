package ru.stqa.pft.sandbox;

public class HelloWorld {
    public static void main(String[] args) {
        hello("world");
        hello("user");

        Square s = new Square(4);
        System.out.println("Площадь квадрата со стороной " + s.len + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со второнами " + r.a + " и " + r.b + " = " + r.area());

        Point firstPoint = new Point(15, 10);
        Point secondPoint = new Point(20, 30);
        System.out.println("Расстояние между точками (" + firstPoint.x + "," + firstPoint.y + ") и (" + secondPoint.x + "," + secondPoint.y + ") = " + firstPoint.countDistance(secondPoint));

    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");

    }

}

