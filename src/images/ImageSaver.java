package images;
/**
 * Created by roberto on 06.10.15.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class ImageSaver extends Application {

    private Scene scene;
    private BorderPane root;
    public static int counter = 0;
    public static long startTime;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();

        scene = new Scene(root, 640, 530);

        scene.widthProperty().addListener(e->{
            //drawImage();
        });

        scene.heightProperty().addListener(e->{
            //drawImage();
        });

        MenuItem save = new MenuItem("save");

        save.setOnAction(e -> saveImage(primaryStage));

        Menu fileMenu = new Menu("File");

        fileMenu.getItems().add(save);

        MenuBar menuBar = new MenuBar(fileMenu);

        root.setTop(menuBar);

        drawImage();

        primaryStage.setTitle("Save Image");

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void drawImage() {

        ImageView imageView = new ImageView();

        WritableImage wImage = new WritableImage((int) root.getWidth(), (int) root.getHeight() - 50);

        imageView.setImage(wImage);

        root.setCenter(imageView);

        int cores = Runtime.getRuntime().availableProcessors();

        Renderer task = null;

        startTime = System.currentTimeMillis();

        for(int core = 1; core <= cores;core++) {

            task = new Renderer(wImage, cores, core);

            Thread t = new Thread(task);
            t.setDaemon(true);
            t.start();
        }

        ProgressBar bar = new ProgressBar();
        bar.progressProperty().bind(task.progressProperty());
        Label label = new Label();
        label.textProperty().bind(task.messageProperty());
        root.setBottom(new HBox(bar,label));
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
        /*WritableImage wim = canvas.snapshot(null, null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
        } catch (Exception e) {
            System.out.println("Konnte Datei nicht speichern!");
        }*/
    }
}
