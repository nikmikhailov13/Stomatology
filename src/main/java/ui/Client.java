package ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.sql.Date;
public class Client {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private Date bdate;
    private SimpleStringProperty home;
    private SimpleStringProperty phone;
    private SimpleStringProperty email;
    private SimpleStringProperty ilnesses;
    private SimpleStringProperty scargy;
    private SimpleStringProperty prikus;
    private SimpleStringProperty gigiene;
    private SimpleStringProperty teeth_color;
    private SimpleStringProperty doctor;
    private ObservableList<Tooth> teeth;
    private String sex;
    private String existingIlness;
    private String investigationData;
    private String xRay;
    private String dateOfTeaching;
    private String dateOfControl;
    private String diagnose;


    public Client (String name,  Date bdate, String home, String phone, String email,  String ilnesses,  String scargy,  String prikus, String gigiene,
                    String teeth_color, String doctor, int id, String sex, String existingIlness, String investigationData,String xRay, String dateOfTeaching, String dateOfControl, String diagnose) {
        this.name = new SimpleStringProperty(name);
        this.bdate = bdate;
        this.home = new SimpleStringProperty(home);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.id = new SimpleIntegerProperty(id);
        this.ilnesses = new SimpleStringProperty(ilnesses);
        this.scargy = new SimpleStringProperty(scargy);
        this.prikus = new SimpleStringProperty(prikus);
        this.gigiene = new SimpleStringProperty(gigiene);
        this.teeth_color = new SimpleStringProperty(teeth_color);
        this.doctor = new SimpleStringProperty(doctor);
        this.sex = sex;
        this.existingIlness = existingIlness;
        this.investigationData = investigationData;
        this.xRay = xRay;
        this.dateOfControl = dateOfControl;
        this.dateOfTeaching = dateOfTeaching;
        this.diagnose = diagnose;

    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public String getHome() {
        return home.get();
    }

    public SimpleStringProperty homeProperty() {
        return home;
    }

    public void setHome(String home) {
        this.home.set(home);
    }

    public ObservableList<Tooth> getTeeth() {
        return teeth;
    }

    public void setTeeth(ObservableList<Tooth> teeth) {
        this.teeth = teeth;
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getIlnesses() {
        return ilnesses.get();
    }

    public SimpleStringProperty ilnessesProperty() {
        return ilnesses;
    }

    public void setIlnesses(String ilnesses) {
        this.ilnesses.set(ilnesses);
    }

    public String getScargy() {
        return scargy.get();
    }

    public SimpleStringProperty scargyProperty() {
        return scargy;
    }

    public void setScargy(String scargy) {
        this.scargy.set(scargy);
    }

    public String getPrikus() {
        return prikus.get();
    }

    public SimpleStringProperty prikusProperty() {
        return prikus;
    }

    public void setPrikus(String prikus) {
        this.prikus.set(prikus);
    }

    public String getGigiene() {
        return gigiene.get();
    }

    public SimpleStringProperty gigieneProperty() {
        return gigiene;
    }

    public void setGigiene(String gigiene) {
        this.gigiene.set(gigiene);
    }

    public String getTeeth_color() {
        return teeth_color.get();
    }

    public SimpleStringProperty teeth_colorProperty() {
        return teeth_color;
    }

    public void setTeeth_color(String teeth_color) {
        this.teeth_color.set(teeth_color);
    }

    public String getDoctor() {
        return doctor.get();
    }

    public SimpleStringProperty doctorProperty() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor.set(doctor);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getExsistingIlness() {
        return existingIlness;
    }

    public void setExsistingIlness(String exsistingIlness) {
        this.existingIlness = exsistingIlness;
    }

    public String getInvestigationData() {
        return investigationData;
    }

    public void setInvestigationData(String investigationData) {
        this.investigationData = investigationData;
    }

    public String getxRay() {
        return xRay;
    }

    public void setxRay(String xRay) {
        this.xRay = xRay;
    }

    public String getDateOfTeaching() {
        return dateOfTeaching;
    }

    public void setDateOfTeaching(String dateOfTeaching) {
        this.dateOfTeaching = dateOfTeaching;
    }

    public String getDateOfControl() {
        return dateOfControl;
    }

    public void setDateOfControl(String dateOfControl) {
        this.dateOfControl = dateOfControl;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }
}
