package sql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.apache.commons.io.IOUtils;
import ui.*;

import java.awt.*;
import java.io.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLController {

    private DBWorker dbWorker;
    private final String INSERTION = "INSERT INTO client (t11, t12, t13, t14, t15, t16, t17, t18, t21, t22, t23, t24, t25, t26, t27, t28, t31, t32, t33, t34, t35, t36, t37, t38, t41, t42, t43, t44, t45, t46, t47, t48, name, bdate, phone, email, ilnesses, scargy, prikus, gigiene, teeth_color, home, doctor, sex, currentilness, investigationdata, xray, dateofteaching, dateofcontrol, diagnose)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_CLIENT = "UPDATE client SET t11=?, t12=?, t13=?, t14=?, t15=?, t16=?, t17=?, t18=?," +
            "t21=?, t22=?, t23=?, t24=?, t25=?, t26=?, t27=?, t28=?, t31=?, t32=?, t33=?, t34=?, t35=?, t36=?, t37=?, t38=?," +
            "t41=?, t42=?, t43=?, t44=?, t45=?, t46=?, t47=?, t48=?, " +
            "name=?, bdate=?, phone=?, email=?, ilnesses=?, scargy=?, prikus=?, gigiene=?, teeth_color=?, home =?, doctor =?, " +
            "sex = ?, currentilness = ?, investigationdata = ?, xray = ?, dateofteaching = ?, dateofcontrol = ?, diagnose = ? WHERE id = ?";

    private final String DOCTOR_QUERY = "SELECT * FROM doctor ORDER BY name";
    private final String DOCTOR_INSERTION = "INSERT INTO doctor VALUES (?,?,?)";
    private final String DOCTOR_UPDATE = "UPDATE doctor SET name = ?, phone = ?, email = ? WHERE id = ?";

    private final String DIAGNOSE_INSERTION = "INSERT INTO diagnoses VALUES (?)";
    private final String DIAGNOSE_UPDATE = "UPDATE diagnoses SET name = ? WHERE id = ?";
    private final String DIAGNOSE_QUERY = "SELECT * FROM diagnoses ORDER BY name";

    private final String TREATMENT_INSERTION = "INSERT INTO treatments VALUES (?)";
    private final String TREATMENT_QUERY = "SELECT * FROM treatments ORDER BY treatment";

    private final String EMAIL = "SELECT email FROM client WHERE email LIKE '%@%'";
    private final String TEETH = "SELECT t11, t12, t13, t14, t15, t16, t17, t18, t21, t22, t23, " +
            "t24, t25, t26, t27, t28, t31, t32, t33, t34, t35, t36, t37, t38, t41, t42, t43, t44," +
            " t45, t46, t47, t48 FROM client WHERE id = ?";

    private final String INSERT_PHOTO = "INSERT INTO photo VALUES (?,?,?)";
    private final String PHOTO_QUERY = "SELECT * FROM photo WHERE id_client=?";

    private final String HISTORY_INSERTION = "INSERT INTO history (id_client, diagnose, date, notes,  treatment) VALUES (?, ?, ?, ?, ?)";
    private final String HISTORY_QUERY = "SELECT * FROM history WHERE id_client = ?";

    private final String PROTEZ_INSERTION = "INSERT INTO protez VALUES (?, ?, ?)";
    private final String PROTEZ_QUERY = "SELECT * FROM protez WHERE id_client = ?";

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet;
    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private ObservableList<Doctor> doctors = FXCollections.observableArrayList();
    private ObservableList<Diagnose> diagnoses = FXCollections.observableArrayList();
    private ObservableList<Treatment> treatments = FXCollections.observableArrayList();
    private ObservableList<Photo> photos = FXCollections.observableArrayList();
    private ObservableList<String> teeth = FXCollections.observableArrayList();
    private ObservableList<History> history = FXCollections.observableArrayList();
    private ObservableList<Protez> protezes = FXCollections.observableArrayList();


    public SQLController() {
        dbWorker = new DBWorker();
    }

    //clients
    public void addClient(ObservableList<String> teeth, String name, Date birthdate, String home, String phone, String email, String ilnesses, String scargy, String prikus, String gigiene, String teeth_color, String doctor, String sex, String currentIlness, String investigationData, String xRay, String dateOfTeaching, String dateOfControl, String diagnose) throws SQLException {

        preparedStatement = dbWorker.getConnection().prepareStatement(INSERTION);

        for (int i = 1; i <33; i++) {
            preparedStatement.setString(i, teeth.get(i-1));
        }

        preparedStatement.setString(33, name);
        preparedStatement.setDate(34, birthdate);
        preparedStatement.setString(35, phone);
        preparedStatement.setString(36, email);
        preparedStatement.setString(37, ilnesses);
        preparedStatement.setString(38, scargy);
        preparedStatement.setString(39, prikus);
        preparedStatement.setString(40, gigiene);
        preparedStatement.setString(41, teeth_color);
        preparedStatement.setString(42, home);
        preparedStatement.setString(43, doctor);
        preparedStatement.setString(44, sex);
        preparedStatement.setString(45, currentIlness);
        preparedStatement.setString(46, investigationData);
        preparedStatement.setString(47, xRay);
        preparedStatement.setString(48, dateOfTeaching);
        preparedStatement.setString(49, dateOfControl);
        preparedStatement.setString(50, diagnose);

        preparedStatement.execute();

    }

    public void updateClient(ObservableList<String> teeth, int id, String name, Date birthdate, String home, String phone, String email, String ilnesses, String scargy, String prikus, String gigiene, String teeth_color, String doctor, String sex, String currentIlness, String investigationData, String xRay, String dateOfTeaching, String dateOfControl, String diagnose) throws SQLException {

        preparedStatement = dbWorker.getConnection().prepareStatement(UPDATE_CLIENT);

        for (int i = 1; i <33; i++) {
            preparedStatement.setString(i, teeth.get(i-1));
        }

        preparedStatement.setString(33, name);
        preparedStatement.setDate(34, birthdate);
        preparedStatement.setString(35, phone);
        preparedStatement.setString(36, email);
        preparedStatement.setString(37, ilnesses);
        preparedStatement.setString(38, scargy);
        preparedStatement.setString(39, prikus);
        preparedStatement.setString(40, gigiene);
        preparedStatement.setString(41, teeth_color);
        preparedStatement.setString(42, home);
        preparedStatement.setString(43, doctor);
        preparedStatement.setString(44, sex);
        preparedStatement.setString(45, currentIlness);
        preparedStatement.setString(46, investigationData);
        preparedStatement.setString(47, xRay);
        preparedStatement.setString(48, dateOfTeaching);
        preparedStatement.setString(49, dateOfControl);
        preparedStatement.setString(50, diagnose);
        preparedStatement.setInt(51, id);

        preparedStatement.execute();
    }

    public ObservableList<Client> getClients(String query) {
        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               // int id = resultSet.getInt("id");
                Client client = new Client( resultSet.getString("name"), resultSet.getDate("bdate"), resultSet.getString("home"),
                        resultSet.getString("phone"), resultSet.getString("email"),
                        resultSet.getString("ilnesses"), resultSet.getString("scargy"),
                        resultSet.getString("prikus"), resultSet.getString("gigiene"),
                        resultSet.getString("teeth_color"), resultSet.getString("doctor"), resultSet.getInt("id"),
                        resultSet.getString("sex"), resultSet.getString("currentIlness"),
                        resultSet.getString("investigationdata"), resultSet.getString("xray"),
                        resultSet.getString("dateofteaching"), resultSet.getString("dateofcontrol"), resultSet.getString("diagnose"));
                System.out.println(client);

                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public ObservableList<String> getTeeth (int id){

        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(TEETH);
            preparedStatement.setInt(1, id );
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
            for (int i = 1; i < 33; i++) {
                teeth.addAll(resultSet.getString(i));
            }}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teeth;
    }


    //additional tools
    public void deleteFromTable(int id, String query) {
        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getClientsEmails() {

        String emails = "";

        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(EMAIL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emails += resultSet.getString(1) + ",";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emails;
    }

    public ObservableList<String> getListOf(String query) {
         ObservableList<String> list = FXCollections.observableArrayList();
        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    //doctors
    public ObservableList<Doctor> getDoctors() {

        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(DOCTOR_QUERY);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Doctor doctor = new Doctor(resultSet.getString("name"),
                        resultSet.getString("phone"), resultSet.getString("email"), resultSet.getInt("id"));
                doctors.add(doctor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;

    }

    public void addDoctor(String name, String phone, String email) {

        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(DOCTOR_INSERTION);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDoctor(String name, String phone, String email, int id) {
        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(DOCTOR_UPDATE);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //diagnoses
    public void addDiagnose(String diagnoseName) {

        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(DIAGNOSE_INSERTION);
            preparedStatement.setString(1, diagnoseName);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDiagnose (String diagnoseName,  int id) {
        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(DIAGNOSE_UPDATE);
            preparedStatement.setString(1, diagnoseName);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Diagnose> getDiagnoses () {
        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(DIAGNOSE_QUERY);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Diagnose diagnose = new Diagnose(resultSet.getString("name"), resultSet.getInt("id"));
                diagnoses.add(diagnose);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnoses;

    }

    //treatments
    public void addTreatment(String treatmentName) {

        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(TREATMENT_INSERTION);
            preparedStatement.setString(1, treatmentName);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Treatment> getTreatments () {
        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(TREATMENT_QUERY);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Treatment treatment = new Treatment(resultSet.getString("treatment"), resultSet.getInt("id_treatment"));
                treatments.add(treatment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return treatments;

    }


    //photo
    public void addPhoto(File photo, String comment, int id_client) throws SQLException, FileNotFoundException {


            FileInputStream  fis = new FileInputStream(photo);
            preparedStatement = dbWorker.getConnection().prepareStatement(INSERT_PHOTO);
            preparedStatement.setBinaryStream(1, fis, (int) photo.length());
            preparedStatement.setString(2, comment);
            preparedStatement.setInt(3, id_client);
            preparedStatement.execute();

    }

    public ObservableList<Photo> getPhotos (Client client) {

        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(PHOTO_QUERY);
            preparedStatement.setInt(1, client.getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ByteArrayInputStream input  = new ByteArrayInputStream(resultSet.getBytes("file"));
                Photo photo = new Photo(new Image(input), resultSet.getString("comment"), resultSet.getInt("id_client"), resultSet.getInt("id"));
                photos.add(photo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return photos;
    }



    //history
    public void setClientHistory(int id_client, String diagnose, Date date, String notes, String treatment) throws SQLException {
        preparedStatement = dbWorker.getConnection().prepareStatement(HISTORY_INSERTION);
        preparedStatement.setInt(1, id_client);
        preparedStatement.setString(2, diagnose);
        preparedStatement.setDate(3, date);
        preparedStatement.setString(4, notes);
        preparedStatement.setString(5, treatment);
        preparedStatement.execute();
    }

    public ObservableList<History> getClientHistory (int id_client) throws SQLException {
        preparedStatement = dbWorker.getConnection().prepareStatement(HISTORY_QUERY);
        preparedStatement.setInt(1, id_client);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            History historyItem = new History(resultSet.getDate("date"), resultSet.getString("diagnose"),
                    resultSet.getString("treatment"), resultSet.getString("notes"), resultSet.getInt("id"));
            history.add(historyItem);
        }
        return  history;
    }

    public void setClientProtez(Date date, String protez, int id_client) throws SQLException {

        preparedStatement = dbWorker.getConnection().prepareStatement(PROTEZ_INSERTION);
        preparedStatement.setDate(1, date);
        preparedStatement.setString(2, protez);
        preparedStatement.setInt(3, id_client);
        preparedStatement.execute();
    }

    public ObservableList<Protez> getClientProtez (int id_client)  {
        try {
            preparedStatement = dbWorker.getConnection().prepareStatement(PROTEZ_QUERY);
            preparedStatement.setInt(1, id_client);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Protez protez = new Protez(resultSet.getDate("date"), resultSet.getString("protez"), resultSet.getInt("id_client"), resultSet.getInt("id"));
                protezes.add(protez);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return protezes;
    }

}

