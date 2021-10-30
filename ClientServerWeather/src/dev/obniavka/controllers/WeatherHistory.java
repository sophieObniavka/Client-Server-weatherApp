package dev.obniavka.controllers;

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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;

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
    Stage window;

    ObservableList<String> cityList = FXCollections.observableArrayList("Донецьк, Донецька область","Львів, Львівська область",
            "Київ, Київська область", "Тернопіль, Тернопільська область","Івано-Франківськ, Івано-Франківська область","Дніпро, Дніпровська область",
            "Луцьк, Волинська область", "Ужгород, Ужгородська область", "Вінниця, Вінницька область", "Луганськ, Луганська область","Житомир, Житомирська область",
            "Запоріжжя, Запорізька область","Кіровоград, Кіровоградська область", "Миколаїв, Миколаївська область", "Одеса, Одеська область", "Полтава, Полтавська область",
            "Рівне, Рівненська область", "Суми, Сумська область", "Харків, Харківська область", "Херсон, Херсонська область", "Хмельницький, Хмельницька область",
            "Черкаси, Черкаська область", "Чернігів, Чернігівська область" , "Чернівці, Чернівецька область");

    @FXML
    void initialize(){

        cityPick.setValue("Місто, область");
        cityPick.setItems(cityList);

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



    public  void authorizeScene(ActionEvent actionEvent) throws IOException{
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/login.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


    public void turnNow(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/sample.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void turnSevenDays(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/dev/obniavka/scenes/sevenDays.fxml"));
        Scene scene = new Scene(newScene);
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
