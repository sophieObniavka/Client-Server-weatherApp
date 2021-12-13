package dev.obniavka;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public final static int PORT = 2411;
    public static int clientID = 1;


   private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

   private static ExecutorService pool = Executors.newFixedThreadPool(10000);
    public static void main(String[] a) throws IOException {

        ServerSocket s = new ServerSocket(Server.PORT);
        System.out.println("Server has started");
        System.out.println("Waiting for clients...");

        while (true) {
            Socket socket = s.accept();
            System.out.println("Client " + clientID + " is connected");
            clientID++;
            ClientHandler clientThread = new ClientHandler(socket);
            clientHandlers.add(clientThread);
            pool.execute(clientThread);
        }


    }
}
