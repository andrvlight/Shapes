package Shapes;

import Shapes.Point.Point;

public class Circle extends Shape {
    private double radius;

    public Circle(Point center, double radius) throws IllegalArgumentException {
        super(center);
        if (radius <= 0) throw new IllegalArgumentException("radius must be > 0");
        this.radius = radius;
    }

    @Override
    public boolean contains(Point point) {
        double distanceX = point.getX() - center.getX();
        double distanceY = point.getY() - center.getY();
        return distanceX * distanceX + distanceY * distanceY <= radius * radius;
    }

    @Override
    public double getSize() {return radius;}

    public void setRadius(double radius) {this.radius = radius;}
}
