public class HelloWorld {
    public static void main(String[] args) {
        hello("world");
        hello("user");

        double l = 5;
        System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

        double a = 4;
        double b = 6;
        System.out.println("Площадь прямоугольника со второнами " + a + " и " + b + " = " + area(a, b));
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");


//      рассчитывается квадрат значения и выводится на консоль
//        double side = 6.5;
//        double quadr = side * side;
//        System.out.println("Площадь квадрата со стороной " + side + " = " + quadr);
    }

    public static double area (double side) {
        return side * side;
    }

    public static double area (double a, double b) {
        return a * b;
    }
}

