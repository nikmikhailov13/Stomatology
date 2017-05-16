package ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Doctor {

    private SimpleStringProperty name;
    private SimpleStringProperty phone;
    private SimpleStringProperty email;
    private SimpleIntegerProperty id;


    public Doctor (String name, String phone, String email, int id) {
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.id = new SimpleIntegerProperty(id);

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

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

}
