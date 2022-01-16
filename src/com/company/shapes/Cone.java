package com.company.shapes;
import com.company.HasWeight;
import com.company.SolidOfRevolution;
import java.util.Objects;
public class Cone extends SolidOfRevolution implements HasWeight {
    private double height;
    private double weight;

    public Cone(double radius, double height, double weight) {
        super(radius);
        setHeight(height);
        setWeight(weight);
    }

    public void setHeight(double height){
        if (height <= 0) throw new IllegalStateException("Height must be greater then zero");
        this.height = height;}

    public double getHeight(){ return height;}

    @Override
    public double getSurfaceSquare(){ return Math.PI * radius * radius * (2 * Math.PI + 1); }

    @Override
    public double getVolume(){ return (Math.PI * Math.pow( radius, 2) * height)/3;}

    @Override
    public double getWeight(){ return weight; }

    @Override
    public void setWeight(double weight){
        if (weight <= 0) throw new IllegalStateException("Weight must be greater then zero");
        this.weight = weight;}

    @Override
    public double getDensity () {
        return weight / getVolume();
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cone cone = (Cone) o;
        return Double.compare(cone.height, height) == 0 &&
                Double.compare(cone.weight, weight) == 0;
    }

    @Override
    public int hashCode () {
        return Objects.hash(height, weight);
    }

    @Override
    public String toString () {
        return "Cone{" +
                "height=" + height +
                ", weight=" + weight +
                ", radius=" + radius +
                '}';
    }
}

