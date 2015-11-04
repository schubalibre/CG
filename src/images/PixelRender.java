package images;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Created by roberto on 16.10.15.
 */
public class PixelRender extends Render{

    private WritableImage wImage;
    private PixelWriter pixelWriter;

    public PixelRender(WritableImage wImage) {
        super();
        this.wImage = wImage;
        this.pixelWriter =  wImage.getPixelWriter();
    }

    public void start(){
        for (double readY = 0; readY < wImage.getHeight(); readY++) {
            for (double readX = 0; readX < wImage.getWidth(); readX++) {
                pixelWriter.setColor((int) readX, (int) readY, getColor(readX, readY));
            }
        }
    }

    private Color getColor(double readX, double readY) {
        final tools.Color color = world.hit(cam.rayFor((int) wImage.getWidth(), (int) wImage.getHeight(), (int) readX, (int) (wImage.getHeight() - readY) ));
        return new javafx.scene.paint.Color(color.r, color.g, color.b,1);
    }
}
