package dev.obniavka.controllers;

import dev.obniavka.InfoBox;
import dev.obniavka.dbconnection.SaveWeatherInfoToDB;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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

ConnectionToServer cnT = new ConnectionToServer();

    public WeatherInput() throws IOException {
    }

    @FXML
    void initialize(){
        skySelect.setValue("Небо");
        skySelect.setItems(skyList);
        cityCheck.setValue("Місто, область");
        cityCheck.setItems(cityList);

    }

    public void setDateInput() {
        LocalDate ld = dateInput.getValue();
        LocalDate local = LocalDate.now();

        if (ld.isAfter(local)) {
            info.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(
                    Duration.seconds(3)
            );
            visiblePause.setOnFinished(
                    eve -> info.setVisible(false)
            );
            visiblePause.play();
            dateInput.isDisable();
            dateInput.getEditor().clear();
            dateInput.setDisable(false);

        }
    }



    public void turnBackToNow(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/sample.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void addWeather(){

        if (writePress.getText() == null || writeTemp.getText().isEmpty() || dateInput.getValue() == null || skySelect.getValue() == null || cityCheck.getValue() == null || writePress.getText() == null || skySelect.getValue() == "Небо" || cityCheck.getValue() == "Місто, область") {

            inf.weatherAdd();
        } else {
            cnT.addWeather(cityCheck.getValue().toString(), writeTemp.getText(), writePress.getText(), String.valueOf(dateInput.getValue()),skySelect.getValue().toString());
            inf.succsesfulAdding();
        }

    }

}
