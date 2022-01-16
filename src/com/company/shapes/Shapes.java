package com.company.shapes;

public enum Shapes {
    CONE,
    SPHERE,
    CUBOID,
    TRIANGULAR_PRISM;
    public static Shapes fromInt(int i) { return values()[i]; }
}
