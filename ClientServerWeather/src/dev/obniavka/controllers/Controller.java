package dev.obniavka.controllers;

import dev.obniavka.DaysOfWeek;
import dev.obniavka.UrlContent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;


import java.time.LocalDate;


public class Controller {
    public Label cardOne;
    public ImageView iconClear;
    public ImageView iconCloud;
    public ImageView iconRainy;
    public Button admin;
    @FXML
    private Label day;
    @FXML
    private Text tysk;
    @FXML
    private Text temp;
    @FXML
    private Button searchButton;
    @FXML
    private TextField cityInput;
    Stage window;

    @FXML void initialize(){


        searchButton.setOnAction(event -> {
            String getCity = cityInput.getText();
            String output = UrlContent.getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getCity + "&appid=2cbefecfc08bdc704d243280d9a5599b");
            LocalDate localDate = LocalDate.now();

            if (!output.isEmpty()) {

                JSONObject obj = new JSONObject(output);
                int tempr = (int) ((int) obj.getJSONObject("main").getDouble("temp") - 273.15);
                int press = ((int)obj.getJSONObject("main").getDouble("pressure")-282);
                iconClear.setVisible(false);
                iconRainy.setVisible(false);
                iconCloud.setVisible(false);
                temp.setText("");
                day.setText("");
                tysk.setText("");
                temp.setText("Температура: ");
                day.setText(DaysOfWeek.ukrainianDay((localDate.getDayOfWeek()).toString()));
                day.setFont(javafx.scene.text.Font.font(null, FontWeight.BOLD, 28));
                day.setStyle("-fx-font-size: 28px;");
                tysk.setText("Тиск: ");
                temp.setText(temp.getText() + tempr + "°C");
                tysk.setText(tysk.getText() + press + " мм рт. ст.");

                actionOnIcon();



            }
        });
    }

    public void actionOnIcon(){
        if(returnWeather().contains("Clear")){
            iconClear.setVisible(true);
        }
        else if(returnWeather().contains("Clouds")){
            iconCloud.setVisible(true);
        }
        else if(returnWeather().contains("Rain")){
            iconRainy.setVisible(true);
        }
    }

    public String returnWeather(){
        String getCity = cityInput.getText();
        String output = UrlContent.getUrlContent("http://api.openweathermap.org/data/2.5/forecast?q=" + getCity + "&appid=2cbefecfc08bdc704d243280d9a5599b");
        JSONObject obj = new JSONObject(output);
        JSONArray weatherObject = obj.getJSONArray("list");
        String weather = weatherObject.getJSONObject(0).toString();
        return weather;
    }



}