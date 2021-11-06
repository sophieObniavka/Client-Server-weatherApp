package dev.obniavka;

import dev.obniavka.dbconnection.SaveWeatherInfoToDB;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.sql.SQLException;
import java.util.ArrayList;

public class Server {
    public final static int PORT = 2411;

    public static void main(String[] a) throws IOException {
        ServerSocket s = new ServerSocket(Server.PORT);
        SaveWeatherInfoToDB db = new SaveWeatherInfoToDB();
        String tmp;

    try {


        Socket socket = s.accept();
        System.out.println("Yaay, client is connected");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out =
                new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream())), true);

             while (true) {
                String scene = in.readLine();
                System.out.println("Scene is " + scene);


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
                    }
                    else {
                        System.out.println(infoToClient.get(0) + "," + infoToClient.get(1) + "," + infoToClient.get(2));
                        out.println(infoToClient.get(0) + "," + infoToClient.get(1) + "," + infoToClient.get(2));
                    }

                    System.out.println("I've sent weather history");
                } catch (SQLException e) {
                    System.out.println("MySQL exception");
                } catch (ClassNotFoundException e) {
                    System.out.println("There is no such class");
                }



            }

             }



    } catch (IOException ex) {
        System.out.println("Server is closed");

    }

    }
}
