package com.kodilla.testing.shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ShapeCollector {
    private LinkedList<Shape> shapeCollection = new LinkedList<>();
    public int addFigure(Shape shape) {
        /*
        * Add shape to shape collection
         */
        shapeCollection.add(shape);
        return shapeCollection.indexOf(shape);
    }
    public boolean removeFigure(Shape shape) {
        /*
        * remove shape from collection
         */
        if (shapeCollection.contains(shape)) {
            shapeCollection.remove(shape);
            return true;
        }
        return false;
    }
    public Shape getFigure(int index) {
        /*
        * Getting shape from collection
         */
        if (index >= 0 && index < shapeCollection.size()) {
            return shapeCollection.get(index);
        }
        return null;
    }
    public void showFigures() {
        /*
        * show tha names of all Shapes in collection
         */
        Iterator<Shape> shapeIterator = shapeCollection.iterator();
        while (shapeIterator.hasNext()) {
            System.out.println(shapeIterator.next());
        }
    }
}
