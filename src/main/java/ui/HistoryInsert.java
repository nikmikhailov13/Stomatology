package ui;

import javafx.application.Platform;
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
import java.util.Arrays;
import java.util.List;

public class HistoryInsert {

    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double width = primaryScreenBounds.getWidth() / 2.3;
    private final double height = primaryScreenBounds.getHeight() / 2.5;
    private Stage stage;
    private Scene scene;
    private StackPane stackPane;
    private VBox vBox, vBox1;
    private HBox hBox1, hBox2;
    private ComboBox<String> comboBox1, comboBox2, comboBox3, comboBox4;
    private TextArea textArea1;
    private Button save;
    private DatePicker datePicker;
    private Label message;
    private Client client;
    private PatientInfo patientInfo;
    private ObservableList<String> ilnesses, treatments;

    public HistoryInsert(Client client, PatientInfo patientInfo) {
        this.client = client;
        this.patientInfo = patientInfo;

        stage = new Stage();
        stackPane = new StackPane();
        scene = new Scene(stackPane, width, height);

        vBox = new VBox(20);
        vBox1 = new VBox(10);
        hBox1 = new HBox(10);
        hBox2 = new HBox(10);

        comboBox1 = new ComboBox<>();
        comboBox2 = new ComboBox<>();
        comboBox3 = new ComboBox<>();
        comboBox4 = new ComboBox<>();

        textArea1 = new TextArea();

        save = new Button("Зберегти");
        datePicker = new DatePicker();
        message = new Label("");
    }

    public void showInterface() {
        stage.setTitle("Stomatology: Додавання запису");
        stage.getIcons().add(new Image(Entrance.class.getResourceAsStream("/teeth.png")));
        stackPane.setPadding(new Insets(20));

        SQLController sqlController = new SQLController();
        ilnesses = sqlController.getListOf("SELECT name FROM diagnoses ORDER BY name");
        treatments = sqlController.getListOf("SELECT treatment FROM treatments ORDER BY treatment");
        addItemsToBoxes();
        setItems();
        save.setOnAction(event -> saveData());
        stage.setScene(scene);
        stage.show();

    }

    private void addItemsToBoxes() {

        textArea1.setPrefWidth(width / 2);
        datePicker.setPrefWidth(width / 2.3);
        datePicker.setValue(LocalDate.now());
        message.setPrefWidth(width / 2.65);
        save.setPrefWidth(width / 5.5);
        save.setTextFill(Color.GREEN);

        vBox1.getChildren().addAll(datePicker, comboBox1, comboBox2, comboBox3, comboBox4, save, message);
        hBox1.getChildren().addAll(vBox1, textArea1);
        stackPane.getChildren().add(hBox1);
    }

    private void setItems() {

        setComboBox(comboBox1, "Діагноз", ilnesses);
        setComboBox(comboBox2, "Лікування 1", treatments);
        setComboBox(comboBox3, "Лікування 2", treatments);
        setComboBox(comboBox4, "Лікування 3", treatments);

        textArea1.setPromptText("Анамнез, статус, рекомендації");
        message.setAlignment(Pos.BOTTOM_CENTER);
    }

    private void setComboBox(ComboBox<String> cb, String promptText, ObservableList<String> items) {
        cb.setEditable(true);
        cb.setPrefWidth(width / 2.3);


        // Create a FilteredList wrapping the ObservableList.
        FilteredList<String> filteredItems = new FilteredList<>(items, p -> true);

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
        cb.setPromptText(promptText);
    }

    private void saveData() {
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

    private void setClientHistory() throws SQLException {

        SQLController sqlController = new SQLController();

        List<ComboBox> comboBoxes = Arrays.asList(comboBox2, comboBox3, comboBox4);

        String treatmentList = "";

        for (int i = 0; i < 3; i++) {

            if (comboBoxes.get(i).getValue() != null) {
                String treatment = comboBoxes.get(i).getValue().toString();
                treatmentList += treatment + "\n";

                if (!exists(treatment, treatments))
                    sqlController.addTreatment(treatment);
            }
        }

        String diagnose = comboBox1.getValue();
        if (diagnose != null) {
            if (!exists(diagnose, ilnesses)) {
                sqlController.addDiagnose(diagnose);
            }
        }


        java.sql.Date date = java.sql.Date.valueOf(datePicker.getValue());
        String notes = textArea1.getText();


        sqlController.setClientHistory(client.getId(), diagnose, date, notes, treatmentList);

    }


    private boolean exists(String item, ObservableList<String> items) {

        boolean answer = false;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null && items.get(i).toLowerCase().equals(item.toLowerCase()))
                answer = true;
        }
        return answer;
    }
}
