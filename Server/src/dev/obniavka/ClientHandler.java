package dev.obniavka;

import dev.obniavka.dbconnection.SaveWeatherInfoToDB;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    SaveWeatherInfoToDB db = new SaveWeatherInfoToDB();
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    String tmp;
    String date;
    public  ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter( client.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String scene = null;
                try {
                    scene = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                if (scene.equals("main")) {


                    System.out.println("Client needs weather for today");
                    tmp = in.readLine();


                    ArrayList<String> result = Weather.findForecast(tmp);
                    if (result == null) {
                        out.println("null");
                        System.out.println("There is no such city");
                    } else {
                        System.out.println(result.get(0) + "," + result.get(1) + "," + result.get(2));
                        out.println(result.get(0) + "," + result.get(1) + "," + result.get(2));
                    }
                    System.out.println("I've sent weather info to client!");

                } else if (scene.equals("seven days")) {
                    System.out.println("Client needs weather forecast");
                    tmp = in.readLine();
                    ArrayList<String> result = Weather.findForecast(tmp);

                    if (result == null) {
                        out.println("null");
                        System.out.println("There is no such city");
                    } else {
                        out.println(result.get(0) + "," + result.get(1) + "," + result.get(2) + "," + result.get(3) + "," + result.get(4) + "," + result.get(5) + "," + result.get(6) + "," + result.get(7) + "," + result.get(8) + "," + result.get(9) + "," + result.get(10) + "," + result.get(11) + "," + result.get(12) + "," + result.get(13) + "," + result.get(14) + "," + result.get(15) + "," + result.get(16) + "," + result.get(17) + "," + result.get(18) + "," + result.get(19) + "," + result.get(20));
                        System.out.println("First " + result.get(0) + "," + result.get(1) + "," + result.get(2));
                        System.out.println("second " + result.get(3) + "," + result.get(4) + "," + result.get(5));
                        System.out.println("third " + result.get(6) + "," + result.get(7) + "," + result.get(8));
                        System.out.println("fourth " + result.get(9) + "," + result.get(10) + "," + result.get(11));
                        System.out.println("fifth " + result.get(12) + "," + result.get(13) + "," + result.get(14));
                        System.out.println("sixth " + result.get(15) + "," + result.get(16) + "," + result.get(17));
                        System.out.println("seventh " + result.get(18) + "," + result.get(19) + "," + result.get(20));
                        System.out.println("I've sent forecast");
                    }
                } else if (scene.equals("historySearch")) {
                    System.out.println("Client wants to see weather history");
                    try {
                        tmp = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        date = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ArrayList<String> infoToClient;
                        infoToClient = db.findInfo(tmp, date);
                        if (infoToClient == null || infoToClient.isEmpty()) {
                            out.println("null");
                            System.out.println("I couldn't find data with selected criterias");
                        } else {
                            out.println(infoToClient.get(0) + "," + infoToClient.get(1) + "," + infoToClient.get(2));
                            System.out.println("I've sent weather history");
                        }



                } else if (scene.equals("authorize")) {
                    System.out.println("Admin wants to authorise");
                    String login = null;
                    try {
                        login = String.valueOf(in.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String pass = String.valueOf(in.readLine());

                    try {
                        out.println(db.authorize(login, pass));
                    } catch (SQLException e) {
                        System.out.println("MySQL exception");
                    } catch (ClassNotFoundException e) {
                        System.out.println("There is no such class");
                    }
                } else if (scene.equals("input")) {
                    System.out.println("Admin wants to add weather");
                    String oblast = String.valueOf(in.readLine());
                    String temp = String.valueOf(in.readLine());
                    String press = String.valueOf(in.readLine());
                    String date = null;
                    try {
                        date = String.valueOf(in.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String sky = String.valueOf(in.readLine());
                    try {
                        db.addWeather(oblast, temp, press, date, sky);
                        System.out.println("I've added data in database");
                    } catch (SQLException e) {
                        System.out.println("MySQL exception");
                    } catch (ClassNotFoundException e) {
                        System.out.println("There is no such class");
                    }

                }

            }


        } catch (IOException | SQLException | ClassNotFoundException e) {
          //  e.printStackTrace();
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                System.out.println("Client is gone!");
            }
        }
    }
}
