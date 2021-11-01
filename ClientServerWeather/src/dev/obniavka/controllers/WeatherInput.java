package dev.obniavka.controllers;

import dev.obniavka.InfoBox;
import dev.obniavka.dbconnection.SaveWeatherInfoToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class WeatherInput {
    public DatePicker dateInput;
    public TextField writeTemp,writePress;

    SaveWeatherInfoToDB save = new SaveWeatherInfoToDB();
    InfoBox inf = new InfoBox();

    ObservableList<String> skyList = FXCollections.observableArrayList("Чисте","Хмарно","Сніг", "Дощ");
    ObservableList<String> cityList = FXCollections.observableArrayList("Донецьк, Донецька область","Львів, Львівська область",
            "Київ, Київська область", "Тернопіль, Тернопільська область","Івано-Франківськ, Івано-Франківська область","Дніпро, Дніпровська область",
            "Луцьк, Волинська область", "Ужгород, Закарпатська область", "Вінниця, Вінницька область", "Луганськ, Луганська область","Житомир, Житомирська область",
            "Запоріжжя, Запорізька область","Кіровоград, Кіровоградська область", "Миколаїв, Миколаївська область", "Одеса, Одеська область", "Полтава, Полтавська область",
            "Рівне, Рівненська область", "Суми, Сумська область", "Харків, Харківська область", "Херсон, Херсонська область", "Хмельницький, Хмельницька область",
            "Черкаси, Черкаська область", "Чернігів, Чернігівська область" , "Чернівці, Чернівецька область");
    public ComboBox skySelect, cityCheck;
    @FXML
    Label info;
    Stage window;


    public void turnBackToNow(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/sample.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void addWeather() {

        if (writePress.getText() == null || writeTemp.getText().isEmpty() || dateInput.getValue() == null || skySelect.getValue() == null || cityCheck.getValue() == null || writePress.getText() == null || skySelect.getValue() == "Небо" || cityCheck.getValue() == "Місто, область") {
             inf.weatherAdd();
        } else {
            inf.succsesfulAdding();
        }

    }

}
