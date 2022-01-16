package com.company.shapes;

import com.company.HasWeight;
import com.company.Shape;
import java.util.Objects;


public class TriangularPrism extends Shape implements HasWeight {
    private double firstSideOfBase;
    private double secondSideOfBase;
    private double thirdSideOfBase;
    private double height;
    private double weight;

    public TriangularPrism(double firstSideOfBase, double secondSideOfBase, double thirdSideOfBase, double height, double weight) {
        if(firstSideOfBase <= 0 || secondSideOfBase <= 0 || thirdSideOfBase <= 0) throw new IllegalStateException("Sides of triangle must be greater then zero");
        isExisting(firstSideOfBase, secondSideOfBase, thirdSideOfBase);
        this.firstSideOfBase = firstSideOfBase;
        this.secondSideOfBase = secondSideOfBase;
        this.thirdSideOfBase = thirdSideOfBase;
        setHeight(height);
        setWeight(weight);
    }

    public void setFirstSideOfBase(double firstSideOfBas){
        if(firstSideOfBas > 0 && isExisting(firstSideOfBas, this.secondSideOfBase, this.thirdSideOfBase)) this.firstSideOfBase = firstSideOfBas;
        else throw new IllegalStateException("Side of triangle must be greater then zero");
    }

    public double getFirstSideOfBase() {
        return firstSideOfBase;
    }

    public void setSecondSideOfBase(double secondSideOfBase){
        if(secondSideOfBase > 0 && isExisting(this.firstSideOfBase, secondSideOfBase, this.thirdSideOfBase)) this.secondSideOfBase = secondSideOfBase;
        else throw new IllegalStateException("Side of triangle must be greater then zero");
    }

    public double getSecondSideOfBase() {
        return firstSideOfBase;
    }

    public void setThirdSideOfBase(double thirdSideOfBase){
        if(thirdSideOfBase > 0 && isExisting(this.firstSideOfBase, this.secondSideOfBase, thirdSideOfBase)) this.thirdSideOfBase = thirdSideOfBase;
        else throw new IllegalStateException("Side of triangle must be greater then zero");
    }

    public double getThirdSideOfBase() {
        return thirdSideOfBase;
    }

    public void setHeight(double height){
        if (height <= 0) throw new IllegalStateException("Height must be greater then zero");
        this.height = height;
    }

    public double getHeight(){ return height;}

    private double getBaseSquare(){
        double p = (firstSideOfBase + secondSideOfBase + thirdSideOfBase)/2;
        return Math.sqrt( p * (p - firstSideOfBase) * (p - secondSideOfBase) * (p - thirdSideOfBase));
    }

    @Override
    public double getSurfaceSquare(){ return 2 * getBaseSquare() + (firstSideOfBase * height) + (secondSideOfBase * height) + (thirdSideOfBase * height); }

    @Override
    public double getVolume(){ return getBaseSquare() * height; }

    @Override
    public double getWeight(){ return weight; }

    @Override
    public void setWeight(double weight){
        if (weight <= 0) throw new IllegalStateException("Weight must be greater then zero");
        this.weight = weight;
    }

    @Override
    public double getDensity () {
        return weight / getVolume();
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriangularPrism that = (TriangularPrism) o;
        return Double.compare(that.firstSideOfBase, firstSideOfBase) == 0 &&
                Double.compare(that.secondSideOfBase, secondSideOfBase) == 0 &&
                Double.compare(that.thirdSideOfBase, thirdSideOfBase) == 0 &&
                Double.compare(that.height, height) == 0 &&
                Double.compare(that.weight, weight) == 0;
    }

    @Override
    public int hashCode () {
        return Objects.hash(firstSideOfBase, secondSideOfBase, thirdSideOfBase, height, weight);
    }

    @Override
    public String toString () {
        return "TriangularPrism{" +
                "firstSideOfBase=" + firstSideOfBase +
                ", secondSideOfBase=" + secondSideOfBase +
                ", thirdSideOfBase=" + thirdSideOfBase +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    static private boolean isExisting(double firstSideOfBas, double secondSideOfBase, double thirdSideOfBase){
        if (firstSideOfBas > (secondSideOfBase + thirdSideOfBase) && secondSideOfBase > (firstSideOfBas + thirdSideOfBase) && thirdSideOfBase > (firstSideOfBas + secondSideOfBase)) return false;
        return true;
    }
}
