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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.*;


public class ControllerOnSevenDays {
    public Label day7;
    public Label day2;
    public Label day3;
    public Label day4;
    public Label day5;
    public Label day6;
    public Label day1;
    public TextField cityInput;
    public Button searchButton;
    public ImageView Clear,Clear1, Clear2, Clear3, Clear4, Clear5, Clear6;
    public ImageView Clouds, Clouds1, Clouds2, Clouds3, Clouds4, Clouds5, Clouds6;
    public ImageView Rainy, Rainy1, Rainy2, Rainy3, Rainy4, Rainy5, Rainy6;
    public Button sevenDays;

    @FXML
    private Text temp;
    @FXML
    private Text temp1,temp2,temp3,temp4,temp5,temp6;
    @FXML
    private Text tysk,tysk1,tysk2,tysk3,tysk4,tysk5,tysk6;
    Stage window;

    InfoBox info = new InfoBox();

    ConnectionToServer cnT = new ConnectionToServer();

    public ControllerOnSevenDays() throws IOException {
    }

    @FXML void initialize(){
    searchButton.setOnAction(event -> {

            clearAll();

            day1.setText("");
            day2.setText("");
            day3.setText("");
            day4.setText("");
            day5.setText("");
            day6.setText("");
            day7.setText("");
            temp.setText("");
            temp2.setText("");
            temp3.setText("");
            temp4.setText("");
            temp5.setText("");
            temp6.setText("");
            tysk.setText("");
            tysk1.setText("");
            tysk2.setText("");
            tysk3.setText("");
            tysk4.setText("");
            tysk5.setText("");
            tysk6.setText("");
            tysk.setText("Тиск: ");
            tysk1.setText("Тиск: ");
            tysk2.setText("Тиск: ");
            tysk3.setText("Тиск: ");
            tysk4.setText("Тиск: ");
            tysk5.setText("Тиск: ");
            tysk6.setText("Тиск: ");
            temp2.setText("Температура: ");
            temp3.setText("Температура: ");
            temp4.setText("Температура: ");
            temp5.setText("Температура: ");
            temp6.setText("Температура: ");
            temp.setText("");
            temp.setText("Температура: ");
            temp1.setText("");
            temp1.setText("Температура: ");

        try {
            String[] result =
                    cnT.forecast(cityInput.getText()).split(",");
            if (result[0].equals("null")){
               info.incorrectCityMessage();
           }

            temp.setText(temp.getText() + result[0] + "°C");
            temp1.setText(temp1.getText() + result[3] + "°C");
           temp2.setText(temp2.getText() + result[6] + "°C");
            temp3.setText(temp3.getText() + result[9] + "°C");
            temp4.setText(temp4.getText() + result[12] + "°C");
            temp5.setText(temp5.getText() + result[15] + "°C");
            temp6.setText(temp6.getText() + result[18] + "°C");


           day1.setText(DaysOfWeek.setDays(0));
            day1.setFont(Font.font(null, FontWeight.BOLD, 28));
           day1.setStyle("-fx-font-size: 28px;");
            day2.setText(DaysOfWeek.setDays(1));
           day2.setFont(Font.font(null, FontWeight.BOLD, 28));
            day2.setStyle("-fx-font-size: 28px;");
            day3.setText(DaysOfWeek.setDays(2));
            day3.setFont(Font.font(null, FontWeight.BOLD, 28));
            day3.setStyle("-fx-font-size: 28px;");
            day4.setText(DaysOfWeek.setDays(3));
            day4.setFont(Font.font(null, FontWeight.BOLD, 28));
            day4.setStyle("-fx-font-size: 28px;");
            day5.setText(DaysOfWeek.setDays(4));
            day5.setFont(Font.font(null, FontWeight.BOLD, 28));
            day5.setStyle("-fx-font-size: 28px;");
            day6.setText(DaysOfWeek.setDays(5));
            day6.setFont(Font.font(null, FontWeight.BOLD, 28));
            day6.setStyle("-fx-font-size: 28px;");
            day7.setText(DaysOfWeek.setDays(6));
            day7.setFont(Font.font(null, FontWeight.BOLD, 28));
            day7.setStyle("-fx-font-size: 28px;");

            tysk.setText(tysk.getText() + result[1] + " мм рт. ст.");
            tysk1.setText(tysk1.getText() + result[4] + " мм рт. ст.");
            tysk2.setText(tysk2.getText() + result[7] + " мм рт. ст.");
            tysk3.setText(tysk3.getText() + result[10] + " мм рт. ст.");
            tysk4.setText(tysk4.getText() + result[13] + " мм рт. ст.");
            tysk5.setText(tysk5.getText() + result[16] + " мм рт. ст.");
            tysk6.setText(tysk6.getText() + result[19]+ " мм рт. ст.");

          setFirstPicture(result[2]);
            setSecondsPicture(result[5]);
            setThirdPicture(result[8]);
            setFourthPicture(result[11]);
            setFifthPicture(result[14]);
            setSixthPicture(result[17]);
            setSeventhPicture(result[20]);

        } catch (IOException e) {
            e.printStackTrace();
        }
        });
    }


//set invisible pictures before searching for new information
    void clearAll(){
        Clear.setVisible(false);
        Clear1.setVisible(false);
        Clear2.setVisible(false);
        Clear3.setVisible(false);
        Clear4.setVisible(false);
        Clear5.setVisible(false);
        Clear6.setVisible(false);
        Clouds.setVisible(false);
        Clouds1.setVisible(false);
        Clouds2.setVisible(false);
        Clouds3.setVisible(false);
        Clouds4.setVisible(false);
        Clouds5.setVisible(false);
        Clouds6.setVisible(false);
        Rainy.setVisible(false);
        Rainy1.setVisible(false);
        Rainy2.setVisible(false);
        Rainy3.setVisible(false);
        Rainy4.setVisible(false);
        Rainy5.setVisible(false);
        Rainy6.setVisible(false);
    }
// setting each picture

