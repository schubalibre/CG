package Camera;

import MathLib.Point3;
import MathLib.Vector3;
import Tools.Ray;

/**
 * Created by roberto on 25/10/15.
 * @author Robert Dziuba
 */
public abstract class Camera {
    public final Point3 e;
    public final Vector3 g;
    public final Vector3 t;
    public final Vector3 u;
    public final Vector3 v;
    public final Vector3 w;


    /**
     *
     * @param e eye position
     * @param g gaze direction
     * @param t up vector
     */
    public Camera(Point3 e, Vector3 g, Vector3 t) {
        if (e == null) {
            throw new IllegalArgumentException("The Point  e cannot be null!");
        }
        if (g == null) {
            throw new IllegalArgumentException("The Vector g cannot be null!");
        }
        if (t == null) {
            throw new IllegalArgumentException("The Vector t cannot be null!");
        }
        this.e = e;
        this.g = g;
        this.t = t;
        /**
         * w = - g/|g|
         */

        this.w = g.normalized().mul(-1);
        /**
         * u = t x w / |t x w|
         */
        this.u = new Vector3(t.x(w).x / t.x(w).magnitude, t.x(w).y / t.x(w).magnitude, t.x(w).z / t.x(w).magnitude);
        /**
         * v = w x u - w und u sind schon normalisiert
         */
        this.v = new Vector3(w.x(u).x, w.x(u).y, w.x(u).z);
    }

    public abstract Ray rayFor(int w, int h, int x, int y);

    @Override
    public String toString() {
        return "Camera{" +
                "e=" + e +
                ", g=" + g +
                ", t=" + t +
                ", u=" + u +
                ", v=" + v +
                ", w=" + w +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Camera camera = (Camera) o;

        if (e != null ? !e.equals(camera.e) : camera.e != null) return false;
        if (g != null ? !g.equals(camera.g) : camera.g != null) return false;
        if (t != null ? !t.equals(camera.t) : camera.t != null) return false;
        if (u != null ? !u.equals(camera.u) : camera.u != null) return false;
        if (v != null ? !v.equals(camera.v) : camera.v != null) return false;
        return !(w != null ? !w.equals(camera.w) : camera.w != null);

    }

    @Override
    public int hashCode() {
        int result = e != null ? e.hashCode() : 0;
        result = 31 * result + (g != null ? g.hashCode() : 0);
        result = 31 * result + (t != null ? t.hashCode() : 0);
        result = 31 * result + (u != null ? u.hashCode() : 0);
        result = 31 * result + (v != null ? v.hashCode() : 0);
        result = 31 * result + (w != null ? w.hashCode() : 0);
        return result;
    }
}
