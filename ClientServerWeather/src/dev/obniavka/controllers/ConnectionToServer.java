package dev.obniavka.controllers;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ConnectionToServer {

        static Socket socket;

    static {
        try {
            socket = new Socket("localhost", 2411);
        } catch (IOException e) {
            e.printStackTrace();
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
       // System.out.println(in.readLine());
        return String.valueOf(in.readLine());
    }

    public int hello(){
        out.println("history");
        System.out.println("Hello");
        return 0;
    }
}
