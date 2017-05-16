package ui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryInsert {

    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double width = primaryScreenBounds.getWidth()/2.3;
    private final double height = primaryScreenBounds.getHeight()/1.5;
    private Stage stage;
    private Scene scene;
    private StackPane stackPane;
    private VBox vBox, vBox1, vBox2, vBox3;
    private HBox hBox1, hBox2, hBox3, hBox4;
    private Label diagnose1, diagnose2, diagnose3;
    private ComboBox<String> comboBox1, comboBox2, comboBox3;
    private TextArea textArea1, textArea2, textArea3;
    private Button save;
    private DatePicker datePicker;
    private Label message;
    private Client client;
    private PatientInfo patientInfo;
    private ObservableList<String> items;

    public HistoryInsert (Client client, PatientInfo patientInfo) {
        this.client = client;
        this.patientInfo = patientInfo;

        stage = new Stage();
        stackPane = new StackPane();
        scene = new Scene(stackPane, width, height);
        vBox = new VBox(20);
        vBox1 = new VBox(10);
        vBox2 = new VBox(10);
        vBox3 = new VBox(10);

        hBox1 = new HBox(10);
        hBox2 = new HBox(10);
        hBox3 = new HBox(10);
        hBox4 = new HBox(10);

        diagnose1 = new Label("Діагноз 1");
        diagnose2 = new Label("Діагноз 2");
        diagnose3 = new Label("Діагноз 3");

        comboBox1 = new ComboBox<>();
        comboBox2 = new ComboBox<>();
        comboBox3 = new ComboBox<>();

        textArea1 = new TextArea();
        textArea2 = new TextArea();
        textArea3 = new TextArea();

        save = new Button("Зберегти");
        datePicker = new DatePicker();
        message = new Label("");
    }

    public void  showInterface () {
       stage.setTitle("Stomatology: Додавання запису");
       stage.getIcons().add( new Image( Entrance.class.getResourceAsStream("/teeth.png")));
       stackPane.setPadding(new Insets(20));

        SQLController sqlController = new SQLController();
       items = sqlController.getListOf("SELECT name FROM diagnoses");
       addItemsToBoxes();
       setItems();
       save.setOnAction(event -> saveData());
       stage.setScene(scene);
       stage.show();

    }

    private void addItemsToBoxes () {

        textArea1.setPrefWidth(width/2.2);
        textArea2.setPrefWidth(width/2.2);
        textArea3.setPrefWidth(width/2.2);

        hBox1.getChildren().addAll(comboBox1, textArea1);
        hBox2.getChildren().addAll(comboBox2, textArea2);
        hBox3.getChildren().addAll(comboBox3, textArea3);

        datePicker.setValue(LocalDate.now());
        datePicker.setPrefWidth(width/2.75);
        message.setPrefWidth(width/2.65);
        save.setPrefWidth(width/5.5);
        save.setTextFill(Color.GREEN);
        hBox4.getChildren().addAll(datePicker, message, save);

        vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4);
        stackPane.getChildren().add(vBox);
    }

    private void setItems(){

        setComboBox(comboBox1, "Діагноз 1");
        setComboBox(comboBox2, "Діагноз 2");
        setComboBox(comboBox3, "Діагноз 3");

        textArea1.setPromptText("Додайте нотатки");
        textArea2.setPromptText("Додайте нотатки");
        textArea3.setPromptText("Додайте нотатки");

        message.setAlignment(Pos.BOTTOM_CENTER);


    }

    private void setComboBox(ComboBox<String> cb, String promptText) {
        cb.setEditable(true);
        cb.setPrefWidth(width/2);
        cb.setPromptText(promptText);


        // Create a FilteredList wrapping the ObservableList.
        FilteredList<String> filteredItems = new FilteredList<String>(items, p -> true);

        // Add a listener to the textProperty of the combobox editor. The
        // listener will simply filter the list every time the input is changed
        // as long as the user hasn't selected an item in the list.
        cb.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = cb.getEditor();
            final String selected = cb.getSelectionModel().getSelectedItem();

            // This needs run on the GUI thread to avoid the error described
            // here: https://bugs.openjdk.java.net/browse/JDK-8081700.
            Platform.runLater(() -> {
                // If the no item in the list is selected or the selected item
                // isn't equal to the current input, we refilter the list.
                if (selected == null || !selected.equals(editor.getText())) {
                    filteredItems.setPredicate(item -> {
                        // We return true for any items that starts with the
                        // same letters as the input. We use toUpperCase to
                        // avoid case sensitivity.
                        if (item.toUpperCase().startsWith(newValue.toUpperCase())) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
            });
        });

        cb.setItems(filteredItems);
    }

    private void saveData () {
        message.setText("");
        try {
            setClientHistory();
            message.setText(" Інформацію додано до бази!");
            message.setTextFill(Color.GREEN);
            patientInfo.initHistoryTable(client);
        } catch (SQLException e) {
            message.setText("Виникла помилка при доданні до базы");
            message.setTextFill(Color.RED);
        }
    }

    private void setClientHistory () throws SQLException {
        List<ComboBox> comboBoxes = Arrays.asList(comboBox1, comboBox2, comboBox3);
        List<TextArea> textAreas = Arrays.asList(textArea1, textArea2, textArea3);
        SQLController sqlController = new SQLController();


        for (int i = 0; i < 3; i++) {
            if (comboBoxes.get(i).getValue()!= null) {
                String diagnose = comboBoxes.get(i).getValue().toString();

                if (!diagnoseExists(diagnose)){
                    sqlController.addDiagnose(diagnose);
                }

                java.sql.Date date = java.sql.Date.valueOf(datePicker.getValue());
                String notes = textAreas.get(i).getText();


                    sqlController.setClientHistory(client.getId(), diagnose, date, notes);

            }
        }
    }

    private boolean diagnoseExists (String diagnose) {

        boolean answer = false;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equals(diagnose))
                answer = true;
        }
        return answer;
    }
}
