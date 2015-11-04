package geometries;

import mathLib.Normal3;
import mathLib.Point3;
import tools.Color;
import tools.Ray;

/**
 * Created by roberto on 25/10/15.
 */
public class Plane extends Geometry {
    public final Point3 a;
    public final Normal3 n;

    public Plane(Color color, Point3 a, Normal3 n) {
        super(color);

        if (a == null) {
            throw new IllegalArgumentException("The a cannot be null!");
        }
        if (n == null) {
            throw new IllegalArgumentException("The n cannot be null!");
        }

        this.a = a;
        this.n = n;
    }

    public Hit hit(Ray r) {
        if (r == null) {
            throw new IllegalArgumentException("The r cannot be null!");
        }
        // t = ((a - o) · n)/ (d · n)

        final double nenner = r.d.dot(n);

        if (nenner != 0) {
            double t = n.dot(a.sub(r.o)) / nenner;
            if (t > 0) return new Hit(t, r, this);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "a=" + a +
                ", n=" + n +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (a != null ? !a.equals(plane.a) : plane.a != null) return false;
        return !(n != null ? !n.equals(plane.n) : plane.n != null);

    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (n != null ? n.hashCode() : 0);
        return result;
    }
}
