package com.company;

public abstract class SolidOfRevolution extends Shape {
    protected double radius;

    protected SolidOfRevolution(double radius){
        setRadius(radius);
    }

    public double getRadius(){ return radius; }

    public void setRadius(double radius){
        if(radius <= 0 ) throw new IllegalArgumentException("Radius must be greater then zero");
        this.radius = radius;
    }
}
