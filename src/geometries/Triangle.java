package geometries;

import mathLib.Mat3x3;
import mathLib.Point3;
import mathLib.Vector3;
import tools.Color;
import tools.Ray;

/**
 * This class represents a Triangle Object.
 * @ Robert Dziuba on 03.11.15.
 */
public class Triangle extends Geometry{
    /**
     * The a corner point of the Triangle.
     */
    private final Point3 a;
    /**
     * The b corner point of the Triangle.
     */
    private final Point3 b;
    /**
     * The c corner point of the Triangle.
     */
    private final Point3 c;

    /**
     * Instantiates a new Triangle Object.
     * @param a corner point of the Sphere. Can't be null.
     * @param b corner point of the Sphere. Can't be null.
     * @param c corner point of the Sphere. Can't be null.
     * @param color of the Sphere. Can't be null.
     * @throws IllegalArgumentException if one of the given arguments are null.
     */
    public Triangle(final Point3 a,final Point3 b,final Point3 c,final Color color) {
        super(color);
        if (a == null) {
            throw new IllegalArgumentException("The a cannot be null!");
        }
        if (b == null) {
            throw new IllegalArgumentException("The b cannot be null!");
        }
        if (c == null) {
            throw new IllegalArgumentException("The c cannot be null!");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public Hit hit(final Ray r) {
        if (r == null) {
            throw new IllegalArgumentException("The r cannot be null!");
        }

        // M x = V

        final Vector3 v = new Vector3(a.x - r.o.x, a.y - r.o.y, a.z - r.o.z);

        final Mat3x3 m = new Mat3x3(
                a.x - b.x, a.x - c.x, r.d.x,
                a.y - b.y, a.y - c.y, r.d.y,
                a.z - b.z, a.z - c.z, r.d.z
        );

        final double detA = m.determinante;
        final double detA1 = m.changeCol1(v).determinante;
        final double detA2 = m.changeCol2(v).determinante;
        final double detA3 = m.changeCol3(v).determinante;

        final double beta = detA1 / detA;
        final double gamma = detA2 / detA;
        final double t = detA3 / detA;

        if((beta > 0 && gamma > 0 ) && beta + gamma <= 1){
            return new Hit(t, r, this);
        }

        return null;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Triangle triangle = (Triangle) o;

        if (a != null ? !a.equals(triangle.a) : triangle.a != null) return false;
        if (b != null ? !b.equals(triangle.b) : triangle.b != null) return false;
        return !(c != null ? !c.equals(triangle.c) : triangle.c != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (a != null ? a.hashCode() : 0);
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }
}
