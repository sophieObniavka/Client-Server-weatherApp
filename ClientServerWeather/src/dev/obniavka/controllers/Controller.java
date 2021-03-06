package dev.obniavka.controllers;

import dev.obniavka.DaysOfWeek;
import dev.obniavka.InfoBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;




public class Controller{
    public Label cardOne;
    public ImageView iconClear;
    public ImageView iconCloud;
    public ImageView iconRainy;
    public Button admin;
    public ImageView Snow;
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


    InfoBox info = new InfoBox();



    public Controller() throws IOException {
    }

    ConnectionToServer cT = new ConnectionToServer();

    @FXML void initialize() {

        cT.closeClientAndServerTimerInitialization(cityInput.getText());


        searchButton.setOnAction(event -> {
            String cityBefore = cityInput.getText();
                iconClear.setVisible(false);
                iconRainy.setVisible(false);
                iconCloud.setVisible(false);
                Snow.setVisible(false);
                temp.setText("");
                day.setText("");
                tysk.setText("");
                temp.setText("Температура: ");
                day.setFont(javafx.scene.text.Font.font(null, FontWeight.BOLD, 28));
                day.setStyle("-fx-font-size: 28px;");
                tysk.setText("Тиск: ");
                try {
                    cT.closeServerIfClientIsInactive(cityBefore, cityInput.getText());

                   String [] result =  cT.weatherInfo(cityInput.getText()).split(",");

                    if(result[0].equals("null")){

                        info.incorrectCityMessage();
                    }
                    else {
                        if (result[2].contains("Clear")) {
                            iconClear.setVisible(true);
                        } else if (result[2].contains("Clouds")) {
                            iconCloud.setVisible(true);
                        } else if (result[2].contains("Rain")) {
                            iconRainy.setVisible(true);
                        } else if (result[2].contains("Snow")) {
                            Snow.setVisible(true);
                        }
                        temp.setText(temp.getText() + result[0] + "°C");
                        tysk.setText(tysk.getText() + result[1] + " мм рт. ст.");
                        day.setText(DaysOfWeek.setDays(0));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }



        });
    }



    public void turnSevenDays(ActionEvent actionEvent) throws IOException {

        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/sevenDays.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(event ->{
            javafx.application.Platform.exit();
        });

    }
    public void turnWeatherHistory(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/weatherHistory.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(event ->{
            javafx.application.Platform.exit();
        });
    }
    public  void authorizeScene(ActionEvent actionEvent) throws IOException{
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/login.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(event ->{
            javafx.application.Platform.exit();
        });
    }


}