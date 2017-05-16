package ui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sql.SQLController;

public class TextWindow {

    private Stage stage;
    private Scene scene;
    private BorderPane layout;
    private VBox vBox;
    private TextArea textArea;
    private Button saveButton = new Button("Зберегти");
    private Separator separator;
    private String title;
    private double width, height;


    public TextWindow (String title, double width, double height) {
        textArea = new TextArea();

        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void showWindow(){

        vBox = new VBox(10);
        layout = new BorderPane();
        scene = new Scene(layout, width, height);
        textArea.setPrefHeight(height - 50);

        stage = new Stage();

        separator = new Separator(Orientation.HORIZONTAL);

        stage.getIcons().add( new Image( Entrance.class.getResourceAsStream("/teeth.png")));

        saveButton.setOnAction(event -> save());

        vBox.getChildren().addAll(textArea, saveButton);
        layout.setPadding(new Insets(20, 20, 15, 20));
        layout.setCenter(vBox);
        stage.setScene(scene);
        stage.setTitle("Stomatology : " + title);
        stage.showAndWait();
    }

    public void save () {
        stage.close();
    }

    public void setTextArea(String text){
        textArea.setText(text);
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return textArea.getText();
    }


}
