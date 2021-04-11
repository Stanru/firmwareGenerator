package controllers.tabs.parameterInterfaces;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ComboBoxParameterDecorator {
    private Label label;
    private ComboBox<String> comboBox;
    private String defaultText;

    public ComboBoxParameterDecorator(Label label, ComboBox<String> comboBox) {
        if(label == null)
            throw new NullPointerException("label can't be null");
        if(comboBox == null)
            throw new NullPointerException("comboBox can't be null");

        this.label = label;
        this.comboBox = comboBox;
    }

    public void addItems(String items){
        if(defaultText == null) {
            defaultText = items;
            comboBox.setValue(defaultText);
        }
        comboBox.getItems().add(items);
    }

    public void block(){
        comboBox.setDisable(true);
    }

    public void unblock(){
        comboBox.setDisable(false);
        visible();
    }

    public void visible(){
        label.setVisible(true);
        comboBox.setVisible(true);
    }

    public void hide(){
        label.setVisible(false);
        comboBox.setVisible(false);
        block();
    }

    public String getText(){
        return getText();
    }
}
