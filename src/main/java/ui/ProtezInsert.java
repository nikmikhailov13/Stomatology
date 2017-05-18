package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sql.SQLController;

import java.sql.SQLException;
import java.time.LocalDate;

public class ProtezInsert {

    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double width = primaryScreenBounds.getWidth() / 3.3;
    private final double height = primaryScreenBounds.getHeight() / 3;
    private Stage stage;
    private Scene scene;
    private StackPane stackPane;
    private VBox vBox1;
    private TextArea textArea1;
    private Button save;
    private DatePicker datePicker;
    private Label message;
    private Client client;
    private PatientInfo patientInfo;

    public ProtezInsert(Client client, PatientInfo patientInfo) {
        this.client = client;
        this.patientInfo = patientInfo;

        stage = new Stage();
        stackPane = new StackPane();
        scene = new Scene(stackPane, width, height);

        vBox1 = new VBox(10);
        textArea1 = new TextArea();

        save = new Button("Зберегти");
        datePicker = new DatePicker();
        message = new Label("");
    }

    public void showInterface() {
        stage.setTitle("Stomatology: Додавання запису");
        stage.getIcons().add(new Image(Entrance.class.getResourceAsStream("/teeth.png")));
        stackPane.setPadding(new Insets(20));

        addItemsToBoxes();
        save.setOnAction(event -> saveData());
        stage.setScene(scene);
        stage.show();
    }

    private void addItemsToBoxes() {
        textArea1.setPromptText("Протезування");
        textArea1.setPrefWidth(width / 2);
        datePicker.setValue(LocalDate.now());
        message.setPrefWidth(width / 2);
        save.setPrefWidth(width / 5.5);
        save.setTextFill(Color.GREEN);

        vBox1.getChildren().addAll(datePicker, textArea1, save, message);
        stackPane.getChildren().add(vBox1);
    }

    private void saveData() {
        message.setText("");
        try {
            setClientProtez();
            message.setText(" Інформацію додано до бази!");
            message.setTextFill(Color.GREEN);
            patientInfo.initProtezTable(client);
        } catch (SQLException e) {
            e.printStackTrace();
            message.setText("Виникла помилка при доданні до базы");
            message.setTextFill(Color.RED);
        }
    }

    private void setClientProtez() throws SQLException {
        java.sql.Date date = java.sql.Date.valueOf(datePicker.getValue());
        String protez = textArea1.getText();
        SQLController sqlController = new SQLController();
        sqlController.setClientProtez(date, protez, client.getId());
    }

}
