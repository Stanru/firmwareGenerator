package controllers.tabs;

import controllers.tabs.parameterInterfaces.TextParameterDecorator;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public abstract class AbstractTab implements Initializable {
    protected TextParameterDecorator serialNumberInterface;
    protected TextParameterDecorator yearOfReleaseInterface;
    protected TextParameterDecorator countChannelsInterface;
    protected TextParameterDecorator hourInterface;
    protected TextParameterDecorator minutesInterface;

    protected Properties properties = new Properties();

    protected Devises devise;
    protected int serialNumber = 5;
    protected int yearOfRelease = 2020;
    protected int countChannels = 4;
    protected int hour = 0;
    protected int minutes = 0;
    protected final int addressInFlash = 0x0800BFF0;

    abstract protected ComboBox<String> getTypeComboBox();
    abstract protected Label getSerialNumberLabel();
    abstract protected TextField getSerialNumberField();
    abstract protected Label getYearOfReleaseLabel();
    abstract protected TextField getYearOfReleaseField();
    abstract protected Label getCountChannelsLabel();
    abstract protected TextField getCountChannelsField();
    abstract protected Label getUpTimerLabel();
    abstract protected TextField getHourField();
    abstract protected Label getColonLabel();
    abstract protected TextField getMinutesField();
    abstract protected Button getButton();

    @Override
    final public void initialize(URL location, ResourceBundle resources) {
        for(Devises o: Devises.values())
            if(o.getNameInterface().equals(this.getClass().getName()))
                getTypeComboBox().getItems().add(o.getName());

        serialNumberInterface = new TextParameterDecorator(getSerialNumberLabel(), getSerialNumberField(), serialNumber);
        yearOfReleaseInterface = new TextParameterDecorator(getYearOfReleaseLabel(), getYearOfReleaseField(), yearOfRelease);
        countChannelsInterface = new TextParameterDecorator(getCountChannelsLabel(), getCountChannelsField(), countChannels);
        hourInterface = new TextParameterDecorator(getUpTimerLabel(), getHourField(), hour);
        minutesInterface = new TextParameterDecorator(getColonLabel(), getMinutesField(), minutes);

        initializeInterface();
    }

    abstract public void initializeInterface();

    final public void typeComboBoxChange(){
        for(Devises o: Devises.values())
            if(o.getName().equals(getTypeComboBox().getValue()))
                devise = o;

        if(devise.getTypeDev() > 0) {
            serialNumberInterface.block();
            serialNumberInterface.unblock();
            yearOfReleaseInterface.unblock();
            countChannelsInterface.visible();
            countChannelsInterface.setText(String.valueOf(devise.getCountChannels()));
            upTimerUnblock();
        } else {
            serialNumberInterface.hide();
            yearOfReleaseInterface.hide();
            countChannelsInterface.hide();
            upTimerHide();
        }
        buttonUnblock();
    }

    final public void serialNumberFieldChange(){
        serialNumber = serialNumberInterface.getTextConvertToInt();

        if (serialNumber > 0 && serialNumber <= 16383) {
            yearOfReleaseInterface.unblock();
            countChannelsInterface.visible();
            countChannelsInterface.setText(String.valueOf(devise.getCountChannels()));
            upTimerUnblock();
            buttonUnblock();
        } else {
            yearOfReleaseInterface.hide();
            countChannelsInterface.hide();
            upTimerHide();
            buttonBlock();
        }
    }

    final public void yearOfReleaseFieldChange(){
        yearOfRelease = yearOfReleaseInterface.getTextConvertToInt();

        if(yearOfRelease > 2018 && yearOfRelease <= 2047) {
            countChannelsInterface.visible();
            countChannelsInterface.setText(String.valueOf(devise.getCountChannels()));
            upTimerUnblock();
            buttonUnblock();
        } else {
            countChannelsInterface.hide();
            upTimerHide();
            buttonBlock();
        }
    }

    final public void countChannelsFieldChange(){
        countChannels = countChannelsInterface.getTextConvertToInt();

        if (countChannels > 0 && countChannels <= devise.getCountChannels()) {
            upTimerUnblock();
            buttonUnblock();
        } else {
            upTimerBlock();
            buttonBlock();
        }
    }

    final public void hourFieldChange(){
        hour = hourInterface.getTextConvertToInt();

        if (hour >= 0) {
            buttonUnblock();
        } else {
            buttonBlock();
        }
    }

    final public void minutesFieldChange(){
        minutes = minutesInterface.getTextConvertToInt();

        if (minutes >= 0 && minutes < 60) {
            getButton().setDisable(false);
        } else {
            buttonBlock();
        }
    }

    final protected void upTimerBlock(){
        hourInterface.block();
        minutesInterface.block();
    }

    final protected void upTimerUnblock(){
        hourInterface.unblock();
        minutesInterface.unblock();
    }

    final protected void upTimerVisible(){
        hourInterface.visible();
        minutesInterface.visible();
    }

    final protected void upTimerHide(){
        hourInterface.hide();
        minutesInterface.hide();
    }

    final protected void buttonBlock(){
        getButton().setDisable(true);
    }

    final protected void buttonUnblock(){
        getButton().setDisable(false);
    }

    final public void buttonPush(){
        // TODO: 21.04.2021 Подключить проперти из окружения
        // TODO: 21.04.2021 Сформировать строку записи времени наработки
        String stringConfigDevice = ":020000040800F2\n" + getStringConfigDevice();
        String nameFileFirmware = getNameFileFirmware();

        System.out.println(stringConfigDevice);
        System.out.println(nameFileFirmware);
        // TODO: 21.04.2021 реализовать алгоритм записи файла
    }

    abstract public String getNameFileFirmware();

    final protected String getStringConfigDevice(){
        byte[] stringId = new byte[12];

        int id =  (serialNumber & 0xE) << 5
                | (devise.getTypeDev() & 0x5) << 19
                | (devise.getTypeGroup() & 0x02) << 24
                | 3  << 30;

        stringId[0] = 8;
        stringId[1] = (byte)(addressInFlash >> 8);
        stringId[2] = (byte)addressInFlash;
        stringId[3] = 0x00;
        stringId[4] = (byte)id;
        stringId[5] = (byte)(id >> 8);
        stringId[6] = (byte)(id >> 16);
        stringId[7] = (byte)(id >> 24);
        stringId[8] = (byte)countChannels;
        stringId[9] = (byte)yearOfRelease;
        stringId[10] = (byte)(yearOfRelease >> 8);
        stringId[11] = 0x00;

        StringBuilder stringConfigDevice = new StringBuilder(":");
        for (byte b : stringId) {
            stringConfigDevice.append(String.format("%02X", b));
        }
        stringConfigDevice.append(CRC.getCRC8(stringId)).append("\n");

        return stringConfigDevice.toString();
    }
}
