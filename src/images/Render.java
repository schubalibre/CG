package images;

import geometries.AxisAlignedBox;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import mathLib.Normal3;
import mathLib.Point3;
import mathLib.Vector3;
import tools.Color;
import tools.World;
import camera.Camera;
import camera.PerspectiveCamera;

/**
 * Created by roberto on 03.11.15.
 */
public abstract class Render {

    protected Camera cam;
    protected World world;

    public Render() {
        initCam();
        initWorld();
    }

    public abstract void start();

    private void initWorld() {
        world = new World(new tools.Color(0,0,0));

        world.addObject(new Plane(
                new Color(0,1,0),
                new Point3(0,-1,0),
                new Normal3(0,1,0)
        ));

        world.addObject(new Sphere(
                new Color(1,0,0),
                new Point3(0,0,-3),
                0.5
        ));

        world.addObject(new Triangle(
                new Point3(-0.5, 0.5, -3),
                new Point3(0.5,0.5,-3),
                new Point3(0.5,-0.5,-3),
                new Color(1,0,1)
        ));

        world.addObject(new AxisAlignedBox(
                new Color(0,0,1),
                new Point3(-0.5,0,-0.5),
                new Point3(0.5,1,0.5)

        ));
    }

    private void initCam() {
        final Point3 e = new Point3(3,3,3);
        final Vector3 g = new Vector3(-3,-3,-3);
        final Vector3 t = new Vector3(0,1,0);
        final double angle = Math.PI/4;

        cam = new PerspectiveCamera(e,g,t,angle);
    }
}