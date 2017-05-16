package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sql.SQLController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class MailSender {
    private Stage primaryStage;
    private TableView tableView;
    private VBox vBox;
    private HBox hBox1, hBox2, hBox3;
    private Label  topicLabel, textLabel, messageLabel;
    private TextField topic;
    private TextArea text;
    private Button backButton, sendButton;
    private BorderPane layout;
    private Scene scene;
    final int height = 425;
    final int width = 440;

    final String username = "dr.losovoi@gmail.com";
    final String password = "dr.losovoijava";


    public MailSender (Stage primaryStage) {
        this.primaryStage = primaryStage;
        layout = new BorderPane();
        vBox = new VBox(10);
        hBox1 = new HBox(10);
        topicLabel = new Label("Тема повідомлення");
        textLabel = new Label("Текст повідомлення");
        messageLabel = new Label("");
        topic = new TextField();
        text = new TextArea();
             text.setPrefColumnCount(10);
             text.setPrefHeight(height-60);
        backButton = new Button("Назад");
        sendButton = new Button("Відправити");
        sendButton.setTextFill(Color.GREEN);
    }

    public void showInterface() {
        primaryStage.setTitle("Stomatology : Розсилка листів");
        primaryStage.getIcons().add( new Image( MainMenu.class.getResourceAsStream("/teeth.png")));

        buttonSetOnAction();

        messageLabel.setPrefWidth(253);

        hBox1.getChildren().addAll(backButton,messageLabel, sendButton);
        vBox.getChildren().addAll(topicLabel, topic, textLabel, text, hBox1);

        layout.setPadding(new Insets(15, 20, 15, 20));
        layout.setCenter(vBox);
        scene = new Scene(layout, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sendMessage(String text, String topic) {

          SQLController SQLController = new SQLController();
          String recipients = SQLController.getClientsEmails();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(recipients));

            message.setSubject(topic);
            message.setText(text);

            Transport.send(message);

            messageLabel.setText("       Повідомлення успішно відправлено");
            messageLabel.setTextFill(Color.GREEN);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private void buttonSetOnAction() {
        backButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.showInterface();
            primaryStage.close();
        });

        sendButton.setOnAction(event -> sendMessage(text.getText(), topic.getText()));

    }


}
