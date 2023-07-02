package controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class clientController extends Thread{

   public AnchorPane emojiPane;
   public Label lblName;
   public TextField messageField;
   public ScrollPane messageShowField;
   public javafx.scene.layout.VBox vBox;

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

   @Override
   public void run(){
      try {
         while (true) {

            String message = reader.readLine();
            String[] tokens = message.split(" ");
            String command = tokens[0];

            StringBuilder fullMessage = new StringBuilder();
            for (int i = 1; i < tokens.length; i++) {
               fullMessage.append(tokens[i] + " ");
            }

            String[] msgArray = message.split(" ");
            String s = "";
            for (int i = 0; i < msgArray.length - 1; i++) {
               s += msgArray[i + 1] + " ";
            }

            Text text = new Text(s);
            String firstLetter = "";

            if (s.length() > 3) {
               firstLetter = s.substring(0, 3);
            }

            if (firstLetter.equalsIgnoreCase("img")) {

               s = s.substring(3, s.length() - 1);

               File file = new File(s);
               Image image = new Image(file.toURI().toString());

               ImageView imageView = new ImageView(image);
               imageView.setFitHeight(150);
               imageView.setFitWidth(200);

               final HBox hbox = new HBox(10);
               hbox.setAlignment(Pos.BOTTOM_RIGHT);

               if (!command.equalsIgnoreCase(lblName.getText())) {

                  vBox.setAlignment(Pos.TOP_LEFT);
                  hbox.setAlignment(Pos.CENTER_LEFT);

                  Text textNew = new Text("  " + command + " :");
                  hbox.getChildren().add(textNew);
                  hbox.getChildren().add(imageView);

               } else {
                  hbox.setAlignment(Pos.BOTTOM_RIGHT);
                  hbox.getChildren().add(imageView);
                  Text textNew = new Text("");
                  hbox.getChildren().add(textNew);
               }


               Platform.runLater(() -> vBox.getChildren().addAll(hbox));
            } else {
               TextFlow textFlow = new TextFlow();

               if (!command.equalsIgnoreCase(lblName.getText() + ":")) {

                  Text textName = new Text(command + " ");
                  textName.getStyleClass().add("textName");
                  textFlow.getChildren().add(textName);

                  textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                          "-fx-background-color: rgb(95,48,224);" +
                          " -fx-background-radius: 30px");
                  textFlow.setPadding(new Insets(3, 10, 3, 10));

               }

               textFlow.getChildren().add(text);
               textFlow.setMaxWidth(200);

               TextFlow flow = new TextFlow(textFlow);
               final HBox hBox = new HBox(12);

               if (!command.equalsIgnoreCase(lblName.getText() + ":")) {

                  vBox.setAlignment(Pos.TOP_LEFT);
                  hBox.setAlignment(Pos.CENTER_LEFT);
                  hBox.getChildren().add(flow);

               } else {

                  Text text1 = new Text(fullMessage + " ");
                  TextFlow flow1 = new TextFlow(text1);
                  hBox.setAlignment(Pos.BOTTOM_RIGHT);
                  hBox.getChildren().add(flow1);
                  hBox.setPadding(new Insets(2, 5, 2, 10));


                  flow1.setStyle("-fx-color: rgb(239,242,255);" +
                          "-fx-background-color: rgb(92,150,231);" +
                          "-fx-background-radius: 30px");
                  flow1.setPadding(new Insets(3, 10, 3, 10));
               }

               Platform.runLater(() -> vBox.getChildren().addAll(hBox));


            }
         }
      }catch (Exception e){
         e.printStackTrace();
      }
   }

   public void annoying(MouseEvent event) {

   }

   public void closeOnAction(MouseEvent event) {
      System.exit(0);

   }

   public void cryNo(MouseEvent event) {

   }

   public void cryYes(MouseEvent event) {

   }

   public void dream(MouseEvent event) {

   }

   public void eww(MouseEvent event) {

   }

   public void happy(MouseEvent event) {

   }

   public void happyCry(MouseEvent event) {

   }

   public void hideEmoji(MouseEvent event) {
      emojiPane.setVisible(false);
   }

   public void joke(MouseEvent event) {

   }

   public void kidding(MouseEvent event) {

   }

   public void love(MouseEvent event) {

   }

   public void messageOnAction(ActionEvent event) {

      String message = messageField.getText();
      writer.println(lblName.getText() +": " + message);

      messageField.clear();

      if(message.equalsIgnoreCase("BYE") || message.equalsIgnoreCase("logout")){
         System.exit(0);
      }

   }

   public void messageSendOnAction(MouseEvent event) {

   }

   public void money(MouseEvent event) {

   }

   public void normal(MouseEvent event) {

   }

   public void sad(MouseEvent event) {

   }

   public void sendImageOnAction(MouseEvent event) {

   }

   public void viewEmojiOnAction(MouseEvent event) {

   }

   public void wonder(MouseEvent event) {

   }

   public void wow(MouseEvent event) {

   }
}
