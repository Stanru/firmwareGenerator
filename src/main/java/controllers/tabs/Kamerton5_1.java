package controllers.tabs;

import controllers.tabs.parameterInterfaces.TextParameterDecorator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Kamerton5_1 extends AbstractTab {
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
    private Label countChannelsLabel;
    @FXML
    private TextField countChannelsField;
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
    protected ComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    @Override
    protected Label getSerialNumberLabel() {
        return serialNumberLabel;
    }

    @Override
    protected TextField getSerialNumberField() {
        return serialNumberField;
    }

    @Override
    protected Label getYearOfReleaseLabel() {
        return yearOfReleaseLabel;
    }

    @Override
    protected TextField getYearOfReleaseField() {
        return yearOfReleaseField;
    }

    @Override
    protected Label getCountChannelsLabel() {
        return countChannelsLabel;
    }

    @Override
    protected TextField getCountChannelsField() {
        return countChannelsField;
    }

    @Override
    protected Label getUpTimerLabel() {
        return upTimerLabel;
    }

    @Override
    protected TextField getHourField() {
        return hourField;
    }

    @Override
    protected Label getColonLabel() {
        return colonLabel;
    }

    @Override
    protected TextField getMinutesField() {
        return minutesField;
    }

    @Override
    protected Button getButton() {
        return button;
    }

    @Override
    public void initializeInterface() {
        devise = Devises.KAMERTON_5;
        serialNumberInterface = new TextParameterDecorator(serialNumberLabel, serialNumberField, serialNumber);
        yearOfReleaseInterface = new TextParameterDecorator(yearOfReleaseLabel, yearOfReleaseField, yearOfRelease);
        countChannelsInterface = new TextParameterDecorator(countChannelsLabel, countChannelsField, countChannels);
        hourInterface = new TextParameterDecorator(upTimerLabel, hourField, hour);
        minutesInterface = new TextParameterDecorator(colonLabel, minutesField, minutes);
    }

    @Override
    public String getNameFileFirmware() {
        return String.format("Камертон_5_исп_1_%d(%d)_%s.hex", serialNumber, yearOfRelease, properties.getProperty("version"));
    }
}
