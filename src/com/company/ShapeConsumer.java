package com.company;

import java.util.LinkedList;
import java.util.Random;

public class ShapeConsumer extends Thread {
    private static final Random random = new Random();
    private final Space space;

    public ShapeConsumer(Space space){ this.space = space; }

    @Override
    public void run() {
        while (true) {
            LinkedList<Shape> shapes = (LinkedList<Shape>)space.getShapes(); //
                Shape randShape = shapes.get(random.nextInt(shapes.size())); //
                try{
                    sleep(random.nextInt(9000) + 1000);
                    synchronized (space){
                        while(shapes.isEmpty() || !space.delete(randShape)){
                            System.out.println("Consumer wait");
                            space.wait();
                        }
                        System.out.println("Consumer delete figure. Total weight: " + space.getTotalWeight() );
                        space.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            System.out.print("");
        }
    }
}
