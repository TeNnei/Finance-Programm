package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ImportExcell {
    @FXML
    private TextField ExcelLink;
    @FXML
    private Button LinkGo;

    @FXML
    void initialize() {

        LinkGo.setOnAction(actionEvent -> {
            String link = ExcelLink.getText();
            InsertFromExcel Link = new InsertFromExcel();
            Link.InsertFromExcel(link);
        });
    }
}