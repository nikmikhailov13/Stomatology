package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sql.SQLController;

import java.util.Arrays;
import java.util.List;

public class Settings{
    private Stage primaryStage;
    private TableView doctorTableView, diagnoseTableView, treatmentsTableView;
    private ObservableList<Diagnose> diagnoses = FXCollections.observableArrayList();
    private ObservableList<Treatment> treatments = FXCollections.observableArrayList();
    private TabPane tabPane;
    private FlowPane flowPane;
    private Tab doctorTab, diagnoseTab, treatmentTab, chartTab, infoTab;
    private VBox vBox, vBox1, vBox2, vBox3;
    private HBox hBox1, hBox2, hBox3, hBox4;
    private TextField nameTextField, phoneTextField, emailTextField;
    private TextArea diagnoseNameText, treatmentNameText;
    private Button backButton, deleteDoctor, addDoctor, addTreatment, deleteTreatment, backButton2;
    private Button backButton1, deleteDiagnose, addDiagnose;
    private BorderPane layout;
    private Scene scene;
    private List<TextField> textFields;
    private SQLController sqlController = new SQLController();
    private ObservableList<Doctor> doctors = sqlController.getDoctors();

    private final String DOCTORDELETE = "DELETE FROM doctor WHERE id = ?";
    private final String DIAGNOSEDELETE = "DELETE FROM diagnoses WHERE id = ?";
    private final String TREATMENT_DELETE = "DELETE FROM treatments WHERE id_treatment = ?";

    public Settings() {
        primaryStage = new Stage();
        layout = new BorderPane();
        flowPane = new FlowPane();

        nameTextField = new TextField();
        phoneTextField = new TextField();
        emailTextField = new TextField();

        diagnoseNameText = new TextArea();
        treatmentNameText = new TextArea();

        backButton = new Button(" Назад ");
        deleteDoctor = new Button(" Видалити ");
        addDoctor = new Button(" Додати ");
        backButton1 = new Button(" Назад ");
        addDiagnose = new Button(" Додати ");
        addTreatment = new Button(" Додати ");
        backButton2 = new Button(" Назад ");
        addDiagnose = new Button(" Додати ");
        deleteTreatment = new Button(" Видалити ");
        deleteDiagnose = new Button(" Видалити ");



        doctorTableView = new TableView();
        diagnoseTableView = new TableView();
        treatmentsTableView = new TableView();

        tabPane = new TabPane();
        doctorTab = new Tab("       Лікарі      ");
        diagnoseTab = new Tab("      Діагнози      ");
        chartTab = new Tab("     Статистика     ");
        infoTab = new Tab("     Про програму    ");
        treatmentTab = new Tab("    Лікування    ");

        hBox1 = new HBox(10);
        hBox2 = new HBox(5);
        hBox3 = new HBox(5);
        hBox4 = new HBox(10);
        vBox2 = new VBox();
        vBox3 = new VBox();
        vBox = new VBox();
        vBox1 = new VBox(5);
        scene = new Scene(tabPane, 600, 500);

    }

