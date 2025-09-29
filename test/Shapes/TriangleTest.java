package Shapes;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import Shapes.Point.Point;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TriangleTest {
    private Triangle triangle;
    private Point center;

    @BeforeEach
    void setUp() {
        center = new Point(0, 0);
        triangle = new Triangle(center, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroSideLength() {
        new Triangle(center, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSideLength() {
        new Triangle(center, -1);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, true",
            "0, 2, true",
            "-1, -1, true",
            "0, -2, true",
            "-10, 10, false"
    })
    public void testContains(double x, double y, boolean expected) {
        Point point = new Point(x, y);
        assertEquals(expected, triangle.contains(point));
    }
}