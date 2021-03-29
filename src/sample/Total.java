package sample;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class Total {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML
    private TreeTableView<Expences> expences = new TreeTableView<Expences>();
    @FXML TreeTableColumn<Expences, String> treeTableColumn = new TreeTableColumn<>("Имя счета");
    @FXML TreeTableColumn<Expences, String> treeTableColumn2 = new TreeTableColumn<>("Итог");


    @FXML
    void initialize() {
        treeTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("expens"));
        treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("total"));

        expences.getColumns().add(treeTableColumn);
        expences.getColumns().add(treeTableColumn2);

    }

   public void HashMapIn (){
        String MapInf = "SELECT category, name_score, defference FROM consolid";
       Map<String, Map<Expences, List<Expences>>> results = new HashMap<>();
        try {
            Connection Map = DatabaseHandler.getDbConnection();
            PreparedStatement FirstMap = Map.prepareStatement(MapInf);
            ResultSet rs = FirstMap.executeQuery();
            while (rs.next()){
                String category = rs.getString(1);
                String name_score = rs.getString(2);
                String difference = rs.getString(3);
                BigDecimal total = new BigDecimal(difference);

                Expences infFromMap = new Expences(category, name_score, total);


            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       System.out.println(results);
    }
}
