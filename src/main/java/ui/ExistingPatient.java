package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sql.SQLController;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExistingPatient {
    private int width = 800;
    private int height = 650;
    private Stage primaryStage;
    private TableView tableView;
    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private ObservableList<Client> sortedClients = FXCollections.observableArrayList();
    private SQLController SQLController;
    private VBox vBox;
    private HBox hBox1, hBox2;
    private TextField nameSearchTextField, phoneSearchTextField, doctorSearchTextField, ageSearchTextField;
    private Button addButton, backButton, deleteButton, changeButton;
    private BorderPane layout;
    private Scene scene;
    private Separator separator1, separator2;
    private final String DELETE = "DELETE FROM client WHERE id = ?";
    private  String QUERY;


    public ExistingPatient(String query) {
        this.QUERY = query;
        primaryStage = new Stage();
        layout = new BorderPane();
        tableView = new TableView();
        hBox1 = new HBox();
        hBox2 = new HBox(10);
        vBox = new VBox(10);
        nameSearchTextField = new TextField();
        phoneSearchTextField = new TextField();
        doctorSearchTextField = new TextField();
        ageSearchTextField = new TextField();

        backButton = new Button("   До головного меню   ");
        deleteButton = new Button("   Видалити пацієнта   ");
        changeButton = new Button("   Змінити   ");
        addButton = new Button("    Додати пацієнта    ");

        SQLController = new SQLController();
    }

    public void showInterface() {
        primaryStage.setTitle("Stomatology : База пацієнтів");
        primaryStage.getIcons().add(new Image(MainMenu.class.getResourceAsStream("/teeth.png")));


        hBox1.getChildren().addAll(nameSearchTextField, ageSearchTextField, phoneSearchTextField, doctorSearchTextField);

        initTableView(QUERY);

        List<TextField> textFields = Arrays.asList(nameSearchTextField, ageSearchTextField, phoneSearchTextField, doctorSearchTextField);
        textFields.forEach(textField -> {
            textField.setPrefWidth((width - 20) / 4);
            textField.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    filterTable(clients, sortedClients, tableView);
                }
            });
        });

        nameSearchTextField.setPromptText("Пошук за ім'ям");
        ageSearchTextField.setPromptText("Пошук за датою");
        phoneSearchTextField.setPromptText("Пошук за номером");
        doctorSearchTextField.setPromptText("Пошук за доктором");

        hBox2.getChildren().addAll(backButton, addButton, deleteButton);
        buttonsSetOnAction();

        vBox.getChildren().addAll(hBox1, hBox2);

        layout.setBottom(vBox);
        layout.setCenter(tableView);
        layout.setPadding(new Insets(10));
        scene = new Scene(layout, width, height);
        layout.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initTableView(String query) {
        TableColumn<Client, String> name = new TableColumn<>("Ім'я");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<Client, String> bdate = new TableColumn<>("Дата народження");
        bdate.setCellValueFactory(new PropertyValueFactory<>("bdate"));

        TableColumn<Client, String> phone = new TableColumn<>("Номер телефону");
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Client, String> doctor = new TableColumn<>("Лікар");
        doctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));

        tableView.getColumns().addAll(name, bdate, phone, doctor);

        ObservableList<TableColumn> list = tableView.getColumns();

        for (TableColumn tablecolumn : list) {
            tablecolumn.setPrefWidth((width - 21) / 4);
        }
        clients = SQLController.getClients(query);

        tableView.setItems(clients);


        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Client client = (Client)tableView.getSelectionModel().getSelectedItem();
                PatientInfo patientInfo = new PatientInfo();
                patientInfo.initialize();
                patientInfo.fillGaps(client);
                patientInfo.setButtonsUpdate(client, this);
                patientInfo.show();
            }
        });

    }

    private boolean contains(String text, String word) {
        return "".equals(word) || text.toLowerCase().contains(word.toLowerCase());
    }

    public class Predicates {

        Predicate<Client> namePredicate = client -> contains(client.getName(), nameSearchTextField.getText());
        Predicate<Client> bdatePredicate = client -> contains(client.getBdate().toString(), ageSearchTextField.getText());
        Predicate<Client> phonePredicate = client -> contains(client.getPhone(), phoneSearchTextField.getText());
        Predicate<Client> doctorPredicate = client -> contains(client.getDoctor(), doctorSearchTextField.getText());

    }

    private List<Client> filter(List<Client> source) {
        Predicates predicate = new Predicates();
        return source.stream().filter(predicate.namePredicate).filter(predicate.bdatePredicate)
                .filter(predicate.phonePredicate).filter(predicate.doctorPredicate).collect(Collectors.toList());
    }

    public void filterTable(ObservableList original, ObservableList sorted, TableView table) {
        sorted.clear();
        sorted.addAll(filter(original));
        table.setItems(sorted);
        table.refresh();
    }

    private void buttonsSetOnAction() {

        backButton.setOnAction(e -> {

            MainMenu mainMenu = new MainMenu();
            mainMenu.showInterface();
            primaryStage.close();
        });

        deleteButton.setOnAction(e -> {
            Client client = (Client) tableView.getSelectionModel().getSelectedItem();
            boolean confirm = Dialog.showConfirmationDialog("Видалення пацієнта", "Ви впевнені, що хочете видалити\n" + client.getName() +"?",
                    "Інформація буде стерта назавжди" );
            if(confirm){
            SQLController.deleteFromTable(client.getId(), DELETE);
            clients.remove(client);
            tableView.refresh();}

        });

        changeButton.setOnAction(event -> tableView.refresh());

        addButton.setOnAction(e -> {
            PatientInfo patientInfo = new PatientInfo();
            patientInfo.initialize();
            patientInfo.setButtonsSave(this);
            patientInfo.show();
        });
    }

    public void refresh() {
        clients.clear();
        clients = SQLController.getClients(QUERY);
        tableView.setItems(clients);
        tableView.refresh();
    }
}
