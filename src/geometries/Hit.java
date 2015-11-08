package geometries;

import tools.Ray;

/**
 * Created by
 * @author Robert Dziuba on 25/10/15.
 */
public class Hit {
    public final double t;
    public final Ray ray;
    public final Geometry geo;

    public Hit(final double t, final Ray ray, final Geometry geo) {
        if (ray == null) {
            throw new IllegalArgumentException("The Ray cannot be null!");
        }
        if (geo == null) {
            throw new IllegalArgumentException("The Geometry cannot be null!");
        }
        this.t = t;
        this.ray = ray;
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "Hit{" +
                "t=" + t +
                ", ray=" + ray +
                ", geo=" + geo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hit hit = (Hit) o;

        if (Double.compare(hit.t, t) != 0) return false;
        if (ray != null ? !ray.equals(hit.ray) : hit.ray != null) return false;
        return !(geo != null ? !geo.equals(hit.geo) : hit.geo != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(t);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ray != null ? ray.hashCode() : 0);
        result = 31 * result + (geo != null ? geo.hashCode() : 0);
        return result;
    }
}
