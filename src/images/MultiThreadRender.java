package images;

import geometries.Plane;
import mathLib.Normal3;
import mathLib.Point3;
import mathLib.Vector3;
import tools.World;
import camera.Camera;
import camera.PerspectiveCamera;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * @author roberto
 * @
 */
public class MultiThreadRender extends Task {

    private PixelWriter pixelWriter;
    private WritableImage wImage;
    private int cores;
    private int core;

    private Camera cam;
    private World world;

    public MultiThreadRender(WritableImage wImage, int cores, int core) {
        this.wImage = wImage;
        this.pixelWriter =  wImage.getPixelWriter();
        this.cores = cores;
        this.core = core;
        initCam();
        initWorld();
    }

    @Override
    protected Object call() throws Exception {

        final double img_height = wImage.getHeight();
        final double img_width = wImage.getWidth();
        final double img_size = img_height * img_width;

        final double max_height = img_height / cores;

        for (double readY = ( max_height * core) - max_height; readY < max_height * core; readY++) {

            for (double readX = 0; readX < img_width; readX++) {

                final double finalReadX = readX, finalReadY = readY;

                Platform.runLater(new Runnable() {
                    public void run() {

                        pixelWriter.setColor((int) finalReadX, (int) finalReadY, getColor(finalReadX, finalReadY));

                        updateProgress(ImageSaver.counter++, img_size);
                        updateTime(img_size);
                    }
                });

                if (isCancelled()) {
                    break;
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException interrupted) {
                    System.out.println("Abbruch");
                }
            }

        }
        return null;
    }

    private void updateTime(double img_size) {

        long elapsedTime = (System.currentTimeMillis() - ImageSaver.startTime) / 1000;

        // counter / elapsedTime = maxCounter / estimatedTime

        long estimatedTime = (long)((double)elapsedTime / ImageSaver.counter * (img_size));

        updateMessage("elapsed Time: " + elapsedTime + "sec. estimated Time: " + estimatedTime + " sec.");
    }

    private void initWorld() {
        world = new World(new tools.Color(0,0,0));

        world.addObject(new Plane(
                new tools.Color(0,1,0),
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

    private Color getColor(double readX, double readY) {
        final tools.Color color = world.hit(cam.rayFor((int) wImage.getWidth(), (int) wImage.getHeight(), (int) readX, (int) readY));
        return new javafx.scene.paint.Color(color.r, color.g, color.b,1);
    }
}
