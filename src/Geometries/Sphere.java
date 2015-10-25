package Geometries;

import MathLib.Point3;
import Tools.Color;
import Tools.Ray;

/**
 * Created by roberto on 25/10/15.
 */
public class Sphere extends Geometry {
    public final Point3 c;
    public final double r;

    public Sphere(Color color, Point3 c, double r) {
        super(color);
        this.c = c;
        this.r = r;
    }

    public Hit hit(Ray r){
        return null;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "c=" + c +
                ", r=" + r +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sphere sphere = (Sphere) o;

        if (Double.compare(sphere.r, r) != 0) return false;
        return !(c != null ? !c.equals(sphere.c) : sphere.c != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = c != null ? c.hashCode() : 0;
        temp = Double.doubleToLongBits(r);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
