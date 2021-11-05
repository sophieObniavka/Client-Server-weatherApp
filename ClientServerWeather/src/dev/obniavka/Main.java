package dev.obniavka;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    Stage windowMain;
    Scene scene1;


    public static void main (String[]args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        windowMain = stage;

        Parent root = FXMLLoader.load(getClass().getResource("scenes/sample.fxml"));
        stage.setHeight(831);
        stage.setWidth(1134);
        stage.setResizable(false);
        scene1 = new Scene(root);


        windowMain.setScene(scene1);
        windowMain.setTitle("Weather");
        windowMain.show();



    }


}
