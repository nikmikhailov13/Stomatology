package ui;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;


public class Entrance {

    private Stage primaryStage;
    private Label label, loginLabel, passwordLabel, message;
    private PasswordField password;
    private TextField login;
    private Button enterButton;

    public Entrance(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showInterface () {
        primaryStage.setTitle("Stomatology");
        primaryStage.getIcons().add( new Image( Entrance.class.getResourceAsStream("/teeth.png")));
        VBox vBox = new VBox(15);
        HBox hBox1 = new HBox(10);
        HBox hBox2 = new HBox(10);

        loginLabel = new Label("Логін    ");
        passwordLabel = new Label("Пароль");
        label = new Label("Введіть дані для входу в систему");
        message = new Label("");
        login = new TextField();
        password = new PasswordField();

        enterButton = new Button("Увійти");
        enterButton.setOnAction(e -> dataConfirm());
        password.setOnKeyPressed( e -> {
            if (e.getCode() == KeyCode.ENTER)
                dataConfirm();
        });

        hBox1.getChildren().addAll(loginLabel, login);
        hBox2.getChildren().addAll(passwordLabel, password);
        vBox.getChildren().addAll(label, hBox1, hBox2, enterButton, message);

        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10,15,10,15));
        layout.setCenter(vBox);

        Scene scene = new Scene(layout, 250, 200);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void dataConfirm() {
        if (login.getText().equals("dentist") & password.getText().equals("2017")) {
            message.setText("Дані введено правильно");
            message.setTextFill(Color.GREEN);

            PauseTransition pause = new PauseTransition(Duration.seconds(0.7));
            pause.setOnFinished(e -> {

                MainMenu mainMenu = new MainMenu();
                mainMenu.showInterface();
                primaryStage.close();
            });
            pause.play();
        }
        else {
            message.setText("Дані введено неправильно");
            message.setTextFill(Color.RED);
            password.clear();
        }
    }

}
