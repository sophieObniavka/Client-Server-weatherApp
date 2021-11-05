package dev.obniavka;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


public class Weather {

    public static ArrayList<String> findForecast(String city) {
        ArrayList<String> forecast = new ArrayList<>();
        String outputToCheck = URLContent.getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=2cbefecfc08bdc704d243280d9a5599b");

        String output = URLContent.getUrlContent("http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=2cbefecfc08bdc704d243280d9a5599b");

        if(Objects.equals(outputToCheck, "null")){
            return null;
        }

        JSONObject obj = new JSONObject(output);
        JSONArray weatherObject = obj.getJSONArray("list");
        for (int i = 0; i < 40; i+=6) {

          forecast.add(String.valueOf(Integer.parseInt(weatherObject.getJSONObject(i).toString().substring(weatherObject.getJSONObject(i).toString().indexOf("temp") + 4,weatherObject.getJSONObject(i).toString().indexOf("feels")).replaceAll("\\D+", "").substring(0,3))-273));
            forecast.add(String.valueOf(Integer.parseInt(weatherObject.getJSONObject(i).toString().substring(weatherObject.getJSONObject(i).toString().indexOf("pressure") + 4,weatherObject.getJSONObject(i).toString().indexOf("sea_level")).replaceAll("\\D+", ""))-282));
            forecast.add(weatherObject.getJSONObject(i).toString().substring(weatherObject.getJSONObject(i).toString().indexOf("main") + 6,weatherObject.getJSONObject(i).toString().indexOf("id")).replace(",", "").replace("\"", ""));

        }



        return forecast;
    }
}
