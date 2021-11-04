package dev.obniavka;

import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;


public class Weather {

    public static ArrayList<String> findWeather(String city){
        ArrayList<String> info = new ArrayList<>();
        String output = URLContent.getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=2cbefecfc08bdc704d243280d9a5599b");
        LocalDate localDate = LocalDate.now();

        if(output == null){
            return null;
        }
            JSONObject obj = new JSONObject(output);
            int tempr = (int) ((int) obj.getJSONObject("main").getDouble("temp") - 273.15);
            int press = ((int)obj.getJSONObject("main").getDouble("pressure")-282);
            String day = String.valueOf(localDate.getDayOfWeek());

        info.add(String.valueOf(tempr));
        info.add(String.valueOf(press));
        info.add(day);

        return info;

    }
}
