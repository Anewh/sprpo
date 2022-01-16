package com.company.shapes;

import com.company.Shape;

public class DeleteShapeSubscriberImpl implements DeleteShapeSubscriber {// вот это лучше через лямбду
    @Override
    public void onSubscribe (Shape shape) {
        System.out.println("DeleteShapeSubscriber: " + shape.toString());
    }
}
