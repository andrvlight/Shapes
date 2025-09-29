package Shapes;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import Shapes.Point.Point;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SquareTest {
    private Point center;
    private Square square;

    @BeforeEach
    void setUp() {
        center = new Point(0, 0);
        square = new Square(center, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroSideLength() {
        new Square(center, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSideLength() {
        new Square(center, -5);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, true",
            "0, 2.5, true",
            "2.5, 0, true",
            "0, -2.5, true",
            "-2.5, 0, true",
            "2.5, 2.5, true",
            "-2.5, -2.5, true",
            "-2.5, 2.5, true",
            "5, -2.5, false",
    })
    public void testContains(double x, double y, boolean expected) {
        Point point = new Point(x, y);
        assertEquals(expected, square.contains(point));
    }
}