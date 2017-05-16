package ui;


import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class History {

    private Date date;
    private SimpleStringProperty diagnose;
    private SimpleStringProperty notes;
    private int id;


    public History (Date date, String diagnose, String notes, int id) {
        this.date = date;
        this.diagnose = new SimpleStringProperty(diagnose);
        this.notes = new SimpleStringProperty(notes);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDiagnose() {
        return diagnose.get();
    }

    public SimpleStringProperty diagnoseProperty() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose.set(diagnose);
    }

    public String getNotes() {
        return notes.get();
    }

    public SimpleStringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }
}
