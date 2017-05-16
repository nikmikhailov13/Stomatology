package ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Treatment {
    private SimpleStringProperty treatmentName;
    private SimpleIntegerProperty id;

    public Treatment(String treatmentName,  int id) {
        this.treatmentName = new SimpleStringProperty(treatmentName);
        this.id = new SimpleIntegerProperty(id);

    }

    public String getTreatmentName() {
        return treatmentName.get();
    }

    public SimpleStringProperty treatmentNameProperty() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName.set(treatmentName);
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
