package images;
/**
 * Created by roberto on 06.10.15.
 */

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;


public class ImageSaver extends Application {

    private Canvas canvas;
    private Scene scene;
    private MenuBar menuBar;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        MenuItem save = new MenuItem("save");

        save.setOnAction(e -> saveImage(primaryStage));

        Menu fileMenu = new Menu("File");

        fileMenu.getItems().add(save);

        menuBar = new MenuBar(fileMenu);

        canvas = new Canvas();

        VBox root = new VBox(menuBar, canvas);

        scene = new Scene(root, 640, 480);

        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty().subtract(menuBar.heightProperty()));

        scene.widthProperty().addListener(e->{
            drawImages(canvas.getGraphicsContext2D());
        });

        scene.heightProperty().addListener(e->{
            drawImages(canvas.getGraphicsContext2D());
        });

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);

        drawImages(canvas.getGraphicsContext2D());
        primaryStage.show();
    }

    private void drawImages(final GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setStroke(Color.RED);
        gc.setLineWidth(1);
        gc.strokeLine(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void saveImage(final Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resource File");
        File file = fileChooser.showSaveDialog(primaryStage);

        if(file != null){
            saveFile(file);
        }
    }

    private void saveFile(final File file) {
        WritableImage wim = canvas.snapshot(null, null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
        } catch (Exception e) {
            System.out.println("Konnte Datei nicht speichern!");
        }
    }
}
