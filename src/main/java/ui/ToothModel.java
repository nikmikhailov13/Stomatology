package ui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * Created by nikmi on 04.05.2017.
 */
public class ToothModel extends VBox {
    TextArea textArea;

    public ToothModel (String name) {
        Button button = new Button(name);
        button.setPrefWidth(42);
        textArea = new TextArea();
        textArea.setPrefRowCount(1);
        textArea.setPrefWidth(10);
        this.getChildren().addAll(textArea, button);
    }

    public String getText () {
        return textArea.getText();
    }

    public void setText (String text) {
        textArea.setText(text);
    }


}
