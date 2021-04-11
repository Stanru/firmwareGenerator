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

public class Zaslon implements Initializable {
    TextParameterDecorator serialNumberInterface;
    TextParameterDecorator yearOfReleaseInterface;
    TextParameterDecorator numberOfChannelsInterface;
    TextParameterDecorator hourInterface;
    TextParameterDecorator minutesInterface;

    private Devises devise;
    private int serialNumber;
    private int yearOfRelease;
    private int numberOfChannels;
    private int hour;
    private int minutes;

    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private Label serialNumberLabel;
    @FXML
    private TextField serialNumberField;
    @FXML
    private Label yearOfReleaseLabel;
    @FXML
    private TextField yearOfReleaseField;
    @FXML
    private Label numberOfChannelsLabel;
    @FXML
    private TextField numberOfChannelsField;
    @FXML
    private Label upTimerLabel;
    @FXML
    private TextField hourField;
    @FXML
    private Label colonLabel;
    @FXML
    private TextField minutesField;
    @FXML
    private Button button;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(Devises o: Devises.values())
            typeComboBox.getItems().add(o.getName());
        serialNumberInterface = new TextParameterDecorator(serialNumberLabel, serialNumberField, "5");
        yearOfReleaseInterface = new TextParameterDecorator(yearOfReleaseLabel, yearOfReleaseField, "2020");
        numberOfChannelsInterface = new TextParameterDecorator(numberOfChannelsLabel, numberOfChannelsField, "0");
        hourInterface = new TextParameterDecorator(upTimerLabel, hourField, "0");
        minutesInterface = new TextParameterDecorator(colonLabel, minutesField, "0");


        serialNumberInterface.hide();
        yearOfReleaseInterface.hide();
        numberOfChannelsInterface.hide();
        hourInterface.hide();
        minutesInterface.hide();
    }

    public void typeComboBoxChange(){
        for(Devises o: Devises.values())
            if(o.getName().equals(typeComboBox.getValue()))
                devise = o;

        if(devise.id > 0) {
            serialNumberInterface.block();
            serialNumberInterface.unblock();
            yearOfReleaseInterface.unblock();
            numberOfChannelsInterface.visible();
            numberOfChannelsInterface.setText(devise.getChannels().toString());
            upTimerUnblock();
            buttonUnblock();
        } else {
            serialNumberInterface.hide();
            yearOfReleaseInterface.hide();
            numberOfChannelsInterface.hide();
            upTimerHide();
            if(devise == Devises.VOID)
                buttonBlock();
            else
                buttonUnblock();
        }
    }

    public void serialNumberFieldChange(){
        serialNumber = serialNumberInterface.getTextConvertToInt();

        if (serialNumber > 0 && serialNumber <= 16383) {
            yearOfReleaseInterface.unblock();
            numberOfChannelsInterface.visible();
            numberOfChannelsInterface.setText(devise.getChannels().toString());
            upTimerUnblock();
            buttonUnblock();
        } else {
            yearOfReleaseInterface.hide();
            numberOfChannelsInterface.hide();
            upTimerHide();
            buttonBlock();
        }
    }

    public void yearOfReleaseFieldChange(){
        yearOfRelease = yearOfReleaseInterface.getTextConvertToInt();

        if(yearOfRelease > 2018 && yearOfRelease <= 2047) {
            numberOfChannelsInterface.visible();
            numberOfChannelsInterface.setText(devise.getChannels().toString());
            upTimerUnblock();
            buttonUnblock();
        } else {
            numberOfChannelsInterface.hide();
            upTimerHide();
            buttonBlock();
        }
    }

    public void numberOfChannelsFieldChange(){
        numberOfChannels = numberOfChannelsInterface.getTextConvertToInt();

        if (numberOfChannels > 0 && numberOfChannels <= devise.getChannels()) {
            upTimerUnblock();
            buttonUnblock();
        } else {
            upTimerBlock();
            buttonBlock();
        }
    }

    public void hourFieldChange(){
        hour = hourInterface.getTextConvertToInt();

        if (hour >= 0) {
            buttonUnblock();
        } else {
            buttonBlock();
        }
    }

    public void minutesFieldChange(){
        minutes = minutesInterface.getTextConvertToInt();

        if (minutes >= 0 && minutes < 60) {
            button.setDisable(false);
        } else {
            buttonBlock();
        }
    }

    public void buttonPush(){

    }

    private void upTimerBlock(){
        hourInterface.block();
        minutesInterface.block();
    }
    private void upTimerUnblock(){
        hourInterface.unblock();
        minutesInterface.unblock();
    }
    private void upTimerVisible(){
        hourInterface.visible();
        minutesInterface.visible();
    }
    private void upTimerHide(){
        hourInterface.hide();
        minutesInterface.hide();
    }


    private void buttonBlock(){
        button.setDisable(true);
    }
    private void buttonUnblock(){
        button.setDisable(false);
    }

    private enum Devises{
        VOID(" ", 0, 0),
        GNSL("ГНСЛ", 7, 4),
        RSIL("РСиЛ", 21, 0),
        RSGL("РСгЛ", 22, 0),
        VOLS("УЗ ВОЛС", 6, 4),
        SVAZ("Г СВАЗ",3,4),
        UKVZ("МС УКВЗ",10,4),
        UKAZ("МС УКАЗ",11,4),
        SAZ("Г ЗЭС",15,2),
        EMP("Г ШЭП",16,2),
        PDU("ПДУ",2,0),
        IT("ИТ",-1,0),
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

