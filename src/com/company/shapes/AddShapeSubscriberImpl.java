package com.company.shapes;

import com.company.Shape;

public class AddShapeSubscriberImpl implements AddShapeSubscriber { // вот это лучше через лямбду
    @Override
    public void onSubscribe (Shape shape) {
        System.out.println("AddShapeSubscriber: " + shape.toString());
    }
}