    public void showInterface() {
        primaryStage.setTitle("Stomatology : Налаштування");
        primaryStage.getIcons().add(new Image(MainMenu.class.getResourceAsStream("/teeth.png")));


        initDoctorTableView();
        initDiagnoseTableView();
        initTreatmentTableView();

        buttonSetOnAction();
        textFields = Arrays.asList(nameTextField, phoneTextField, emailTextField);
        textFields.forEach(textField -> {
            textField.setPrefWidth(170);
        });
        nameTextField.setPromptText("Введіть ім'я");
        phoneTextField.setPromptText("Введіть телефон");
        emailTextField.setPromptText("Введіть email");

        diagnoseNameText.setPromptText("Введіть діагноз");
        diagnoseNameText.setPrefSize(527, 50);
        addDiagnose.setPrefSize(75, 50);




        hBox1.getChildren().addAll(backButton, deleteDoctor);
        hBox1.setPadding(new Insets(0, 5, 5, 5));
        hBox2.setPadding(new Insets(7, 5, 1, 5));
        hBox2.getChildren().addAll(nameTextField, phoneTextField, emailTextField, addDoctor);
        vBox.getChildren().addAll(doctorTableView, hBox2);
        vBox1.getChildren().addAll(vBox, hBox1);

        hBox3.getChildren().addAll(diagnoseNameText, addDiagnose);
        hBox4.getChildren().addAll(backButton1, deleteDiagnose);
        hBox3.setPadding(new Insets(5));
        hBox4.setPadding(new Insets(0, 5,8, 5));
        vBox2.getChildren().addAll(diagnoseTableView, hBox3, hBox4);

        createPieChart();

        BackgroundImage background = new BackgroundImage(new Image( Settings.class.getResourceAsStream("/teeth.png")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        layout.setBackground(new Background(background));
        Label about = new Label("Version: 1.0.1\nCopyright © 2017 : All rights reserved\nCreated by Nikita Mykhailov\nSupport: nikmikhailov13@gmail.com");
        about.setFont(Font.font("Bookman Old Style", 13));
        layout.setPadding(new Insets(20));
        layout.setBottom(about);

        //treatmentsTab
        HBox hBox5 = new HBox(5);
        HBox hBox6 = new HBox(10);

        treatmentNameText.setPromptText("Введіть лікування");
        treatmentNameText.setPrefSize(527, 50);
        addTreatment.setPrefSize(75, 50);
        addTreatment.setTextFill(Color.GREEN);

        hBox5.getChildren().addAll(treatmentNameText, addTreatment);
        hBox6.getChildren().addAll(backButton2, deleteTreatment);
        hBox5.setPadding(new Insets(5));
        hBox6.setPadding(new Insets(0, 5,8, 5));
        vBox3.getChildren().addAll(treatmentsTableView, hBox5, hBox6);


        doctorTab.setContent(vBox1);
        diagnoseTab.setContent(vBox2);
        chartTab.setContent(flowPane);
        infoTab.setContent(layout);
        treatmentTab.setContent(vBox3);


        tabPane.getTabs().addAll(doctorTab, diagnoseTab, treatmentTab, chartTab, infoTab);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void buttonSetOnAction() {

        backButton.setOnAction(e -> toMainMenue());
        backButton1.setOnAction(e -> toMainMenue());
        backButton2.setOnAction(e -> toMainMenue());

        deleteDoctor.setOnAction(e -> doctorDelete());
        deleteDiagnose.setOnAction(e -> deleteDiagnose());
        deleteTreatment.setOnAction(e -> deleteTreatment());

        addDoctor.setOnAction(e -> addDoctor());
        addDoctor.setTextFill(Color.GREEN);
        addTreatment.setOnAction(e-> addTreatment());

        addDiagnose.setOnAction(e -> addDiagnose() );
        addDiagnose.setTextFill(Color.GREEN);


    }

    private void initDoctorTableView() {

        TableColumn<Doctor, String> name = new TableColumn<>("Ім'я");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
//        name.setCellFactory(TextFieldTableCell.forTableColumn());
//        name.setOnEditCommit(e -> {
//            Doctor doctor = e.getTableView().getSelectionModel().getSelectedItem();
//            doctor.setName(e.getNewValue());
//            sqlController.updateDoctor(e.getNewValue(), doctor.getPhone(), doctor.getEmail(), doctor.getId());
//        });


        TableColumn<Doctor, String> phone = new TableColumn<>("Телефон");
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phone.setCellFactory(TextFieldTableCell.forTableColumn());
//        phone.setOnEditCommit(e -> {
//            Doctor doctor = e.getTableView().getSelectionModel().getSelectedItem();
//            doctor.setPhone(e.getNewValue());
//            sqlController.updateDoctor(doctor.getName(), e.getNewValue(), doctor.getEmail(), doctor.getId());
//        });


        TableColumn<Doctor, String> email = new TableColumn<>("Email");
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
//        email.setCellFactory(TextFieldTableCell.forTableColumn());
//        email.setOnEditCommit(e -> {
//            Doctor doctor = e.getTableView().getSelectionModel().getSelectedItem();
//            doctor.setEmail(e.getNewValue());
//            sqlController.updateDoctor(doctor.getName(), doctor.getPhone(), e.getNewValue(), doctor.getId());
//        });

        doctorTableView.getColumns().addAll(name, phone, email);

        ObservableList<TableColumn> list = doctorTableView.getColumns();

        for (TableColumn tablecolumn : list) {
            tablecolumn.setPrefWidth(199);
        }

        doctorTableView.setItems(doctors);
 //     doctorTableView.setEditable(true);
        doctorTableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Doctor doctor = (Doctor) doctorTableView.getSelectionModel().getSelectedItem();
                String doctorName = "'" + doctor.getName() + "'";

                ExistingPatient existingPatient = new ExistingPatient("SELECT * FROM client WHERE client.doctor =" + doctorName + "ORDER BY name"  );
                existingPatient.showInterface();

            }
        });

    }

