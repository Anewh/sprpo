package com.company;
import com.company.shapes.*;

import java.util.*;

public class ShapeProducer extends Thread {
    private static final Random random = new Random();
    private final Space space;

    public ShapeProducer(Space space) {
        this.space = space;
    }

    @Override
    public void run() {
        while (true){
            Shape shape = createShape();
            try {
                sleep(random.nextInt(9000) + 1000);
                synchronized (space) {
                    while (!space.add(shape)) {
                        System.out.println("Producer wait");
                        space.wait(); }
                    System.out.println("Producer: + " + ((HasWeight)shape).getWeight());
                    System.out.println("Total weight: " + (space.getTotalWeight()));
                    System.out.println("Max total weight: " + (space.getMaxTotalWeight()));
                    space.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private double randDouble(){
        return random.nextDouble() * 1000 + 1;
    }
    private double randDouble(double max){
        return ((double )random.nextInt((int)max-1)) + random.nextDouble();
    }

    private Shape createShape(){
        Shapes figure = Shapes.fromInt(random.nextInt(4));
        Shape shape = null;
        switch(figure){
            case CONE: {
                shape = new Cone(randDouble(), randDouble(), randDouble(space.getMaxTotalWeight()));
                break;
            }
            case SPHERE: {
                shape = new Sphere(randDouble(),  randDouble(space.getMaxTotalWeight()));
                break;
            }
            case CUBOID: {
                shape = new Cuboid(randDouble(), randDouble(),randDouble(), randDouble(space.getMaxTotalWeight()));
                break;
            }
            case TRIANGULAR_PRISM: {
                shape = new TriangularPrism(randDouble(), randDouble(), randDouble(), randDouble(), randDouble(space.getMaxTotalWeight()));
                break;
            }
        }
        return shape;
    }
}
