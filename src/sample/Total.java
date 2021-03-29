package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Total {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TreeTableView<Expences> expences = new TreeTableView<Expences>();
    TreeTableColumn<Expences, String> treeTableColumn2 = new TreeTableColumn<>("Название счета");
    TreeTableColumn<Expences, BigDecimal> treeTableColumn3 = new TreeTableColumn<>("Итог");

    @FXML
    void initialize() {
        HashMapIn();
        treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
       treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("name_of_score"));
       treeTableColumn3.setCellValueFactory(new TreeItemPropertyValueFactory<>("difference"));


        expences.getColumns().add(treeTableColumn2);
        expences.getColumns().add(treeTableColumn3);
    }

   public void HashMapIn (){
        String MapInf = "SELECT category, name_score, defference FROM consolid";
       Map<String, Map<Expences, List<Expences>>> results = new HashMap<>();
        try {
            Connection Map = DatabaseHandler.getDbConnection();
            PreparedStatement FirstMap = Map.prepareStatement(MapInf);
            ResultSet rs = FirstMap.executeQuery();
            while (rs.next()) {
                String category = rs.getString(1);
                String name_score = rs.getString(2);
                String difference = rs.getString(3);

                BigDecimal total = new BigDecimal(difference);

                if (results.containsKey(category)) {
                    Map<Expences, List<Expences>> innerMap = results.get(category);
                    innerMap.entrySet().iterator().next().getValue().add(new Expences(category, name_score, total));
                } else {
                    Map<Expences, List<Expences>> innerMap = new HashMap<>();
                    innerMap.put(new Expences(category, category, BigDecimal.TEN), new ArrayList<>());
                    innerMap.entrySet().iterator().next().getValue().add(new Expences(category, name_score, total));
                    results.put(category, innerMap);
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       TreeItem<Expences> rootItem = new TreeItem(new Expences("Данные", "Данные", BigDecimal.ZERO));
       results.entrySet().forEach(entry -> {
           Map<Expences, List<Expences>> innerMap = entry.getValue();
           TreeItem<Expences> categoryItem;
           categoryItem = new TreeItem(innerMap.entrySet().iterator().next().getKey());
           rootItem.getChildren().add(categoryItem);
           innerMap.entrySet().iterator().next().getValue().forEach(expenceInList -> {
               TreeItem<Expences> scoreItem = new TreeItem(expenceInList);
               categoryItem.getChildren().add(scoreItem);
           });
       });
       expences.setRoot(rootItem);
    }
}
