package Tools;

import MathLib.Point3;
import MathLib.Vector3;

/**
 * Created by roberto on 24/10/15.
 */

public class Ray {

    /**
     * The Point of the origin
     */
    public final Point3 o;
    /**
     * The Vector of the direction
     */
    public final Vector3 d;

    public Ray(Point3 o, Vector3 d) {
        this.o = o;
        this.d = d;
    }

    /**
     * Calculated the Point of the Ray at the given t
     *
     * @param t the value
     * @return Point3
     */
    public Point3 at(double t){
        // origin + direction * t
        return o.add(d.mul(t));
    }

    /**
     * Calculated the t of the Ray at the given point
     *
     * @param p the point
     * @return double
     * @throws IllegalArgumentException will be thrown if the given argument was null
     */
    public double tOf(Point3 p){
        if (p == null) {
            throw new IllegalArgumentException("The Point cannot be null!");
        }
        //|p − <o,d>| /  <d,d>
        return (p.sub(o).magnitude) / d.magnitude;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "d=" + d +
                ", o=" + o +
                '}';
    }

    @Override
    public boolean equals(Object o1) {
        if (this == o1) return true;
        if (o1 == null || getClass() != o1.getClass()) return false;

        Ray ray = (Ray) o1;

        if (o != null ? !o.equals(ray.o) : ray.o != null) return false;
        return !(d != null ? !d.equals(ray.d) : ray.d != null);

    }

    @Override
    public int hashCode() {
        int result = o != null ? o.hashCode() : 0;
        result = 31 * result + (d != null ? d.hashCode() : 0);
        return result;
    }
}
