package Shapes;

import Shapes.Point.Point;

public abstract class Shape {
    protected Point center;

    public Shape(Point center) {
        this.center = center;
    }

    public abstract boolean contains(Point point);

    public Point getCenter() {return center;}

    public void setCenter(Point center) {this.center = center;}

    public abstract double getSize();
}