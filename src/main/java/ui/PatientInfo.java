package ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import sql.SQLController;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class PatientInfo {

    private Stage stage;
    private Scene scene;
    private BorderPane layout, teethPane;
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double width = primaryScreenBounds.getWidth();
    private final double height = primaryScreenBounds.getHeight();
    private final int spacing = 15;
    private double prefWidth;
    private TextField nameTF, homeTF, phoneTF, emailTF, prikusTF, colorTF;
    private DatePicker bDate, visitDate;
    private ComboBox<String> doctorComboBox, sexComboBox, diagnoseComboBox;
    private TextArea ilnessesText, gigieneText, scargyText, currentIlnessText, investigationDataText, xRayText, label14Text, label15Text ;
    private Label nameLabel,sexLabel, bDateLabel, homeLabel, phoneLabel,  emailLabel, diagnoseLabel, ilnessesLabel, currentIlness, investigationData,  teethLabel, prikusLabel,
            gigieneLabel, xRay, colorLabel, doctorLabel, scargyLabel, messageLabel, messageLabel1, messageLabel2, label14, label15;
    private HBox hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7, hBox8, hBox9, hBox10, hBox11, hBox12, hBox13, hBox14, hBox15, hBox16, hBox17, hBox18, upperTeethHBox, lowTeethHBox;
    private VBox vBox1, vBox2, teethVBox;
    private Separator separator, separator1, separator2, separator3;
    private ScrollPane scrollPane, historyScrollPane;
    private Button saveButton,saveButton1,saveButton2, addHistory, choosePhoto, addPhoto, addProtez;
    private TabPane tabPane;
    private Tab teethTab, historyTab, protezTab;
    private ObservableList<String> photosList = FXCollections.observableArrayList();
    private ObservableList<String> teeth = FXCollections.observableArrayList();
    private TableView historyTableView, photoTableView, protezTableView;
    private ObservableList<History> history = FXCollections.observableArrayList();
    private ObservableList<Photo> photos = FXCollections.observableArrayList();
    private ObservableList<Protez> protezes = FXCollections.observableArrayList();
    private FileChooser fileChooser;
    private File photoFile;
    private SQLController sqlController = new SQLController();
    private ToothModel t11, t12, t13, t14, t15, t16, t17, t18, t21, t22, t23, t24, t25, t26, t27, t28;
    private ToothModel1 t31, t32, t33, t34, t35, t36, t37, t38, t41, t42, t43, t44, t45, t46, t47, t48;
    private List<ToothModel> toothModelList1;
    private List<ToothModel1> toothModelList2;

    public PatientInfo() {
        stage = new Stage();
        layout = new BorderPane();
        teethPane = new BorderPane();
        historyScrollPane = new ScrollPane();
        scene = new Scene(layout, width, height);
        nameTF = new TextField();
        homeTF = new TextField();
        phoneTF = new TextField();
        emailTF = new TextField();
        prikusTF = new TextField();
        colorTF = new TextField();

        bDate = new DatePicker();
        visitDate = new DatePicker();

        doctorComboBox = new ComboBox<>();
        sexComboBox = new ComboBox<>();
        diagnoseComboBox = new ComboBox<>();


        ilnessesText = new TextArea();
        gigieneText = new TextArea();
        scargyText = new TextArea();
        currentIlnessText = new TextArea();
        investigationDataText = new TextArea();
        xRayText = new TextArea();
        label14Text = new TextArea();
        label15Text = new TextArea();

        nameLabel = new Label("ПІБ");
        sexLabel = new Label("Стать");
        bDateLabel = new Label("Дата народження");
        homeLabel = new Label("Адреса");
        diagnoseLabel = new Label("Діагноз");
        phoneLabel = new Label("Телефон");
        emailLabel = new Label("Email");
        ilnessesLabel = new Label("Перенесені та супутні\nзахворювання");
        currentIlness = new Label("Розвиток теперішенього\nзахворювання");
        teethLabel = new Label("Схема зубів");
        investigationData = new Label("Дані об'єктивного\nдослідження, зовнішній\nогляд, стан зубів");
        prikusLabel = new Label("Прикус");
        gigieneLabel = new Label("Стан гігєни порожнини\nрота, стан слизової\nоболонки рота, ясен");
        xRay  = new Label("Дані рентгенівських\nобстежень лабораторних\nдосліджень");
        colorLabel = new Label("Колір за шкалою\nВіта");
        doctorLabel = new Label("Лікар");
        scargyLabel = new Label("Скарги");
        label14 = new Label("Дата навчання навичкам\nпорожнини гігієни\nрота");
        label15 = new Label("Дата контролю гігієни\nпорожнини рота");


        hBox1 = new HBox(spacing);
        hBox2 = new HBox(spacing);
        hBox3 = new HBox(spacing);
        hBox4 = new HBox(spacing);
        hBox5 = new HBox(spacing);
        hBox6 = new HBox(spacing);
        hBox7 = new HBox(spacing);
        hBox8 = new HBox(spacing);
        hBox9 = new HBox(spacing);
        hBox10 = new HBox(spacing);
        hBox11 = new HBox(spacing);
        hBox12 = new HBox(spacing);
        hBox13 = new HBox(spacing);
        hBox14 = new HBox(spacing);
        hBox15 = new HBox(spacing);
        hBox16 = new HBox(spacing);
        hBox17 = new HBox(spacing);
        hBox18 = new HBox(spacing);

        vBox1 = new VBox(spacing);
        scrollPane = new ScrollPane();

        saveButton = new Button("Зберегти зміни");
        saveButton.setTextFill(Color.GREEN);

        separator = new Separator(Orientation.HORIZONTAL);
        prefWidth = width / 4 - 60 ;
        List<Label> labelList = Arrays.asList(nameLabel, sexLabel, diagnoseLabel, currentIlness, investigationData, xRay, label14, label15, bDateLabel, homeLabel, phoneLabel, emailLabel, ilnessesLabel, scargyLabel, teethLabel, prikusLabel, gigieneLabel, colorLabel, doctorLabel);
        labelList.forEach(label -> label.setPrefWidth(prefWidth / 1.8));

        List<TextField> textFieldList = Arrays.asList(nameTF, homeTF, phoneTF, emailTF, prikusTF, colorTF);
        textFieldList.forEach(textField -> textField.setPrefWidth(prefWidth));
        ilnessesText.setPrefWidth(prefWidth);
        ilnessesText.setPrefRowCount(3);
        gigieneText.setPrefWidth(prefWidth);
        gigieneText.setPrefRowCount(3);
        scargyText.setPrefWidth(prefWidth);
        scargyText.setPrefRowCount(3);
        currentIlnessText.setPrefWidth(prefWidth);
        currentIlnessText.setPrefRowCount(3);
        investigationDataText.setPrefWidth(prefWidth);
        investigationDataText.setPrefRowCount(3);

        xRayText.setPrefWidth(prefWidth);
        xRayText.setPrefRowCount(3);

        label14Text.setPrefWidth(prefWidth);
        label14Text.setPrefRowCount(3);

        label15Text.setPrefWidth(prefWidth);
        label15Text.setPrefRowCount(3);


        bDate.setPrefWidth(prefWidth);
        bDate.setEditable(true);

        scrollPane.setPadding(new Insets(15, 15, 15, 15));
        scrollPane.setPrefWidth(width / 2 - 150);
        layout.setPadding(new Insets(10));


        tabPane = new TabPane();
        historyTab = new Tab();
        teethTab = new Tab();
        vBox2 = new VBox();

        t11 = new ToothModel("11");
        t12 = new ToothModel("12");
        t13 = new ToothModel("13");
        t14 = new ToothModel("14");
        t15 = new ToothModel("15");
        t16 = new ToothModel("16");
        t17 = new ToothModel("17");
        t18 = new ToothModel("18");

        t21 = new ToothModel("21");
        t22 = new ToothModel("22");
        t23 = new ToothModel("23");
        t24 = new ToothModel("24");
        t25 = new ToothModel("25");
        t26 = new ToothModel("26");
        t27 = new ToothModel("27");
        t28 = new ToothModel("28");

        t31 = new ToothModel1("31");
        t32 = new ToothModel1("32");
        t33 = new ToothModel1("33");
        t34 = new ToothModel1("34");
        t35 = new ToothModel1("35");
        t36 = new ToothModel1("36");
        t37 = new ToothModel1("37");
        t38 = new ToothModel1("38");

        t41 = new ToothModel1("41");
        t42 = new ToothModel1("42");
        t43 = new ToothModel1("43");
        t44 = new ToothModel1("44");
        t45 = new ToothModel1("45");
        t46 = new ToothModel1("46");
        t47 = new ToothModel1("47");
        t48 = new ToothModel1("48");



        separator1 = new Separator(Orientation.VERTICAL);
        separator2 = new Separator(Orientation.VERTICAL);
        separator3 = new Separator();


        upperTeethHBox = new HBox(5);
        lowTeethHBox = new HBox(5);
        teethVBox = new VBox(10);

        messageLabel = new Label("");
        messageLabel1 = new Label("");
        addPhoto = new Button("Додати фото");
        choosePhoto = new Button("Зберегти фото");
        historyTableView = new TableView();
        photoTableView = new TableView();
        saveButton1 = new Button("Зберегти зміни");
        addHistory = new Button("Додати запис");

        protezTab = new Tab();
        saveButton2 = new Button("Зберегти зміни");
        addProtez = new Button("Додати запис");
        messageLabel2 = new Label("");
        protezTableView = new TableView();

        toothModelList1 = Arrays.asList(t11, t12, t13, t14, t15, t16, t17, t18, t21, t22, t23, t24, t25, t26, t27, t28);
        toothModelList2= Arrays.asList(t31, t32, t33, t34, t35, t36, t37, t38, t41, t42, t43, t44, t45, t46, t47, t48);
    }

    public void initialize() {
        stage.setTitle("Stomatology : Інформація про пацієнта");
        stage.getIcons().add(new Image(MainMenu.class.getResourceAsStream("/teeth.png")));

        layout.setLeft(scrollPane);
        layout.setRight(tabPane);

        bDate.setPromptText("день.місяць.рік");
        createPhotoTableView();
        setComboBox(doctorComboBox, sqlController.getListOf("SELECT name FROM doctor"));
        ObservableList<String> sex  = FXCollections.observableArrayList("чоловіча", "жіноча");
        setComboBox(sexComboBox, sex);
        setComboBox(diagnoseComboBox, sqlController.getListOf("SELECT * FROM diagnoses") );
        addItemsToBoxes();
        setTabPane();


        scrollPane.requestFocus();
        stage.setScene(scene);
    }

    public void fillGaps(Client client) {
        nameTF.setText(client.getName());
        bDate.setValue(client.getBdate().toLocalDate());
        homeTF.setText(client.getHome());
        phoneTF.setText(client.getPhone());
        emailTF.setText(client.getEmail());
        diagnoseComboBox.setValue(client.getDiagnose());
        ilnessesText.setText(client.getIlnesses());
        scargyText.setText(client.getScargy());
        prikusTF.setText(client.getPrikus());
        gigieneText.setText(client.getGigiene());
        colorTF.setText(client.getTeeth_color());
        doctorComboBox.setValue(client.getDoctor());
        sexComboBox.setValue(client.getSex());
        currentIlnessText.setText(client.getExsistingIlness());
        investigationDataText.setText(client.getInvestigationData());
        xRayText.setText(client.getxRay());
        label14Text.setText(client.getDateOfTeaching());
        label15Text.setText(client.getDateOfControl());

        initTeeth(client);
        initPhotoTable(client);
        initHistoryTable(client);
        initProtezTable(client);
    }

    public void show() {
        stage.show();
    }

    public void setButtonsSave(ExistingPatient existingPatient) {
        saveButton.setOnAction(event -> {
            saveData();
            existingPatient.refresh();

        });
        saveButton1.setOnAction(event -> {
            saveData();
            existingPatient.refresh();

        });
        saveButton2.setOnAction(event -> {
            saveData();
            existingPatient.refresh();

        });
        addPhoto.setOnAction(e -> messageLabel.setText("Фото можна додавати тільки до існуючих пацієнтів!"));

        addHistory.setOnAction(event ->
            messageLabel1.setText("Діагнози можна додавати до існуючих пацієнтів!"));

        addProtez.setOnAction(e -> messageLabel2.setText("Протезування можна додавати тільки до існуючих пацієнтів!"));
    }

    public void setButtonsUpdate(Client client, ExistingPatient existingPatient) {
        saveButton.setOnAction(event -> {
            updateData(client);
            existingPatient.refresh();
        });
        saveButton1.setOnAction(event -> {
            updateData(client);
            existingPatient.refresh();
        });

        addPhoto.setOnAction(e -> {
            messageLabel.setText("");
            photoFile = chooseFile();
            TextWindow comment = new TextWindow("Комментар до фото", 450, 250);
            comment.showWindow();
            try {
                sqlController.addPhoto(photoFile, comment.getText(), client.getId());
                messageLabel.setText("Фото успішно додано!");
                messageLabel.setTextFill(Color.GREEN);
                initPhotoTable(client);
            } catch (SQLException e1) {
                messageLabel.setText("Помилка при додаванні до бази");
                messageLabel.setTextFill(Color.RED);
            } catch (FileNotFoundException e1) {
                messageLabel.setText("Помилка з файлом");
                messageLabel.setTextFill(Color.RED);
            }
        });

            addHistory.setOnAction(event -> {
                HistoryInsert historyInsert = new HistoryInsert(client, this);
                historyInsert.showInterface();
            });

            addProtez.setOnAction(event -> {
                ProtezInsert protezInsert = new ProtezInsert(client, this);
                protezInsert.showInterface();
            });



    }

    private void pauseAndClose () {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> stage.close());
        pause.play();
    }

    private void addItemsToBoxes() {
        hBox1.getChildren().addAll(nameLabel, nameTF);
        hBox12.getChildren().addAll(sexLabel, sexComboBox);
        hBox2.getChildren().addAll(bDateLabel, bDate);
        hBox3.getChildren().addAll(homeLabel, homeTF);
        hBox4.getChildren().addAll(phoneLabel, phoneTF);
        hBox13.getChildren().addAll(diagnoseLabel, diagnoseComboBox);
        hBox5.getChildren().addAll(emailLabel, emailTF);
        hBox6.getChildren().addAll(ilnessesLabel, ilnessesText);
        hBox14.getChildren().addAll(currentIlness, currentIlnessText);
        hBox11.getChildren().addAll(scargyLabel, scargyText);
        hBox15.getChildren().addAll(investigationData, investigationDataText);
        hBox7.getChildren().addAll(prikusLabel, prikusTF);
        hBox8.getChildren().addAll(gigieneLabel, gigieneText);
        hBox16.getChildren().addAll(xRay, xRayText);
        hBox9.getChildren().addAll(colorLabel, colorTF);
        hBox10.getChildren().addAll(doctorLabel, doctorComboBox);
        hBox17.getChildren().addAll(label14, label14Text);
        hBox18.getChildren().addAll(label15, label15Text);
        vBox1.getChildren().addAll(hBox1, hBox12, hBox2, hBox3, hBox4, hBox5, hBox10, hBox13,  hBox6, hBox14, hBox11, hBox15, separator, hBox7, hBox8, hBox16, hBox9, hBox17, hBox18);
        scrollPane.setContent(vBox1);
    }

    private void setTabPane() {
        setTeethTab();
        setHistoryTab();
        setProtezTab();
        historyTab.setText("                        Щоденник лікаря                           ");

        tabPane.getTabs().addAll(teethTab,historyTab, protezTab );
        tabPane.setPrefWidth(width / 2 + (width / 2) - (prefWidth / 2.5 + prefWidth));
    }

    private void setTeethTab() {

        upperTeethHBox.getChildren().addAll(t18, t17, t16, t15, t14, t13, t12, t11, separator1, t21, t22, t23, t24, t25, t26, t27, t28);
        lowTeethHBox.getChildren().addAll(t48, t47, t46, t45, t44, t43, t42, t41, separator2, t31, t32, t33, t34, t35, t36, t37, t38);
        upperTeethHBox.setPrefWidth(width/2.01);
        lowTeethHBox.setPrefWidth(width/2.01);
        teethVBox.setPadding(new Insets(20, 20, 0, 20));
        teethVBox.setPrefWidth(width/2.01);
        photosList.addAll("Фото-знімки пацієнта: ");


        photoTableView.setPrefHeight(height-400);
        photoTableView.setTableMenuButtonVisible(false);
        HBox photoHbox = new HBox();
        photoHbox.getChildren().add(photoTableView);


        HBox separatorHbox = new HBox();
        separatorHbox.getChildren().add(separator3);
        separator3.setPrefWidth(width/2.01);
        separatorHbox.setPrefWidth(width/2.01);

        Label signsLabel = new Label("Условні позначення виявлених патологічних змін:\nA - Адентія, Pl - Пломба, Cd - Штучна коронка, " +
                "C - Очаг демініралізіації,\nPt - Очаг періапікального розрідження кістної тканини, K - Кіста, Im - Імплант");
        signsLabel.setFont(Font.font(14));
        teethVBox.getChildren().addAll(upperTeethHBox, separatorHbox, lowTeethHBox, signsLabel, photoHbox);



        teethPane.setPadding(new Insets(0, 20, 0, 0));
        teethPane.setTop(teethVBox);

        addPhoto.setPrefHeight(40);
        saveButton.setPrefHeight(40);

        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(0, 20, 0, 20));
        hBox.getChildren().addAll(addPhoto, saveButton, messageLabel);

        teethPane.setBottom(hBox);
        teethTab.setText("                              Зуби                              ");
        teethTab.setContent(teethPane);

    }

    private void setHistoryTab() {
        createHistoryTable();
        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox(10);
        hBox.setPrefHeight(50);

        saveButton1.setPrefHeight(40);
        saveButton1.setTextFill(Color.GREEN);
        addHistory.setPrefHeight(40);

        hBox.setPadding(new Insets(10, 20, 0, 20));

        hBox.getChildren().addAll(addHistory, saveButton1, messageLabel1);
        historyScrollPane.setContent(historyTableView);
        historyScrollPane.setFitToHeight(true);
        historyScrollPane.setMinHeight(height - 150);
        borderPane.setTop(historyScrollPane);
        borderPane.setBottom(hBox);
        historyTab.setContent(borderPane);
    }

    private void setProtezTab(){
        protezTab.setText("                Протезування                ");
        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox(10);
        hBox.setPrefHeight(50);
        hBox.setPadding(new Insets(10, 20, 0, 20));
        hBox.getChildren().addAll(addProtez, saveButton2, messageLabel2);
        saveButton2.setPrefHeight(40);
        saveButton2.setTextFill(Color.GREEN);
        addProtez.setPrefHeight(40);

        createPtotezTable();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinHeight(height-150);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(protezTableView);
        borderPane.setTop(scrollPane);
        borderPane.setBottom(hBox);
        protezTab.setContent(borderPane);

    }

    private void initTeeth(Client client) {
        teeth.clear();
        teeth = sqlController.getTeeth(client.getId());

        for (int i = 0; i < 16; i++) {
            toothModelList1.get(i).setText(teeth.get(i));
        }
        for (int i = 0; i < 16; i++) {
            toothModelList2.get(i).setText(teeth.get(i+16));
        }
    }

    private void createHistoryTable() {

        TableColumn<History, String> date = new TableColumn<>("Дата");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<History, String> diagnose = new TableColumn<>("Діагноз");
        diagnose.setCellValueFactory(new PropertyValueFactory<>("diagnose"));

        TableColumn<History, String> treatment = new TableColumn<>("Лікування");
        treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));

        TableColumn<History, String> note = new TableColumn<>("Анамнез, статус, рекомендації");
        note.setCellValueFactory(new PropertyValueFactory<>("notes"));

