package geometries;

import mathLib.Normal3;
import mathLib.Point3;
import tools.Color;
import tools.Ray;

/**
 * Created by roberto on 03.11.15.
 */
public class AxisAlignedBox extends Geometry{
    final Point3 lbf;
    final Point3 run;

    public AxisAlignedBox(Color color, Point3 lbf, Point3 run) {
        super(color);
        this.lbf = lbf;
        this.run = run;
    }

    @Override
    public Hit hit(Ray r) {

        Plane[] planes = new Plane[6];

        planes[0] = new Plane( // front
                color,
                run,
                new Normal3(0,0,1)
        );
        planes[1] = new Plane( // back
                color,
                lbf,
                new Normal3(0,0,-1)
        );
        planes[2] =  new Plane( // left
                color,
                lbf,
                new Normal3(-1,0,0)
        );
        planes[3] = new Plane( // right
                color,
                run,
                new Normal3(1,0,0)
        );
        planes[4] = new Plane( // up
                color,
                run,
                new Normal3(0,1,0)

        );
        planes[5] = new Plane( // down
                color,
                lbf,
                new Normal3(0,-1,0)
        );

        Hit max = null;

        for(Plane plane : planes){
            final double con = plane.a.sub(r.o).dot(plane.n);
            if(con > 0) {
                final double t = con / r.d.dot(plane.n);
                if (max == null || t > max.t) {
                    max = new Hit(t, r, this);
                }
            }
        }

        return comparison(max);
    }

    private Hit comparison(Hit hit) {
        if(hit != null) {
            Point3 p = hit.ray.at(hit.t);

            final double e = 0.00000000001;

            if (    (lbf.x <= p.x+e && p.x <= run.x+e) &&
                    (lbf.y <= p.y+e && p.y <= run.y+e) &&
                    (lbf.z <= p.z+e && p.z <= run.z+e)
                    )
                return hit;
        }

        return null;
    }

}
