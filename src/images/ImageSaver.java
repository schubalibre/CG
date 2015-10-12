package images;
/**
 * Created by roberto on 06.10.15.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class ImageSaver extends Application {

    private Scene scene;
    private BorderPane root;

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

        PixelWriter pixelWriter = wImage.getPixelWriter();

        imageView.setImage(wImage);

        root.setCenter(imageView);

        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println(getCore());

                for (int readY = 0; readY < wImage.getHeight(); readY++) {

                    for (int readX = 0; readX < wImage.getWidth(); readX++) {

                        final int finalReadX = readX, finalReadY = readY;

                        Platform.runLater(new Runnable() {
                            public void run() {
                                pixelWriter.setColor(finalReadX, finalReadY, getColor(finalReadX, finalReadY));
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
                    updateProgress(readY, wImage.getHeight());
                }
                return null;
            }
        };

        ProgressBar bar = new ProgressBar();
        bar.progressProperty().bind(task.progressProperty());
        root.setBottom(bar);

        int cores = Runtime.getRuntime().availableProcessors();

        setCores(cores);

        for(int core =1; core <= cores; core++) {
            Thread t = new Thread(task);
            t.setDaemon(true);
            t.start();
        }
    }

    private int cores;

    final private void setCores(int cores){
        this.cores=cores+1;
    }

    final private int getCore(){
        cores--;
        return cores;
    }

    private Color getColor(int readX, int readY) {
        return (readX == readY) ? Color.RED : Color.BLACK;
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
