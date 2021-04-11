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

public class Kamerton5_1 implements Initializable {
    TextParameterDecorator serialNumberInterface;
    TextParameterDecorator yearOfReleaseInterface;
    TextParameterDecorator numberOfChannelsInterface;
    ComboBoxParameterDecorator controllerInterface;

    private int serialNumber;
    private int yearOfRelease;
    private int numberOfChannels;
    private Controller controller;

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
        serialNumberInterface = new TextParameterDecorator(serialNumberLabel, serialNumberField, "5");
        yearOfReleaseInterface = new TextParameterDecorator(yearOfReleaseLabel, yearOfReleaseField, "2020");
        numberOfChannelsInterface = new TextParameterDecorator(numberOfChannelsLabel, numberOfChannelsField, "0");
        controllerInterface = new ComboBoxParameterDecorator(controllerLabel, controllerComboBox);

        for(Controller o: Controller.values())
            controllerInterface.addItems(o.getName());

        yearOfReleaseInterface.unblock();
        controllerInterface.unblock();
        buttonUnblock();
    }

    public void serialNumberFieldChange(){
        serialNumber = serialNumberInterface.getTextConvertToInt();

        if (serialNumber > 0 && serialNumber <= 16383) {
            yearOfReleaseInterface.unblock();
            controllerInterface.unblock();
            buttonUnblock();
        } else {
            yearOfReleaseInterface.block();
            numberOfChannelsInterface.block();
            controllerInterface.block();
            buttonBlock();
        }
    }

    public void yearOfReleaseFieldChange(){
        yearOfRelease = yearOfReleaseInterface.getTextConvertToInt();

        if(yearOfRelease > 2018 && yearOfRelease <= 2047) {
            controllerInterface.unblock();
            buttonUnblock();
        } else {
            numberOfChannelsInterface.block();
            controllerInterface.block();
            buttonBlock();
        }
    }

    public void numberOfChannelsFieldChange(){
        numberOfChannels = numberOfChannelsInterface.getTextConvertToInt();

        if (numberOfChannels > 0 && numberOfChannels < 5) {
            controllerInterface.unblock();
            buttonUnblock();
        } else {
            numberOfChannelsInterface.block();
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

    private enum Controller{
        STM32F407("STM32F407"),
        STM32F207("STM32F207");

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
