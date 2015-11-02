package Camera;

import MathLib.Point3;
import MathLib.Vector3;
import Tools.Ray;

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
        // r = -w * (h/2/ tan alpha) +(x - w-1/s) * u + (y - h-1/2) * v

        final Point3 o = e;

        final Vector3 ux = u.mul(x - ((w - 1) / 2));
        final Vector3 vy = v.mul(y - ((h - 1) / 2));
        final Vector3 r = super.w.mul(-1).mul((h / 2) / Math.tan(angle)).add(ux.add(vy));
        final Vector3 d = r.normalized();

        return new Ray(o, d);
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
