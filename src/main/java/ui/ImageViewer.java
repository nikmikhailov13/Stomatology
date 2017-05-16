package ui;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ImageViewer {

    private Stage primaryStage;
    private Image image;
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double width = primaryScreenBounds.getWidth()/1.5;
    private final double height = primaryScreenBounds.getHeight()/1.5;


    public ImageViewer(Image image) {
        primaryStage =  new Stage();
        this.image = image;
    }

    public void showInterface () {
        primaryStage.setTitle("Stomatology: Перегляд фото");
        primaryStage.getIcons().add( new Image( Entrance.class.getResourceAsStream("/teeth.png")));

        StackPane stackPane = new StackPane();
        final double SCALE_DELTA = 1.1;
        ImageView imageView = new ImageView(image);

        stackPane.setOnScroll( event -> {
                if (event.getDeltaY() == 0) {
                    return;
                }
                double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1/SCALE_DELTA;

               imageView.setScaleX(imageView.getScaleX() * scaleFactor);
               imageView.setScaleY(imageView.getScaleY() * scaleFactor);

            });

        stackPane.getChildren().addAll(imageView);
        ScrollPane scrollPane = new ScrollPane(stackPane);

        Scene scene = new Scene(stackPane, width, height);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
