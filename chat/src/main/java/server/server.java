package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {

    private static ArrayList<handler> clients = new ArrayList<handler>();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket =  new ServerSocket(4500);
        Socket accept;
        while (true){

            System.out.println("Waiting for Client .... ");
            accept =  serverSocket.accept();
            System.out.println("Client connected");
            handler clientThread = new handler(accept, clients);
            clients.add(clientThread);
            clientThread.start();
        }
    }
}
