package dev.obniavka;

import dev.obniavka.dbconnection.SaveWeatherInfoToDB;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Server {
    public final static int PORT = 2411;
    public static LocalDateTime clientStartedToWork = LocalDateTime.now();

    public static void main(String[] a) throws IOException {

        ServerSocket s = new ServerSocket(Server.PORT);
        SaveWeatherInfoToDB db = new SaveWeatherInfoToDB();
        String tmp;




        System.out.println("Waiting for client...");
        Socket socket = s.accept();
        System.out.println("Client is connected");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out =
                new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream())), true);
        try {
             while (true) {

                 String scene = in.readLine();
                




            if(scene.equals("main")) {
                System.out.println("Client needs weather for today");
                     tmp = in.readLine();
                     ArrayList<String> result = Weather.findForecast(tmp);
                     if (result == null) {
                         out.println("null");
                         System.out.println("There is no such city");
                     } else {
                         out.println(result.get(0) + "," + result.get(1) + "," + result.get(2));;
                     }
                System.out.println("I've sent weather info to client!");

                 }
            else if (scene.equals("seven days")){
                     System.out.println("Client needs weather forecast");
                     tmp = in.readLine();
                     ArrayList<String> result = Weather.findForecast(tmp);

                if (result == null) {
                    out.println("null");
                    System.out.println("There is no such city");
                } else {
                    out.println(result.get(0) + "," + result.get(1) + "," + result.get(2) + "," + result.get(3) + "," + result.get(4) + "," + result.get(5) + "," + result.get(6) + "," + result.get(7) + "," + result.get(8) + "," + result.get(9) + "," + result.get(10) + "," + result.get(11) + "," + result.get(12) + "," + result.get(13) + "," + result.get(14) + "," + result.get(15) + "," + result.get(16) + "," + result.get(17) + "," + result.get(18) + "," + result.get(19) + "," + result.get(20));
                    System.out.println("I've sent forecast");
                }
                 }

            else if(scene.equals("historySearch")){
                String date;
                System.out.println("Client wants to see weather history");
                tmp = in.readLine();
                date = in.readLine();

                ArrayList<String> infoToClient;
                try {
                    infoToClient = db.findInfo(tmp, date);
                    if(infoToClient == null || infoToClient.isEmpty()){
                        out.println("null");
                        System.out.println("I couldn't find data with selected criterias");
                    }
                    else {
                        out.println(infoToClient.get(0) + "," + infoToClient.get(1) + "," + infoToClient.get(2));
                        System.out.println("I've sent weather history");
                    }


                } catch (SQLException e) {
                    System.out.println("MySQL exception");
                } catch (ClassNotFoundException e) {
                    System.out.println("There is no such class");
                }

            }


            else if(scene.equals("authorize")){
                System.out.println("Admin wants to authorise");
                String login = String.valueOf(in.readLine());
                String pass = String.valueOf(in.readLine());

                try {
                    out.println(db.authorize(login,pass));
                } catch (SQLException e) {
                    System.out.println("MySQL exception");
                } catch (ClassNotFoundException e) {
                    System.out.println("There is no such class");
                }
            }

            else if(scene.equals("input")){
                System.out.println("Admin wants to add weather");
                String oblast = String.valueOf(in.readLine());
                String temp = String.valueOf(in.readLine());
                String press = String.valueOf(in.readLine());
                String date = String.valueOf(in.readLine());
                String sky = String.valueOf(in.readLine());
                socket.setSoTimeout(60*1000);
                try {
                    db.addWeather(oblast,temp,press,date,sky);
                    System.out.println("I've added data in database");
                } catch (SQLException e) {
                    System.out.println("MySQL exception");
                } catch (ClassNotFoundException e) {
                    System.out.println("There is no such class");
                }

            }

             }



    } catch (IOException ex) {

            out.println("I'm not working");
        System.out.println("Server is closed");

    }

    }
}
