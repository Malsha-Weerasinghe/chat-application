import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/login.fxml"));
        Scene mainScene = new Scene(root);
        stage.setScene(mainScene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();
        stage.show();
    }
}
