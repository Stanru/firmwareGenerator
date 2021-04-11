package controllers.tabs.parameterInterfaces;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TextParameterDecorator {
    private Label label;
    private TextField textField;
    private String defaultText;

    public TextParameterDecorator(Label label, TextField textField, String defaultText) {
        if(label == null)
            throw new NullPointerException("label can't be null");
        if(textField == null)
            throw new NullPointerException("textField can't be null");
        if(defaultText == null)
            throw new NullPointerException("defaultText can't be null");

        this.label = label;
        this.textField = textField;
        this.defaultText = defaultText;
        textField.setText(defaultText);
    }

    public void block(){
        textField.setDisable(true);
        textField.setText(defaultText);
    }

    public void unblock(){
        textField.setDisable(false);
        visible();
    }

    public void visible(){
        label.setVisible(true);
        textField.setVisible(true);
    }

    public void hide(){
        label.setVisible(false);
        textField.setVisible(false);
        block();
    }

    public Integer getTextConvertToInt(){
        try {
            return Integer.parseInt(textField.getText());
        }catch (Exception e){
            return -1;
        }
    }

    public void setText(String text){
        if(text == null)
            throw new NullPointerException("text can't be null");
        textField.setText(text);
    }
}
