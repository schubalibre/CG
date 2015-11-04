package camera;

import mathLib.Point3;
import mathLib.Vector3;
import tools.Ray;

/**
 * Created by roberto on 25/10/15.
 *
 * @author Robert Dziuba
 */
public class PerspectiveCamera extends Camera {
    public final double angle;

    public PerspectiveCamera(Point3 e, Vector3 g, Vector3 t, double angle) {
        super(e, g, t);
        this.angle = angle;
    }

    @Override
    public Ray rayFor(int w, int h, int x, int y) {

        // o = e
        // r = -w * (h/2/ tan alpha/2) +(x - w-1/2) * u + (y - h-1/2) * v

        final Vector3 ux = u.mul(x - ((w - 1) / 2));
        final Vector3 vy = v.mul(y - ((h - 1) / 2));
        final Vector3 r = this.w.mul(-1).mul((h / 2) / Math.tan(angle / 2)).add(ux.add(vy));

        return new Ray(e, r.normalized());
    }

    @Override
    public String toString() {
        return "PerspectiveCamera{" +
                "angle=" + angle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PerspectiveCamera that = (PerspectiveCamera) o;

        return Double.compare(that.angle, angle) == 0;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(angle);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
