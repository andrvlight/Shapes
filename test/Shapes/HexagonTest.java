package Shapes;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import Shapes.Point.Point;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class HexagonTest {
    private Hexagon hexagon;
    private Point center;

    @BeforeEach
    void setUp() {
        center = new Point(0, 0);
        hexagon = new Hexagon(center, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroSideLength() {
        new Hexagon(center, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSideLength() {
        new Hexagon(center, -1);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, true",
            "-3, 4, true",
            "3, 4, true",
            "4, 0, true",
            "-4, 0, true",
            "3, -4, true",
            "-3, -4, true",
            "-10, 10, false"
    })
    public void testContains(double x, double y, boolean expected) {
        Point point = new Point(x, y);
        assertEquals(expected, hexagon.contains(point));                                                                                                               }
    }