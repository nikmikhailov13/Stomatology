package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class MainMenu {

    private Stage primaryStage;
    private final int width = 130;
    private final int height = 120;
    private Button newVisit, existingPatient, settingsButton, emailSender;
    private VBox vBox;
    private HBox hBox1, hBox2;
    private Scene scene;
    private BorderPane layout;
    private BackgroundImage background;
    public final static String fontName = "Bookman Old Style";

    public MainMenu () {
        primaryStage = new Stage();

        layout = new BorderPane();
        scene = new Scene(layout, 440, 425);

        vBox = new VBox(125);
        hBox1 = new HBox(120);
        hBox2 = new HBox(120);

        newVisit = new Button("Новий візит");
        existingPatient = new Button("Пацієнти");
        settingsButton = new Button("Налаштування");
        emailSender = new Button("Розсилка \n   листів");
        newVisit.setPrefSize(width, height);
        existingPatient.setPrefSize(width, height);
        settingsButton.setPrefSize(width, height);
        emailSender.setPrefSize(width, height);
    }

    public void showInterface () {

       buttonsSetOnAction();
       setLayout();
       setStage();

    }

    private void buttonsSetOnAction() {

        List<Button> buttonList = Arrays.asList(newVisit, existingPatient, settingsButton, emailSender);
        buttonList.forEach(button -> button.setFont(Font.font(fontName, 14)));


        newVisit.setOnAction(event -> {

                try {
                    Desktop.getDesktop().browse(new URL("https://calendar.google.com/calendar").toURI());
                } catch (Exception e) {
                    e.printStackTrace();
                }

        });

        existingPatient.setOnAction(e -> {
            ExistingPatient existingPatient = new ExistingPatient("SELECT * FROM client ORDER BY name");
            existingPatient.showInterface();
            primaryStage.close();

        });

        emailSender.setOnAction(e -> {
            MailSender mailSender = new MailSender(primaryStage);
            mailSender.showInterface();
        });

        settingsButton.setOnAction(e -> {

            Settings settings = new Settings();
            settings.showInterface();
            primaryStage.close();

        });

    }

    private void setLayout() {
        hBox1.getChildren().addAll(newVisit, existingPatient);
        hBox2.getChildren().addAll( emailSender, settingsButton);
        vBox.getChildren().addAll(hBox1, hBox2);

        layout.setPadding(new Insets(30,30,30,30));
        layout.setCenter(vBox);
        background = new BackgroundImage(new Image( MainMenu.class.getResourceAsStream("/teeth.png")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        layout.setBackground(new Background(background));
    }

    private void setStage() {
        primaryStage.setTitle("Stomatology : Головне меню");
        primaryStage.getIcons().add( new Image( MainMenu.class.getResourceAsStream("/teeth.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
