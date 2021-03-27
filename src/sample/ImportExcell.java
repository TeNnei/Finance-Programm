package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ImportExcell {
    @FXML private TextField ExcelLink;
    @FXML private Button LinkGo;

    @FXML
    void initialize(){

        LinkGo.setOnAction(actionEvent -> {
            String link = ExcelLink.getText();
            InsertFromExcel Link = new InsertFromExcel();
            Link.InsertFromExcel(link);
        });
    }
}
