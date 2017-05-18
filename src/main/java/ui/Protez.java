package ui;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Protez {
    private Date date;
    private SimpleStringProperty protez;
    private int id_client;
    private int id;

    public Protez (Date date, String protez, int id_client, int id) {
        this.date = date;
        this.protez = new SimpleStringProperty(protez);
        this.id_client = id_client;
        this.id = id;
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

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
