package com.company.shapes;
import com.company.SolidOfRevolution;
import com.company.HasWeight;
import java.util.Objects;
public class Sphere extends SolidOfRevolution implements HasWeight {
    private double weight;

    public Sphere(double radius, double weight){
        super(radius);
        setWeight(weight);
    }

    @Override
    public double getSurfaceSquare(){ return 4 * Math.PI * radius * radius; }

    @Override
    public double getVolume(){ return 4/3 * (Math.PI * Math.pow( radius, 3));}

    @Override
    public double getWeight(){ return weight; }

    @Override
    public void setWeight(double weight){
        if (weight <= 0) throw new IllegalStateException("Weight must be greater then zero");
        this.weight = weight;
    }

    @Override
    public double getDensity () { return weight / getVolume(); }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(sphere.weight, weight) == 0;
    }

    @Override
    public int hashCode () { return Objects.hash(weight); }

    @Override
    public String toString () {
        return "Sphere{" +
                "weight=" + weight +
                ", radius=" + radius +
                '}';
    }
}
