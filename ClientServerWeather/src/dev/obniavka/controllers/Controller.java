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
import java.net.Socket;



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


    InfoBox info = new InfoBox();
    @FXML void initialize() throws IOException {
        Socket socket = new Socket("localhost", 2411);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));


        PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), true);





            searchButton.setOnAction(event -> {
                out.println(cityInput.getText());


                iconClear.setVisible(false);
                iconRainy.setVisible(false);
                iconCloud.setVisible(false);
                temp.setText("");
                day.setText("");
                tysk.setText("");
                temp.setText("Температура: ");
                day.setFont(javafx.scene.text.Font.font(null, FontWeight.BOLD, 28));
                day.setStyle("-fx-font-size: 28px;");
                tysk.setText("Тиск: ");
                try {
                    String []result = in.readLine().split(",");
                    if(result == null){
                        temp.setText("");
                        day.setText("");
                        tysk.setText("");
                        iconClear.setVisible(false);
                        iconCloud.setVisible(false);
                        iconRainy.setVisible(false);
                       info.incorrectCityMessage();
                    }
                    else {
                        temp.setText(temp.getText() + result[0] + "°C");
                        tysk.setText(tysk.getText() + result[1] + " мм рт. ст.");
                        day.setText(DaysOfWeek.ukrainianDay(result[2]));
                        if(result[3].contains("Clear")){
                            iconClear.setVisible(true);
                        }
                        else if(result[3].contains("Clouds")){
                            iconCloud.setVisible(true);
                        }
                        else if(result[3].contains("Rain")){
                            iconRainy.setVisible(true);
                        }
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
    }
    public void turnWeatherHistory(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/weatherHistory.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    public  void authorizeScene(ActionEvent actionEvent) throws IOException{
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/login.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


}