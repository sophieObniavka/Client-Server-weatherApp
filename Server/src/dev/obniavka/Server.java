package dev.obniavka;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public final static int PORT = 2411;

    public static void main(String[] a) throws IOException {
        ServerSocket s = new ServerSocket(Server.PORT);

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
                     tmp = in.readLine();
                     ArrayList<String> result = Weather.findForecast(tmp);
                     System.out.println("I've sent weather info to client!");
                     if (result == null) {
                         out.println("null");
                         System.out.println("There is no such city");
                     } else {
                         out.println(result.get(0) + "," + result.get(1) + "," + result.get(2));
                         //out.println(result.get(0) + "," + result.get(1));
                         System.out.println(result.get(0) + "," + result.get(1));
                         System.out.println(result.get(2).replace("\"", ""));
                     }

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

            else if(scene.equals("history")){
                System.out.println("cool");
            }

             }



    } catch (IOException ex) {
        System.out.println("Server is closed");

    }

    }
}
