package com.company;
import com.company.shapes.*;

import java.io.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Space {
    private LinkedList<Shape> shapes;
    private double maxTotalWeight;

    private static Space spaceInstance = null;

    public static Space getInstance(LinkedList<Shape> shapes, double maxTotalWeight) {
        if (spaceInstance == null)
            spaceInstance = new Space(shapes, maxTotalWeight);
        return spaceInstance;
    }

    private final LinkedList<AddShapeSubscriber> addShapeSubscribers = new LinkedList<>();
    private final LinkedList<DeleteShapeSubscriber> deleteShapeSubscribers = new LinkedList<>();

    public void setAddShapeSubscribers(AddShapeSubscriber subscriber) {
        addShapeSubscribers.add(subscriber);
    }

    public void setDeleteShapeSubscribers(DeleteShapeSubscriber subscriber) {
        deleteShapeSubscribers.add(subscriber);
    }

    private Space(LinkedList<Shape> shapes, double maxTotalWeight){
        this.shapes = shapes;
        this.maxTotalWeight = maxTotalWeight;}

    public Collection<Shape> getShapes() { return shapes;}

    public boolean add(Shape figure){
        if((this.getTotalWeight() + ((HasWeight)figure).getWeight() > getMaxTotalWeight())) return false;
        shapes.add(figure);
        for (AddShapeSubscriber subscriber : addShapeSubscribers) {
            subscriber.onSubscribe(((Shape)figure));
        }
        return true;
    }

    public boolean delete(Shape figure){
        boolean isDeleted = shapes.remove(figure);
        if(isDeleted) {
            for (DeleteShapeSubscriber subscriber : deleteShapeSubscribers) {
                subscriber.onSubscribe(figure);
            }
        }
        return isDeleted;
    }

    public double getTotalVolume(){
        return shapes.stream().mapToDouble(Shape::getVolume).sum();}

    public double getTotalSurfaceSquare() {
        return shapes.stream().mapToDouble(Shape::getSurfaceSquare).sum();}

    public void scaleShapes(double factor){
        for (Shape shape: shapes){
            if(shape instanceof Cone){ scaleCone(shape, factor); }
            else if(shape instanceof Cuboid){ scaleCuboid(shape, factor);}
            else if(shape instanceof Sphere){ scaleSphere(shape, factor);}
            else if(shape instanceof TriangularPrism){ scaleTriangularPrism(shape, factor);}
        }
    }

    public Collection<Shape> filterBy(Predicate<Shape> condition){
        return shapes.stream().filter(condition).collect(Collectors.toList());
    }

    public Collection<Shape> sortedBy(Comparator<Shape> comparator){
        return shapes.stream().sorted(comparator).collect(Collectors.toList());
    }


    public void saveShapesToFile(String path) throws IOException {
        try(DataOutputStream fileOut = new DataOutputStream(new FileOutputStream(path))) {
            for (Shape shape : shapes) {
                if (shape instanceof Cone)
                    writeCone((Cone) shape, fileOut);
                else if (shape instanceof Sphere)
                    writeSphere((Sphere) shape, fileOut);
                else if (shape instanceof Cuboid)
                    writeCubouid((Cuboid) shape, fileOut);
                else if (shape instanceof TriangularPrism)
                    writeTriangularPrism((TriangularPrism) shape, fileOut);
            }
        } catch (IOException e) {
            throw new IOException("Writing shapes in file is failed");
        }
    }

    public void loadShapesFromFile(String path) throws IOException {
        try (DataInputStream fileIn = new DataInputStream(new FileInputStream(path))) {
            while (fileIn.available() > 0) {
                Shapes shapeType = Shapes.fromInt(fileIn.readInt());
                switch (shapeType) {
                    case CONE: shapes.add(readCone(fileIn)); break;
                    case SPHERE: shapes.add(readSphere(fileIn)); break;
                    case CUBOID: shapes.add(readCuboid(fileIn)); break;
                    case TRIANGULAR_PRISM: shapes.add(readTriangularPrism(fileIn)); break;
                }
            }
        } catch (IOException e) {
            throw new IOException("Writing shapes in file is failed");
        }
    }

    public double getTotalWeight() {
        return shapes.stream().mapToDouble(shape -> ((HasWeight) shape).getWeight()).sum();
    }

    public double getMaxTotalWeight(){ return this.maxTotalWeight; }

    private void scaleCone(Shape shape, double factor) {
        Cone element = (Cone) shape;
        element.setHeight(factor * element.getHeight());
        element.setRadius(factor * element.getRadius());
    }

    private void scaleCuboid(Shape shape, double factor) {
        Cuboid element = (Cuboid) shape;
        element.setWidth(element.getWidth() * factor);
        element.setHeight(element.getHeight() * factor);
        element.setLength(element.getLength() * factor);
    }

    private void scaleSphere(Shape shape, double factor) {
        Sphere element = (Sphere) shape;
        element.setRadius(factor * element.getRadius());
    }

    private void scaleTriangularPrism(Shape shape, double factor) {
        TriangularPrism element = (TriangularPrism) shape;
        element.setFirstSideOfBase(factor * element.getFirstSideOfBase());
        element.setSecondSideOfBase(factor * element.getSecondSideOfBase());
        element.setThirdSideOfBase(factor * element.getThirdSideOfBase());
        element.setHeight(factor * element.getHeight());
    }

    private void writeCone(Cone cone, DataOutputStream output) throws IOException {
        output.writeInt(Shapes.CONE.ordinal());
        output.writeDouble(cone.getRadius());
        output.writeDouble(cone.getHeight());
        output.writeDouble(cone.getWeight());
    }

    private void writeSphere(Sphere sphere, DataOutputStream output) throws IOException {
        output.writeInt(Shapes.SPHERE.ordinal());
        output.writeDouble(sphere.getRadius());
        output.writeDouble(sphere.getWeight());
    }

    private void writeCubouid(Cuboid cuboid, DataOutputStream output) throws IOException {
        output.writeInt(Shapes.CUBOID.ordinal());
        output.writeDouble(cuboid.getLength());
        output.writeDouble(cuboid.getWidth());
        output.writeDouble(cuboid.getHeight());
        output.writeDouble(cuboid.getWeight());
    }

    private void writeTriangularPrism(TriangularPrism prism, DataOutputStream output) throws IOException {
        output.writeInt(Shapes.TRIANGULAR_PRISM.ordinal());
        output.writeDouble(prism.getFirstSideOfBase());
        output.writeDouble(prism.getSecondSideOfBase());
        output.writeDouble(prism.getThirdSideOfBase());
        output.writeDouble(prism.getHeight());
        output.writeDouble(prism.getWeight());
    }

    private static Cone readCone(DataInputStream input) throws IOException {
        double radius = input.readDouble();
        double height = input.readDouble();
        double weight = input.readDouble();
        return new Cone(radius, height, weight);
    }

    private static Sphere readSphere(DataInputStream input) throws IOException {
        double radius = input.readDouble();
        double weight = input.readDouble();
        return new Sphere(radius, weight);
    }

    private static Cuboid readCuboid(DataInputStream input) throws IOException {
        double length = input.readDouble();
        double width = input.readDouble();
        double height = input.readDouble();
        double weight = input.readDouble();
        return new Cuboid(length, width, height, weight);
    }

    private static TriangularPrism readTriangularPrism(DataInputStream input) throws IOException {
        double firstSideOfBase = input.readDouble();
        double secondSideOfBase = input.readDouble();
        double thirdSideOfBase = input.readDouble();
        double height = input.readDouble();
        double weight = input.readDouble();
        return new TriangularPrism(firstSideOfBase, secondSideOfBase,
                thirdSideOfBase, height, weight);
    }
}
