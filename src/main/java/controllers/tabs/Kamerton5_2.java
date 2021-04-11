package controllers.tabs;

import controllers.tabs.parameterInterfaces.ComboBoxParameterDecorator;
import controllers.tabs.parameterInterfaces.TextParameterDecorator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Kamerton5_2 implements Initializable {
    TextParameterDecorator serialNumberInterface;
    TextParameterDecorator yearOfReleaseInterface;
    TextParameterDecorator numberOfChannelsInterface;
    ComboBoxParameterDecorator controllerInterface;

    private Devises devise;
    private int serialNumber;
    private int yearOfRelease;
    private int numberOfChannels;
    private Controller controller;

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
    private Label controllerLabel;
    @FXML
    private ComboBox<String> controllerComboBox;
    @FXML
    private Button button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(Devises o: Devises.values())
            typeComboBox.getItems().add(o.getName());
        serialNumberInterface = new TextParameterDecorator(serialNumberLabel, serialNumberField, "5");
        yearOfReleaseInterface = new TextParameterDecorator(yearOfReleaseLabel, yearOfReleaseField, "2020");
        numberOfChannelsInterface = new TextParameterDecorator(numberOfChannelsLabel, numberOfChannelsField, "0");
        controllerInterface = new ComboBoxParameterDecorator(controllerLabel, controllerComboBox);

        for(Controller o: Controller.values())
            controllerInterface.addItems(o.getName());

        serialNumberInterface.hide();
        yearOfReleaseInterface.hide();
        numberOfChannelsInterface.hide();
        controllerInterface.hide();
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
            controllerInterface.unblock();
            buttonUnblock();
        } else {
            serialNumberInterface.hide();
            yearOfReleaseInterface.hide();
            numberOfChannelsInterface.hide();
            controllerInterface.hide();
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
            controllerInterface.unblock();
            buttonUnblock();
        } else {
            yearOfReleaseInterface.hide();
            numberOfChannelsInterface.hide();
            controllerInterface.hide();
            buttonBlock();
        }
    }

    public void yearOfReleaseFieldChange(){
        yearOfRelease = yearOfReleaseInterface.getTextConvertToInt();

        if(yearOfRelease > 2018 && yearOfRelease <= 2047) {
            numberOfChannelsInterface.visible();
            numberOfChannelsInterface.setText(devise.getChannels().toString());
            controllerInterface.unblock();
            buttonUnblock();
        } else {
            numberOfChannelsInterface.hide();
            controllerInterface.hide();
            buttonBlock();
        }
    }

    public void numberOfChannelsFieldChange(){
        numberOfChannels = numberOfChannelsInterface.getTextConvertToInt();

        if (numberOfChannels > 0 && numberOfChannels <= devise.getChannels()) {
            controllerInterface.unblock();
            buttonUnblock();
        } else {
            controllerInterface.hide();
            buttonBlock();
        }
    }

    public void controllerComboBoxChange(){
        for(Controller o: Controller.values()) {
            if (o.getName().equals(controllerInterface.getText())) {
                controller = o;
                buttonUnblock();
                return;
            }
        }
        buttonBlock();
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
        BUK("БУК", 4, 0),
        BG("БГ", 5, 4);

        private String name;
        private int id;
        private int channels;
        private String path;

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

        public String getPath() { return path; }
    }

    private enum Controller{
        STM32F407("STM32F405"),
        STM32F207("STM32F205");

        private String name;
        private String file;

        Controller(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getFile() { return file; }
    }
}


