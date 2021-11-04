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


                /*  int b = 2;
               for(int i = 0; i < b; i++){
                   tmp = in.readLine();
                    ArrayList<String> result = Weather.findWeather(tmp);
                    out.println(result.get(0) + "," + result.get(1) + "," + result.get(2));
                    b++;
               }*/

            } catch (IOException ex) {
                System.out.println("Server socket error");
            }

    }
}
