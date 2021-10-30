package dev.obniavka.controllers;


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



    //action to turn back to the main stage
    public void turnBackToNow(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/sample.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

    }

}
