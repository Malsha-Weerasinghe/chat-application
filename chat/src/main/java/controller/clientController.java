package controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.*;
import java.net.Socket;

public class clientController extends Thread{
   public AnchorPane emojiPane;
   public Label lblName;
   public TextField messageField;
   public ScrollPane messageShowField;
   public VBox vBox;

   BufferedReader reader;
   PrintWriter writer;
   Socket socket;
   private FileChooser fileChooser;
   private File file;

   public void initialize() throws IOException{
      String username = loginController.inputName;
      lblName.setText(username);

      try{
         socket = new Socket("localhost", 4500);
         System.out.println("Socket is connected with server");
         reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         writer = new PrintWriter(socket.getOutputStream(), true);

        this.start();
      }catch (IOException e){
         e.printStackTrace();
      }
      emojiPane.setVisible(false);
   }

   public void messageOnAction(ActionEvent event) {

    }
}
