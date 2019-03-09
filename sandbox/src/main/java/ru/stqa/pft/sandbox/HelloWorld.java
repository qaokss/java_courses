package ru.stqa.pft.sandbox;

public class HelloWorld {
    public static void main(String[] args) {
        hello("world");
        hello("user");

        Square s = new Square(4);
        Square s2 = new Square(34234);
        System.out.println("Площадь квадрата со стороной " + s.len + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со второнами " + r.a + " и " + r.b + " = " + r.area());

        Point n = new Point(15, 10);
        Point n2 = new Point(20, 30);
        System.out.println("Расстояние между точками (" + n.x + "," + n.y + ") и (" + n2.x + "," + n2.y + ") = " + Point.countDistance(n, n2));

    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");

    }

}

