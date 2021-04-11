import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main extends Application {
    Properties properties = new Properties();
    /**
     *
     * @param event
     */
    private static void handle(javafx.stage.WindowEvent event){
        try {
            Platform.exit();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * чтобы создать JavaFX приложения, достаточно реализовать метод start(Stage)
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("properties.properties")){
            properties.load(input);
        } catch (IOException e){
            e.printStackTrace();
        }

        Parent root = FXMLLoader.load(getClass().getResource("/windows/mainWindow.fxml"));
        // создаем сцену с заданными шириной и высотой и содержащую наш корневым контейнером, и связываем ее с окном
        Scene scene  = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle(properties.getProperty("progammName")+"_"+properties.getProperty("version")); // задаем заголовок окна
        primaryStage.show(); // запускаем окно

        primaryStage.setOnCloseRequest(Main::handle);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}
