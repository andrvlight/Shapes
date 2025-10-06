package Shapes;

import Shapes.Point.Point;

public class Hexagon extends Shape {
    private double sideLength;

    public Hexagon(Point center, double sideLength) throws IllegalArgumentException {
        super(center);
        if (sideLength <= 0) throw new IllegalArgumentException("sideLength must be > 0");
        this.sideLength = sideLength;
    }

    @Override
    public boolean contains(Point point) {
        double height = Math.sqrt(3) * sideLength;

        Point pointA1, pointA2, pointA3, pointA4,  pointA5, pointA6;
        pointA1 = new Point(center.getX() - sideLength / 2, center.getY() + height / 2);
        pointA2 = new Point(center.getX() + sideLength / 2, center.getY() + height / 2);
        pointA3 = new Point(center.getX() + height / 2, center.getY());
        pointA4 = new Point(center.getX() + sideLength / 2, center.getY() - height / 2);
        pointA5 = new Point(center.getX() - sideLength / 2, center.getY() - height / 2);
        pointA6 = new Point(center.getX() - height / 2, center.getY());

        double s1, s2, s3, s4, s5, s6;
        s1 = product(subs(pointA6, pointA1), subs(point, pointA1));
        s2 = product(subs(pointA5, pointA6), subs(point, pointA6));
        s3 = product(subs(pointA4, pointA5), subs(point, pointA5));
        s4 = product(subs(pointA3, pointA4), subs(point, pointA4));
        s5 = product(subs(pointA2, pointA3), subs(point, pointA3));
        s6 = product(subs(pointA1, pointA2), subs(point, pointA2));

        return s1 >= 0 && s2 >= 0 && s3 >= 0 && s4 >= 0 && s5 >= 0 && s6 >= 0;
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