package controllers.tabs;

import controllers.tabs.parameterInterfaces.TextParameterDecorator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Rosyanka implements Initializable {
    TextParameterDecorator serialNumberInterface;

    private Devises devise;
    private int serialNumber;

    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private Label serialNumberLabel;
    @FXML
    private TextField serialNumberField;
    @FXML
    private Button button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(Devises o: Devises.values())
            typeComboBox.getItems().add(o.getName());
        serialNumberInterface = new TextParameterDecorator(serialNumberLabel, serialNumberField, "5");
        serialNumberInterface.hide();
    }

    public void typeComboBoxChange(){
        for(Devises o: Devises.values())
            if(o.getName().equals(typeComboBox.getValue()))
                devise = o;

        if(devise.id > 0) {
            serialNumberInterface.block();
            serialNumberInterface.unblock();
            buttonUnblock();
        } else {
            serialNumberInterface.hide();
            if(devise == Devises.VOID)
                buttonBlock();
            else
                buttonUnblock();
        }
    }

    public void serialNumberFieldChange(){
        serialNumber = Integer.parseInt(serialNumberField.getText());

        if (serialNumber > 0 && serialNumber <= 16383) {
            buttonUnblock();
        } else {
            buttonBlock();
        }
    }

    public void buttonPush(){

    }

    private void buttonBlock(){
        button.setDisable(true);
    }
    private void buttonUnblock(){
        button.setDisable(false);
    }

    private enum Devises{
        VOID(" ", 0, 0),
        EMP("Сфера-РП", 5, 2),
        DEMP("Сенсор-РП", 6, 2),
        SVAZ("Мелодия-РВ", 7, 4),
        UKVZ("Сенсор-РВ", 8, 4),
        UKAZ("Сенсор-РА",10,4),
        SAZ("Сфера-Р",11,2),
        DSAZ("Сенсор-Р",12,2),
        BSS("Сигнал-Р",15,20),
        DSS("Сенсор-РБ",16,20),
        MRT("Милодия-РТ",17,1),
        IT("Инф-Терминал",-1,0),
        WDT("СторожТаймер",-1,0);

        private String name;
        private int id;
        private int channels;
        private String file;

        Devises(String name, int id, int channels) {
            this.name = name;
            this.id = id;
            this.channels = channels;
        }

        public String getName() {
            return name;
        }

        public Integer getId() {
            return id;
        }

        public Integer getChannels() {
            return channels;
        }

        public String getFile() { return file; }
    }
}

