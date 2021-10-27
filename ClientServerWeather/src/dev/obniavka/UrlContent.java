package dev.obniavka;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlContent {
    public static String getUrlContent(String urlAddress){
        InfoBox info = new InfoBox();
        StringBuffer content = new StringBuffer();

        try{
            URL url = new URL(urlAddress);
            URLConnection connection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
        }

        catch (Exception e){
            info.incorrectCityMessage();
        }
        return content.toString();
    }
}