    private void initDiagnoseTableView() {


        TableColumn<Diagnose, String> name = new TableColumn<>("Діагноз");
        name.setCellValueFactory(new PropertyValueFactory<>("diagnoseName"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(e -> {
            Diagnose diagnose = e.getTableView().getSelectionModel().getSelectedItem();
            diagnose.setDiagnoseName(e.getNewValue());
            sqlController.updateDiagnose(e.getNewValue(), diagnose.getId());
        });
        name.setPrefWidth(597);



        diagnoseTableView.getColumns().addAll(name);



        diagnoses = sqlController.getDiagnoses();
        diagnoseTableView.setItems(diagnoses);
        diagnoseTableView.setEditable(true);

    }

    private void initTreatmentTableView(){
        TableColumn<Diagnose, String> name = new TableColumn<>("Лікування");
        name.setCellValueFactory(new PropertyValueFactory<>("treatmentName"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());

        name.setPrefWidth(597);

        treatmentsTableView.getColumns().addAll(name);

        treatments = sqlController.getTreatments();
        treatmentsTableView.setItems(treatments);

    }

    private void createPieChart() {
        flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
            for (int i = 0; i < doctors.size(); i++){
                pieData.add(new PieChart.Data(getSurname(doctors.get(i).getName()), getCountOf(doctors.get(i).getName())));
            }
        PieChart pieChart = new PieChart(pieData);
        pieChart.setTitle("Кількість пацієнтів у лікарів");


        flowPane.getChildren().addAll(pieChart);
    }

    private void addDoctor() {


        if (!nameTextField.getText().equals(""))
        {
            doctors.clear();
            sqlController.addDoctor(nameTextField.getText(), phoneTextField.getText(), emailTextField.getText());
            doctors = sqlController.getDoctors();
            doctorTableView.setItems(doctors);
            doctorTableView.refresh();
            textFields.forEach(textField -> textField.clear());
        }
    }

    private void doctorDelete() {

        Doctor doctor = (Doctor) doctorTableView.getSelectionModel().getSelectedItem();
        sqlController.deleteFromTable(doctor.getId(), DOCTORDELETE);
        doctors.remove(doctor);
        doctorTableView.refresh();

    }

    private void addDiagnose() {

        if (!diagnoseNameText.getText().equals(""))
        {
            diagnoses.clear();
            sqlController.addDiagnose(diagnoseNameText.getText());
            diagnoses = sqlController.getDiagnoses();
            diagnoseTableView.setItems(diagnoses);
            diagnoseTableView.refresh();
            diagnoseNameText.clear();
        }
    }

    private void deleteDiagnose() {

        Diagnose diagnose = (Diagnose) diagnoseTableView.getSelectionModel().getSelectedItem();
        sqlController.deleteFromTable(diagnose.getId(), DIAGNOSEDELETE);
        diagnoses.remove(diagnose);
        diagnoseTableView.refresh();
    }

    private void addTreatment () {
        if (!treatmentNameText.getText().equals(""))
        {
            treatments.clear();
            sqlController.addTreatment(treatmentNameText.getText());
            treatments = sqlController.getTreatments();
            treatmentsTableView.setItems(treatments);
            treatmentsTableView.refresh();
            treatmentNameText.clear();
        }

    }

    private void deleteTreatment () {
        Treatment treatment = (Treatment)treatmentsTableView.getSelectionModel().getSelectedItem();
        sqlController.deleteFromTable(treatment.getId(), TREATMENT_DELETE);
        treatments.remove(treatment);
        treatmentsTableView.refresh();
    }

    private int getCountOf (String doctor){
        int count = 0;
        ObservableList<String> allDoctors = sqlController.getListOf("SELECT doctor FROM client");
        for (int i = 0; i< allDoctors.size(); i++){
            if (allDoctors.get(i).equals(doctor))
                count++;
        }
        return  count;
    }

    private String getSurname(String doctor){
        return doctor.substring(0, doctor.indexOf(" "));
    }

    private void toMainMenue(){
        MainMenu mainMenu = new MainMenu();
        mainMenu.showInterface();
        primaryStage.close();
    }


}
