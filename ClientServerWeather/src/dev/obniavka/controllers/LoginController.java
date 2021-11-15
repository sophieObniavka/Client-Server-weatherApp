package dev.obniavka.controllers;


import dev.obniavka.InfoBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    public Button adminBack;
    public PasswordField pass;
    public TextField login;
    Stage window;

   ConnectionToServer cnT = new ConnectionToServer();

    public LoginController() throws IOException {
    }


    //action to turn back to the main stage
    public void turnBackToNow(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/sample.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void enterInput(ActionEvent actionEvent) throws IOException{

        if(cnT.authorize(login.getText(), pass.getText()).equals("admin")) {
            Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/weatherInput.fxml"));
            Scene scene = new Scene(newScene);
            window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();


        }
        else {
            InfoBox info = new InfoBox();
            info.incorrectAdmin();
        }
    }


}
