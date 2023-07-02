package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class handler extends Thread{

    private ArrayList<handler> clients;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public handler (Socket socket, ArrayList<handler> clients) {
        try{
            this.socket = socket;
            this.clients = clients;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(),true);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void run(){

        try {
            String message;
            while ((message = reader.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                for (handler cl : clients) {
                    cl.writer.println(message);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            try{
                reader.close();;
                writer.close();
                socket.close();
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }

}
