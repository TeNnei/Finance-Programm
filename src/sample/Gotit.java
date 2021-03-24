package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Gotit {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button gotit;

    @FXML
    void initialize() {
        gotit.setOnAction(actionEvent -> {
    gotit.getScene().getWindow().hide();
});
    }
}