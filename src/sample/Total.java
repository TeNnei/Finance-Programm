package sample;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;


public class Total {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML
    private TreeTableView<Expences> expences = new TreeTableView<Expences>();
    @FXML TreeTableColumn<Expences, String> treeTableColumn = new TreeTableColumn<>("Имя счета");
    @FXML TreeTableColumn<Expences, String> treeTableColumn1 = new TreeTableColumn<>("Код");
    @FXML TreeTableColumn<Expences, String> treeTableColumn2 = new TreeTableColumn<>("Итог");


    @FXML
    void initialize() {
        treeTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("expens"));
        treeTableColumn1.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("total"));

        expences.getColumns().add(treeTableColumn);
        expences.getColumns().add(treeTableColumn1);
        expences.getColumns().add(treeTableColumn2);

    }

   public void HashMapIn (){
        String MapInf = "SELECT category, name_score, defference FROM consolid";
         Map<String, List<String>> results = new HashMap<>();
        try {
            Connection Map = DatabaseHandler.getDbConnection();
            PreparedStatement FirstMap = Map.prepareStatement(MapInf);
            ResultSet rs = FirstMap.executeQuery();
            while (rs.next()){
                String category = rs.getString(1);
                String name_score = rs.getString(2);
                String difference = rs.getString(3);

                    List<String> score_names = new ArrayList<>();
                    score_names.add(name_score);
                    score_names.add(difference);
                    results.put(category, score_names);

                TreeItem infor = new TreeItem(new Expences(name_score, difference));
                TreeItem finall = new TreeItem(new Expences(category));
                finall.getChildren().add(infor);
                expences.setRoot(finall);
            }
            Map.close();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(results);
    }
}
