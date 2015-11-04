package images;


import geometries.Plane;
import mathLib.Normal3;
import mathLib.Point3;
import mathLib.Vector3;
import camera.Camera;
import tools.World;
import tools.Color;
import camera.PerspectiveCamera;
import javafx.application.Platform;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;

/**
 * Created by roberto on 10/10/15.
 */
public class ImageRendering implements Runnable {

    final private BorderPane root;
    private Camera cam;
    private World world;

    public ImageRendering(BorderPane root) {
        this.root = root;
        initCam();
        initWorld();
    }

    private void initWorld() {
        world = new World(new Color(0,0,0));

        world.addObject(new Plane(
                new Color(0,1,0),
                new Point3(0,-1,0),
                new Normal3(0,1,0)
        ));
    }

    private void initCam() {
        final Point3 e = new Point3(0,0,0);
        final Vector3 g = new Vector3(0,0,-1);
        final Vector3 t = new Vector3(0,1,0);
        final double angle = Math.PI/4;

        cam = new PerspectiveCamera(e,g,t,angle);
    }

    public void run(){
        Platform.runLater(new Runnable() {

            public void run() {

                ImageView imageView = new ImageView();

                WritableImage wImage = new WritableImage((int) root.getWidth(), (int) root.getHeight());

                PixelWriter pixelWriter = wImage.getPixelWriter();

                for (int readY = 0; readY < wImage.getHeight(); readY++){
                    for (int readX = 0; readX < wImage.getWidth(); readX++) {
                        pixelWriter.setColor(readX, readY, getColor(readX, readY));

                        imageView.setImage(wImage);
                        root.setCenter(imageView);
                    }
                }

                /*imageView.setImage(wImage);
                root.setCenter(imageView);*/
            }
        });
    }

    private javafx.scene.paint.Color getColor(int readX, int readY) {

        final Color color = world.hit(cam.rayFor((int)root.getWidth(),(int)root.getHeight(),readX,readY));

        return new javafx.scene.paint.Color(color.r, color.g, color.b,1);
    }
}
