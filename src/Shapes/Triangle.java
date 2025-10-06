package Shapes;

import Shapes.Point.Point;

public class Triangle extends Shape {
    private double sideLength;

    public Triangle(Point center, double sideLength) throws IllegalArgumentException {
        super(center);
        if (sideLength <= 0) throw new IllegalArgumentException("sideLength must be > 0");
        this.sideLength = sideLength;
    }

    @Override
    public boolean contains(Point point) {
        double height = Math.sqrt(3) * sideLength;

        Point pointA = new Point(center.getX() - sideLength / 2, center.getY() - height / 3);
        Point pointB = new Point(center.getX() + sideLength / 2, center.getY() - height / 3);
        Point pointC = new Point(center.getX(), center.getY() + 2 * height / 3);

        double s1, s2, s3;
        s1 = product(subs(pointB, pointA), subs(point, pointA));
        s2 = product(subs(pointC, pointB), subs(point, pointB));
        s3 = product(subs(pointA, pointC), subs(point, pointC));

        return s1 >= 0 && s2 >= 0 && s3 >= 0;
    }

    private Point subs(Point pointA, Point pointB) {
        return new Point(pointA.getX() - pointB.getX(),  pointA.getY() - pointB.getY());
    }

    private double product(Point pointA, Point pointB) {
        return pointA.getX() * pointB.getY() - pointA.getY() * pointB.getX();
    }

    @Override
    public double getSize() {return sideLength;}

    public void setSideLength(double sideLength) {this.sideLength = sideLength;}
}