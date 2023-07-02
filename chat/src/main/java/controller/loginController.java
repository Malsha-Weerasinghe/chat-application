package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    public TextField nameField;
    static String inputName;

    public void nameOnAction(ActionEvent event) throws IOException {

        inputName = nameField.getText();
        nameField.clear();
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) FXMLLoader.load(loginController.class.getResource("/view/clientForm.fxml"))));
    }
}
