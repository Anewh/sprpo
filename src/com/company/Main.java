package com.company;
import com.company.shapes.AddShapeSubscriberImpl;
import com.company.shapes.DeleteShapeSubscriberImpl;

import java.util.*;

public class Main {
    public static void main (String[] args) {
        Space space = Space.getInstance(new LinkedList<>(), 1000);
        Collection<Shape> shapes = new LinkedList<>();
        ShapeProducer shapeProducer = new ShapeProducer(space);
        ShapeConsumer shapeConsumer = new ShapeConsumer(space);

        space.setDeleteShapeSubscribers(new DeleteShapeSubscriberImpl());
        space.setAddShapeSubscribers(new AddShapeSubscriberImpl());

        shapeProducer.start();
        shapeConsumer.start();
    }
}
