package com.kodilla.testing.shape;

//import com.kodilla.testing.forum.ForumUser;
//import com.kodilla.testing.shape.ShapeCollector;
//import com.kodilla.testing.shape.Square;
//import com.kodilla.testing.shape.Wheel;
//import com.kodilla.testing.shape.Triangle;
import org.junit.jupiter.api.*;

import java.util.LinkedList;

@Nested
@DisplayName("Shape Collection Test Suite")
public class ShapeCollectionTestSuite {
    private static int testCounter = 0;
    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("This is the beginning of tests");
    }
    @AfterAll
    public static void afterAllTests() {
        System.out.println("All tests are finished.");
    }
    @BeforeEach
    public void beforeEveryTest() {
        testCounter++;
        System.out.println("Preparing to execute test #" + testCounter);
    }
    @Test
    void testAddShape() {
        /*
         * Given
         */
        ShapeCollector shapeCollector = new ShapeCollector();
        Square square = new Square("square", 1d);
        Wheel wheel = new Wheel("wheel", 1d);
        Triangle triangle = new Triangle("triangle", 2d, 1d);
        /*
         * When
         */
        shapeCollector.addFigure(square);
        shapeCollector.addFigure(wheel);
        shapeCollector.addFigure(triangle);
        /*
         * Then
         */
        Assertions.assertEquals(square, shapeCollector.getFigure(0));
        Assertions.assertEquals(wheel, shapeCollector.getFigure(1));
        Assertions.assertEquals(triangle, shapeCollector.getFigure(2));
    }
    @Test
    void testGetShapeName() {
        /*
         * Given
         */
        ShapeCollector shapeCollector = new ShapeCollector();
        Square square = new Square("square", 1d);
        Wheel wheel = new Wheel("wheel", 1d);
        Triangle triangle = new Triangle("triangle", 2d, 1d);
        /*
         * When
         */
        shapeCollector.addFigure(square);
        shapeCollector.addFigure(wheel);
        shapeCollector.addFigure(triangle);
        /*
         * Then
         */
        Assertions.assertEquals(square.getShapeName(), shapeCollector.getFigure(0).getShapeName());
        Assertions.assertEquals(wheel.getShapeName(), shapeCollector.getFigure(1).getShapeName());
        Assertions.assertEquals(triangle.getShapeName(), shapeCollector.getFigure(2).getShapeName());
    }
    @Test
    void testGetShapeField() {
        /*
         * Given
         */
        ShapeCollector shapeCollector = new ShapeCollector();
        Square square = new Square("square", 1d);
        Wheel wheel = new Wheel("wheel", 1d);
        Triangle triangle = new Triangle("triangle", 2d, 1d);
        /*
         * When
         */
        shapeCollector.addFigure(square);
        shapeCollector.addFigure(wheel);
        shapeCollector.addFigure(triangle);
        /*
         * Then
         */
        Assertions.assertEquals(square.getField(), shapeCollector.getFigure(0).getField());
        Assertions.assertEquals(wheel.getField(), shapeCollector.getFigure(1).getField());
        Assertions.assertEquals(triangle.getField(), shapeCollector.getFigure(2).getField());
    }
    @Test
    void testRemoveShape() {
        /*
         * Given
         */
        ShapeCollector shapeCollector = new ShapeCollector();
        Square square = new Square("square", 1d);
        Wheel wheel = new Wheel("wheel", 1d);
        Triangle triangle = new Triangle("triangle", 2d, 1d);
        shapeCollector.addFigure(square);
        shapeCollector.addFigure(wheel);
        shapeCollector.addFigure(triangle);
        /*
         * When
         */
        LinkedList<Boolean> theResult = new LinkedList<>();
        theResult.add(shapeCollector.removeFigure(square));
        theResult.add(shapeCollector.removeFigure(wheel));
        theResult.add(shapeCollector.removeFigure(triangle));
        /*
         * Then
         */
        Assertions.assertTrue(theResult.get(0));
        Assertions.assertTrue(theResult.get(1));
        Assertions.assertTrue(theResult.get(2));
    }
}