//        date.setPrefWidth(width / 14);
          diagnose.setPrefWidth(width / 8);
          treatment.setPrefWidth(width / 8);
          note.setPrefWidth(width / 3);

        historyTableView.getColumns().addAll(date, diagnose, treatment, note);




    }

    public void initHistoryTable(Client client){
        history.clear();
        try {
            history = sqlController.getClientHistory(client.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        historyTableView.setItems(history);


        historyTableView.setOnKeyPressed( e-> {
            if (e.getCode() == KeyCode.BACK_SPACE){
                History history = (History) historyTableView.getSelectionModel().getSelectedItem();
                boolean confirm = Dialog.showConfirmationDialog("Видалення запису", "Ви впевнені, що хочете видалити запис?",
                        "Будь ласка, підтвердіть свої діі");
                if (confirm) {
                    sqlController.deleteFromTable(history.getId(), "DELETE FROM history WHERE id = ?");
                    initHistoryTable(client);
                }

                }
        });
    }

    private void initPhotoTable(Client client){
        photos.clear();
        photos = sqlController.getPhotos(client);
        photoTableView.setItems(photos);
        photoTableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                openPhoto();
        }});
        photoTableView.setOnKeyPressed( e-> {
            if (e.getCode() == KeyCode.BACK_SPACE){
              Photo photo = (Photo)photoTableView.getSelectionModel().getSelectedItem();
                boolean confirm = Dialog.showConfirmationDialog("Видалення фото", "Ви впевнені, що хочете видалити\n"+ photo.getComment() +"?",
                        "Будь ласка, підтвердіть свої діі");
                if (confirm) {
                    sqlController.deleteFromTable(photo.getId(), "DELETE FROM photo WHERE id = ?");
                    initPhotoTable(client);
                }
            }
        });
    }

    public void initProtezTable (Client client) {
        protezes.clear();
        protezes = sqlController.getClientProtez(client.getId());
        protezTableView.setItems(protezes);
        System.out.println(protezes.toString());

        protezTableView.setOnKeyPressed( e-> {
            if (e.getCode() == KeyCode.BACK_SPACE){
                Protez protez = (Protez) protezTableView.getSelectionModel().getSelectedItem();
                boolean confirm = Dialog.showConfirmationDialog("Видалення запису", "Ви впевнені, що хочете видалити?",
                        "Будь ласка, підтвердіть свої діі");
                if (confirm) {
                    sqlController.deleteFromTable(protez.getId(), "DELETE FROM protez WHERE id = ?");
                    initProtezTable(client);
                }
            }
        });


    }

    private void createPhotoTableView(){
        TableColumn<Photo, String> comment = new TableColumn<>("Фото пацієнта");
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        photoTableView.getColumns().add(comment);
        comment.setPrefWidth(width / 2.01);

    }

    private void createPtotezTable () {
        TableColumn<Protez, String> date = new TableColumn<>("Дата");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Protez, String> protez = new TableColumn<>("Протезування");
        protez.setCellValueFactory(new PropertyValueFactory<>("protez"));

        date.setPrefWidth(width/14);
        protez.setPrefWidth(width/1.8);
        protezTableView.getColumns().addAll(date, protez);
    }

    private void saveData() {
        if ( (nameTF.getText().equals(""))) {
          notEnoughDataForSaving();
        } else {

            java.sql.Date date;
            if (bDate.getValue() == null) {
                date = java.sql.Date.valueOf(LocalDate.now());
            } else date = java.sql.Date.valueOf(bDate.getValue());

            gatheringTeeth();
            try {
                sqlController.addClient(teeth, nameTF.getText(), date, homeTF.getText(), phoneTF.getText(), emailTF.getText(),
                    ilnessesText.getText(), scargyText.getText(), prikusTF.getText(), gigieneText.getText(), colorTF.getText(), doctorComboBox.getValue(),
                        sexComboBox.getValue(), currentIlnessText.getText(), investigationDataText.getText(), xRayText.getText(),
                        label14Text.getText(), label15Text.getText(), diagnoseComboBox.getValue());
                successfullySave();
            } catch (SQLException e) {
                e.printStackTrace();
                unsuccessfullySave();
            }
            pauseAndClose();
        }
    }

    private void updateData(Client client) {
        if ((bDate.getValue() == null) || (nameTF.getText().equals(""))) {
            notEnoughDataForSaving();
        } else {
            java.sql.Date date = java.sql.Date.valueOf(bDate.getValue());
            gatheringTeeth();
            try {
                sqlController.updateClient(teeth, client.getId(), nameTF.getText(), date, homeTF.getText(), phoneTF.getText(), emailTF.getText(),
                        ilnessesText.getText(), scargyText.getText(), prikusTF.getText(), gigieneText.getText(), colorTF.getText(), doctorComboBox.getValue(), sexComboBox.getValue(), currentIlnessText.getText(), investigationDataText.getText(), xRayText.getText(),
                        label14Text.getText(), label15Text.getText(), diagnoseComboBox.getValue());

                successfullySave();
            } catch (SQLException e) {
                e.printStackTrace();
                unsuccessfullySave();
            }
        }

    }

    private void setComboBox(ComboBox<String> cb, ObservableList items) {
        cb.setEditable(true);
        cb.setPrefWidth(prefWidth);
        // Create a list with some dummy values.


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
    }

    private File chooseFile() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Stomatology");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        String path = fileChooser.showOpenDialog(stage).getAbsolutePath();
        photoFile = new File(path);
        return photoFile;
    }

    private void openPhoto () {
        Photo photo = (Photo)photoTableView.getSelectionModel().getSelectedItem();
        ImageViewer imageViewer = new ImageViewer(photo.getPhoto());
        imageViewer.showInterface();
    }

    private void gatheringTeeth() {
        teeth.clear();
        for (int i = 0; i < 16; i++) {
            teeth.add(toothModelList1.get(i).getText());
        }
        for (int i = 0; i < 16; i++) {
            teeth.add(toothModelList2.get(i).getText());
        }
    }

    private void successfullySave() {
        messageLabel.setText("Дані успішно збережено!");
        messageLabel1.setText("Дані успішно збережено!");
        messageLabel2.setText("Дані успішно збережено!");
        messageLabel.setTextFill(Color.GREEN);
        messageLabel1.setTextFill(Color.GREEN);
        messageLabel2.setTextFill(Color.GREEN);
    }

    private void unsuccessfullySave() {
        messageLabel.setText("Помилка");
        messageLabel.setTextFill(Color.RED);
        messageLabel1.setText("Помилка");
        messageLabel1.setTextFill(Color.RED);
        messageLabel2.setText("Помилка");
        messageLabel2.setTextFill(Color.RED);
    }

    private void notEnoughDataForSaving () {
        messageLabel.setText("Введіть усі необхідні дані!");
        messageLabel1.setText("Введіть усі необхідні дані!");
        messageLabel2.setText("Введіть усі необхідні дані!");
        messageLabel.setTextFill(Color.RED);
        messageLabel1.setTextFill(Color.RED);
        messageLabel2.setTextFill(Color.RED);
    }
}
