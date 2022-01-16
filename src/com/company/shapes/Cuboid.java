package com.company.shapes;
import com.company.HasWeight;
import com.company.Shape;
import java.util.Objects;

public class Cuboid extends Shape implements HasWeight {
    private double length;
    private double width;
    private double height;
    private double weight;

    public Cuboid(double length, double width, double height, double weight) {
        setLength(length);
        setWidth(width);
        setHeight(height);
        setWeight(weight);
    }

    public void setWidth(double width){
        if (width <= 0) throw new IllegalStateException("Width must be greater then zero");
        this.width = width;
    }

    public double getWidth(){ return width;}

    public void setLength(double length){
        if (length <= 0) throw new IllegalStateException("Length must be greater then zero");
        this.length = length;
    }

    public double getLength(){ return length;}

    public void setHeight(double height){
        if (height <= 0) throw new IllegalStateException("Height must be greater then zero");
        this.height = height;
    }

    public double getHeight(){ return height;}

    @Override
    public double getSurfaceSquare(){ return 2*(length * width + length * height + width * height); }

    @Override
    public double getVolume(){ return length * width * height;}

    @Override
    public double getWeight(){ return weight; }

    @Override
    public void setWeight(double weight){
        if (weight <= 0) throw new IllegalStateException("Weight must be greater then zero");
        this.weight = weight;
    }

    @Override
    public double getDensity () { return weight / getVolume();}

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuboid cuboid = (Cuboid) o;
        return Double.compare(cuboid.length, length) == 0 &&
                Double.compare(cuboid.width, width) == 0 &&
                Double.compare(cuboid.height, height) == 0 &&
                Double.compare(cuboid.weight, weight) == 0;
    }

    @Override
    public int hashCode () {
        return Objects.hash(length, width, height, weight);
    }

    @Override
    public String toString () {
        return "Cuboid{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
