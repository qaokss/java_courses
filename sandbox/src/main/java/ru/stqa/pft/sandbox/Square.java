package ru.stqa.pft.sandbox;

public class Square {


    public double len;

    public Square(double argLen) {
        this.len = argLen;
    }

    public  double area () {
        return this.len * this.len;
    }
}
