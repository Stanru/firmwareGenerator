import controllers.MainController;
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

    private static void handle(javafx.stage.WindowEvent event){
        try {
            Platform.exit();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Properties properties = new Properties();

        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("properties.properties")){
            properties.load(input);
        } catch (IOException e){
            e.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/windows/mainWindow.fxml"));
        Parent root = loader.load();
        // создаем сцену с заданными шириной и высотой и содержащую наш корневым контейнером, и связываем ее с окном
        Scene scene  = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle(properties.getProperty("progammName")+"_"+properties.getProperty("version")); // задаем заголовок окна
        primaryStage.show(); // запускаем окно

        primaryStage.setOnCloseRequest(Main::handle);
    }

    public static void main(String[] args) {
        launch();
    }
}
