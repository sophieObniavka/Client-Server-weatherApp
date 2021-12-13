package dev.obniavka.controllers;

import dev.obniavka.DaysOfWeek;
import dev.obniavka.InfoBox;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;



public class WeatherHistory {
    public Label day;
    public Text tysk;
    public Text temp;
    public ComboBox cityPick;
    public DatePicker datePick;
    public Label error;
    public ImageView iconClear;
    public ImageView iconCloud;
    public ImageView iconRainy;
    public Button search;
    public ImageView iconSnow;
    Stage window;

    ConnectionToServer cnT = new ConnectionToServer();

    public WeatherHistory() throws IOException {
    }


    ObservableList<String> cityList = FXCollections.observableArrayList("Донецьк, Донецька область","Львів, Львівська область",
            "Київ, Київська область", "Тернопіль, Тернопільська область","Івано-Франківськ, Івано-Франківська область","Дніпро, Дніпровська область",
            "Луцьк, Волинська область", "Ужгород, Ужгородська область", "Вінниця, Вінницька область", "Луганськ, Луганська область","Житомир, Житомирська область",
            "Запоріжжя, Запорізька область","Кіровоград, Кіровоградська область", "Миколаїв, Миколаївська область", "Одеса, Одеська область", "Полтава, Полтавська область",
            "Рівне, Рівненська область", "Суми, Сумська область", "Харків, Харківська область", "Херсон, Херсонська область", "Хмельницький, Хмельницька область",
            "Черкаси, Черкаська область", "Чернігів, Чернігівська область" , "Чернівці, Чернівецька область");



    @FXML
    void initialize() {

        cityPick.setValue("Місто, область");
        cityPick.setItems(cityList);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if((cityPick.getValue().equals("Місто, область") && String.valueOf(datePick.getValue()).equals("")) || cityPick.getValue().equals("Місто, область") || String.valueOf(datePick.getValue()).equals("")) {
                            System.exit(1);
                        }
                    }
                },
                3600000
        );
    }


   ArrayList<String> result() throws IOException {
        ArrayList<String> res = new ArrayList<>();
        if(cnT.searchHistory(cityPick.getValue().toString(), datePick.getValue().toString()).equals("null")){
            res.add("null");
            return res;
        }
            String[] infoList = cnT.searchHistory(cityPick.getValue().toString(), datePick.getValue().toString()).split(",");
            return new ArrayList<String>(Arrays.asList(infoList));
    }





    public void setDateInput() {
        LocalDate ld = datePick.getValue();
        LocalDate local = LocalDate.now();

        if (ld.isAfter(local)) {
            error.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(
                    Duration.seconds(3)
            );
            visiblePause.setOnFinished(
                    eve -> error.setVisible(false)
            );
            visiblePause.play();
            datePick.isDisable();
            datePick.getEditor().clear();
            datePick.setDisable(false);

        }
    }
    public void searchWeatherInfo() throws IOException {
        InfoBox info = new InfoBox();

        if (cityPick.getValue().toString().equals("Місто, область")){
            info.notAllCriterias();
        }
        else{
            if(result().get(0).equals("null")){
                info.noInfo();
            }
            else {
                iconClear.setVisible(false);
                iconRainy.setVisible(false);
                iconCloud.setVisible(false);
                iconSnow.setVisible(false);
                temp.setText("");
                day.setText("");
                tysk.setText("");
                temp.setText("Температура: ");
                LocalDate localDate = datePick.getValue();
                day.setText(DaysOfWeek.ukrainianDay((localDate.getDayOfWeek()).toString()));
                day.setFont(javafx.scene.text.Font.font(null, FontWeight.BOLD, 28));
                day.setStyle("-fx-font-size: 28px;");
                tysk.setText("Тиск: ");
                temp.setText(temp.getText() + result().get(0) + "°C");
                tysk.setText(tysk.getText() + result().get(1) + " мм рт. ст.");

                if (result().get(2).contains("Чисте")) {
                    iconClear.setVisible(true);
                } else if (result().get(2).contains("Хмарно")) {
                    iconCloud.setVisible(true);
                } else if (result().get(2).contains("Дощ")) {
                    iconRainy.setVisible(true);
                } else if (result().get(2).contains("Сніг")) {
                    iconSnow.setVisible(true);
                }
            }
        }

        String cityBefore = cityPick.getValue().toString();
        String dateBefore = datePick.getValue().toString();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if((cityPick.getValue().equals(cityBefore) && String.valueOf(datePick.getValue()).equals(dateBefore)) || cityPick.getValue().equals(cityBefore) || String.valueOf(datePick.getValue()).equals(dateBefore)) {
                            System.exit(1);
                        }
                    }
                },
                3600000
        );
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


    public void turnNow(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/sample.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(event ->{
            javafx.application.Platform.exit();
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



}
