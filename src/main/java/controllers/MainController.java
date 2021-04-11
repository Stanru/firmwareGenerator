package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    private Tab boxStix4;
    @FXML
    private Tab boxKamerton3;
    @FXML
    private Tab boxKamerton5_1;
    @FXML
    private Tab boxKamerton5_2;
    @FXML
    private Tab boxOrbita;
    @FXML
    private Tab boxRosyanka;
    @FXML
    private Tab boxZaslon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            boxStix4.setContent(FXMLLoader.load(getClass().getResource("/windows/tabs/stix4.fxml")));
            boxKamerton3.setContent(FXMLLoader.load(getClass().getResource("/windows/tabs/kamerton3.fxml")));
            boxKamerton5_1.setContent(FXMLLoader.load(getClass().getResource("/windows/tabs/kamerton5_1.fxml")));
            boxKamerton5_2.setContent(FXMLLoader.load(getClass().getResource("/windows/tabs/kamerton5_2.fxml")));
            boxOrbita.setContent(FXMLLoader.load(getClass().getResource("/windows/tabs/orbita.fxml")));
            boxRosyanka.setContent(FXMLLoader.load(getClass().getResource("/windows/tabs/rosyanka.fxml")));
            boxZaslon.setContent(FXMLLoader.load(getClass().getResource("/windows/tabs/zaslon.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
