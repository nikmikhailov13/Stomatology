package ui;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Protez {
    private Date date;
    private SimpleStringProperty protez;

    public Protez (Date date, String protez) {
        this.date = date;
        this.protez = new SimpleStringProperty(protez);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProtez() {
        return protez.get();
    }

    public SimpleStringProperty protezProperty() {
        return protez;
    }

    public void setProtez(String protez) {
        this.protez.set(protez);
    }
}
