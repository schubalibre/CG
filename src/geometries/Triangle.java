package geometries;

import mathLib.Mat3x3;
import mathLib.Point3;
import mathLib.Vector3;
import tools.Color;
import tools.Ray;

/**
 * Created by roberto on 03.11.15.
 */
public class Triangle extends Geometry{
    final private Point3 a;
    final private Point3 b;
    final private Point3 c;

    public Triangle(Point3 a, Point3 b, Point3 c, Color color) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public Hit hit(Ray r) {

        // M x = V

        final Vector3 v = new Vector3(a.x - r.o.x, a.y - r.o.y, a.z - r.o.z);

        final Mat3x3 m = new Mat3x3(
                a.x - b.x, a.x - c.x, r.d.x,
                a.y - b.y, a.y - c.y, r.d.y,
                a.z - b.z, a.z - c.z, r.d.z
        );

        final double detA = m.determinante;
        final double detA1 = m.changeCol1(v).determinante;
        final double detA2 = m.changeCol2(v).determinante;
        final double detA3 = m.changeCol3(v).determinante;

        final double beta = detA1 / detA;
        final double gamma = detA2 / detA;
        final double t = detA3 / detA;

        if((beta > 0 && gamma > 0 ) && beta + gamma <= 1){
            return new Hit(t, r, this);
        }

        return null;
    }
}
