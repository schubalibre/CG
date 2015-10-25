package Camera;

import MathLib.Point3;
import MathLib.Vector3;
import Tools.Ray;

/**
 * Created by roberto on 25/10/15.
 */
public class PerspectiveCamera extends Camera{
    public final double angle;

    public PerspectiveCamera(Point3 e, Vector3 g, Vector3 t, double angle) {
        super(e, g, t);
        this.angle = angle;
    }

    @Override
    public Ray rayFor(int w, int h, int x, int y) {
        return super.rayFor(w, h, x, y);
    }
}
