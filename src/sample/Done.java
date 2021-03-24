package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Done {


    @FXML
    private Button back;


    @FXML
    void initialize(){
        back.setOnAction(actionEvent -> {
            back.getScene().getWindow().hide();
        });
    }
}
