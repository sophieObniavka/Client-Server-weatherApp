package dev.obniavka.controllers;

import javax.swing.*;
import java.io.*;
import java.net.Socket;


public class ConnectionToServer {

        static Socket socket;

    static {
        try {
            socket = new Socket("localhost", 2411);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Немає з'єднання з сервером!");
        }
    }

    BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));


    PrintWriter out = new PrintWriter(
            new BufferedWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream())), true);


    public ConnectionToServer() throws IOException {
    }

    public String weatherInfo (String city) throws IOException {
        out.println("main");
        out.println(city);
        return String.valueOf(in.readLine());
    }

    public String forecast(String city) throws IOException{
        out.println("seven days");
        out.println(city);
        return String.valueOf(in.readLine());
    }

    public String searchHistory(String oblast, String date) throws IOException {
        out.println("historySearch");
        out.println(oblast);
        out.println(date);
        String search = String.valueOf(in.readLine());
        if(search.equals("null")){
            return "null";
        }
        return search;
    }

    public String authorize(String login, String pass) throws IOException {
        out.println("authorize");
        out.println(login);
        out.println(pass);

        return String.valueOf(in.readLine());
    }

    public void addWeather(String oblast,String temp,String pressure, String date, String sky){
        out.println("input");
        out.println(oblast);
        out.println(temp);
        out.println(pressure);
        out.println(date);
        out.println(sky);
    }
}