    void setFirstPicture(String sky) {
           if (sky.contains("Clear")) {
               Clear.setVisible(true);
           } else if (sky.contains("Clouds")) {
               Clouds.setVisible(true);
           } else if (sky.contains("Rain")) {
               Rainy.setVisible(true);
           }
       }

    void setSecondsPicture (String sky) {
        if (sky.contains("Clear")) {
            Clear1.setVisible(true);
        } else if (sky.contains("Clouds")) {
            Clouds1.setVisible(true);
        } else if (sky.contains("Rain")) {
            Rainy1.setVisible(true);
        }
    }

    void setThirdPicture (String sky) {
        if (sky.contains("Clear")) {
            Clear2.setVisible(true);
        } else if (sky.contains("Clouds")) {
            Clouds2.setVisible(true);
        } else if (sky.contains("Rain")) {
            Rainy2.setVisible(true);
        }
    }

    void setFourthPicture (String sky) {
        if (sky.contains("Clear")) {
            Clear3.setVisible(true);
        } else if (sky.contains("Clouds")) {
            Clouds3.setVisible(true);
        } else if (sky.contains("Rain")) {
            Rainy3.setVisible(true);
        }
    }

    void setFifthPicture (String sky) {
        if (sky.contains("Clear")) {
            Clear4.setVisible(true);
        } else if (sky.contains("Clouds")) {
            Clouds4.setVisible(true);
        } else if (sky.contains("Rain")) {
            Rainy4.setVisible(true);
        }
    }

    void setSixthPicture(String sky) {
        if (sky.contains("Clear")) {
            Clear5.setVisible(true);
        } else if (sky.contains("Clouds")) {
            Clouds5.setVisible(true);
        } else if (sky.contains("Rain")) {
            Rainy5.setVisible(true);
        }
    }

    void setSeventhPicture (String sky) {
        if (sky.contains("Clear")) {
            Clear6.setVisible(true);
        } else if (sky.contains("Clouds")) {
            Clouds6.setVisible(true);
        } else if (sky.contains("Rain")) {
            Rainy6.setVisible(true);
        }
    }


    //action on button that sets main scene
    public void turnBackToNow(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/sample.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    //action on button that sets login scene
    public  void authorizeScene(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/login.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    //action on button that sets weather history scene
    public void turnWeatherHistory(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/weatherHistory.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
