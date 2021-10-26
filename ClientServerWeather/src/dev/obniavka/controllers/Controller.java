package dev.obniavka.controllers;

import dev.obniavka.UrlContent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.JSONObject;



public class Controller {

    private Button searchButton;
    @FXML
    private TextField cityInput;


    @FXML void initialize(){


        searchButton.setOnAction(event -> {
            String getCity = cityInput.getText();
            String output = UrlContent.getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getCity + "&appid=2cbefecfc08bdc704d243280d9a5599b");

            if (!output.isEmpty()) {

                JSONObject obj = new JSONObject(output);




            }
        });
    }




}
