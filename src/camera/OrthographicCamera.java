package camera;

import mathLib.Point3;
import mathLib.Vector3;
import tools.Ray;

/**
 * Created by roberto on 25/10/15.
 *
 * @author Robert Dziuba
 */
public class OrthographicCamera extends Camera {

    public final double s;

    public OrthographicCamera(Point3 e, Vector3 g, Vector3 t, double s) {
        super(e, g, t);
        this.s = s;
    }

    @Override
    public Ray rayFor(int w, int h, int x, int y) {

        // d= -w
        // o = e + a * s * (x - w-1/s) / w-1 * u + s * (y - h-1/2) / h-1 * v

        final int a = w / h;
        final Vector3 ux = u.mul((x - ((w - 1) / 2)) / (w - 1)).mul(s).mul(a);
        final Vector3 vy = v.mul((y - ((h - 1) / 2)) / (w - 1)).mul(s);
        final Point3 o = e.add(ux.add(vy));
        final Vector3 d = super.w.mul(-1);

        return new Ray(o, d);
    }

    @Override
    public String toString() {
        return "OrthographicCamera{" +
                "s=" + s +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrthographicCamera that = (OrthographicCamera) o;

        return Double.compare(that.s, s) == 0;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(s);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
