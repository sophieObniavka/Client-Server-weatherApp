package dev.obniavka.controllers;


import dev.obniavka.InfoBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    void initialize() {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if((pass.getText().isEmpty() && login.getText().isEmpty()) || pass.getText().isEmpty() || login.getText().isEmpty()) {
                                javafx.application.Platform.exit();
                        }
                    }
                },
                3600000
        );
    }
    //action to turn back to the main stage
    public void turnBackToNow(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/sample.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(event ->{
            javafx.application.Platform.exit();
        });

    }

    public void enterInput(ActionEvent actionEvent) throws IOException{

        if(cnT.authorize(login.getText(), pass.getText()).equals("admin")) {
            Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/weatherInput.fxml"));
            Scene scene = new Scene(newScene);
            window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            window.setOnCloseRequest(event ->{
                javafx.application.Platform.exit();
            });

        }
        else {
            InfoBox info = new InfoBox();
            info.incorrectAdmin();
        }
    }


}
