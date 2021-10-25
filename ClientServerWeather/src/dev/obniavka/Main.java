package dev.obniavka;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    Stage window;
    Scene scene1;

    public static void main(String[] args){ Application.launch(args); }

    @Override
    public void start(Stage stage) throws Exception{
        window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("scenes/sample.fxml"));
        stage.setHeight(300);
        stage.setWidth(200);
        scene1 = new Scene(root);
        window.setScene(scene1);
        window.setTitle("Weather");

    }


}
