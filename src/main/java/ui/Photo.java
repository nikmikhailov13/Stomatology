package ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;


public class Photo {
    private Image photo;
    private SimpleStringProperty comment;
    private int id_patient;
    private int id;

    public Photo (Image photo, String comment, int id_patient, int id ) {
        this.photo = photo;
        this.comment = new SimpleStringProperty(comment);
        this.id_patient = id_patient;
        this.id = id;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public String getComment() {
        return comment.get();
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
