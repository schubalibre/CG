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
        if (c == null) {
            throw new IllegalArgumentException("The c cannot be null!");
        }
        this.c = c;
        this.r = r;
    }

    public Hit hit(Ray r){

        // t = (-b +- wurzel b2 - 4 * ac) / 2a

        final double a = r.d.dot(r.d);
        final double b = r.d.dot(r.o.sub(c).mul(2));
        final double cn = r.o.sub(c).dot(r.o.sub(c))-(this.r * this.r);

        // d = b2 - 4ac
        final double d = (b*b) - (4 * a * cn);

        if (d > 0) {

            final double t1 = (-b + Math.sqrt(d)) / (2 * a);
            final double t2 = (-b - Math.sqrt(d)) / (2 * a);

            /*double t = Constants.EPSILON;

            if(t2 < Constants.EPSILON && t1 < Constants.EPSILON){
                t = Math.max(t1, t2);
            }
            if(t2 > Constants.EPSILON && t1 > Constants.EPSILON){
                t = Math.min(t1,t2);
            }
            if(t2 > Constants.EPSILON && t1 < Constants.EPSILON){
                t = t2;
            }

            if(t2 < Constants.EPSILON && t1 > Constants.EPSILON){
                t = t1;
            }

            if(t > Constants.EPSILON){
                final Normal3 normal = ray.at(t).sub(this.c).normalized().asNormal();
                return new Hit(t, ray, this,normal,texFor(ray.at(t)));
            }*/
        }

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
