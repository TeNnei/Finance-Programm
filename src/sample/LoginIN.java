package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginIN {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML private PasswordField pass;
    @FXML private TextField log;
    @FXML private Button logiIN;


    @FXML
    void initialize() {
logiIN.setOnAction(actionEvent -> {
    String login = log.getText();
    String password = pass.getText();

    checkCodeIn(login, password);
});

    }
    public void checkCodeIn (String  Log, String Pass){
        DatabaseHandler read = new DatabaseHandler();
        OperatorsCheck pr = new OperatorsCheck();
        pr.setLogin(Log);
        pr.setPassword(Pass);
        Boolean first = read.checkUser(pr);
        if(first)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Programm.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent first1 = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(first1));
            stage.setMinWidth(1366);
            stage.setMinHeight(768);
            stage.show();
            logiIN.getScene().getWindow().hide();
        }
        else{
            System.out.println("Error!");
        }
    }
}
