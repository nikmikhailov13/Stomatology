package ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Diagnose {
    private SimpleStringProperty diagnoseName;
    private SimpleIntegerProperty id;

    public Diagnose(String diagnoseName,  int id) {
        this.diagnoseName = new SimpleStringProperty(diagnoseName);
        this.id = new SimpleIntegerProperty(id);

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

    public String getDiagnoseName() {
        return diagnoseName.get();
    }

    public SimpleStringProperty diagnoseNameProperty() {
        return diagnoseName;
    }

    public void setDiagnoseName(String diagnoseName) {
        this.diagnoseName.set(diagnoseName);
    }

}
