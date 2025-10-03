package Shapes;

import Shapes.Point.Point;

public class Square extends Shape {
    private double sideLength;

    public Square(Point center, double sideLength) throws IllegalArgumentException {
        if (sideLength <= 0) throw new IllegalArgumentException("sideLength must be > 0");
        super(center);
        this.sideLength = sideLength;
    }

    @Override
    public boolean contains(Point point) {
        double halfSideLength = sideLength / 2;

        return point.getX() >= center.getX() - halfSideLength &&
               point.getX() <= center.getX() + halfSideLength &&
               point.getY() >= center.getY() - halfSideLength &&
               point.getY() <= center.getY() + halfSideLength;
    }

    @Override
    public double getSize() {return sideLength;}

    public void setSideLength(double sideLength) {this.sideLength = sideLength;}
}
