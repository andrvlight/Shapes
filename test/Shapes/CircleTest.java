package Shapes;

import Shapes.Point.Point;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CircleTest {
    private Point center;
    private Circle circle;

    @BeforeEach
    public void setUp() {
        center = new Point(0, 0);
        circle = new Circle(center, 5.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroRadius(){
        new Circle(center, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeRadius(){
        new Circle(center, -5.0);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, true",
            "0, 5, true",
            "5, 0, true",
            "0, -5, true",
            "-5, 0, true",
            "5, 5, false",
            "-5, -5, false",
            "-5, 5, false",
            "5, -5, false",
    })
    public void testContains(double x, double y, boolean expected) {
        Point point = new Point(x, y);
        assertEquals(expected, circle.contains(point));
    }
}