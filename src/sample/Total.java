package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.math.BigDecimal;
import java.math.MathContext;
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
    @FXML private TreeTableView<ExpencesUSD> expences_usd = new TreeTableView<ExpencesUSD>();
    TreeTableColumn<Expences, String> treeTableColumn2 = new TreeTableColumn<>("Название счета");
    TreeTableColumn<Expences, BigDecimal> treeTableColumn3 = new TreeTableColumn<>("Итог");

    TreeTableColumn<ExpencesUSD, String> treeTableColumn4 = new TreeTableColumn<>("Название счета");
    TreeTableColumn<ExpencesUSD, BigDecimal> treeTableColumn5 = new TreeTableColumn<>("Итог USD");

    @FXML
    void initialize() {
        HashMapIn();
        HashMapInUsd();
        Ui();
       treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("name_of_score"));
       treeTableColumn3.setCellValueFactory(new TreeItemPropertyValueFactory<>("difference"));


       treeTableColumn4.setCellValueFactory(new TreeItemPropertyValueFactory<>("name_of_score"));
       treeTableColumn5.setCellValueFactory(new TreeItemPropertyValueFactory<>("differenceUsd"));

       expences_usd.getColumns().add(treeTableColumn4);
       expences_usd.getColumns().add(treeTableColumn5);

        expences.getColumns().add(treeTableColumn2);
        expences.getColumns().add(treeTableColumn3);
    }

    Map<String, Map<Expences, List<Expences>>> results;
    BigDecimal amount = BigDecimal.ZERO;
   public void HashMapIn (){
        String MapInf = "SELECT category, name_score, defference FROM consolid";
        results = new HashMap<>();
       MathContext mc = new MathContext(10);
       BigDecimal total = BigDecimal.ZERO;
       Expences a = new Expences();

        try {
            Connection Map = DatabaseHandler.getDbConnection();
            PreparedStatement FirstMap = Map.prepareStatement(MapInf);
            ResultSet rs = FirstMap.executeQuery();
            while (rs.next()) {
                String category = rs.getString(1);
                String name_score = rs.getString(2);
                BigDecimal difference = new BigDecimal(rs.getString(3));

                amount = amount.add(difference, mc);
                if (results.containsKey(category)) {
                    Map<Expences, List<Expences>> innerMap = results.get(category);
                    innerMap.entrySet().iterator().next().getValue().add(new Expences(category, name_score, difference));
                }
                else {
                    Map<Expences, List<Expences>> innerMap = new HashMap<>();
                    innerMap.put(new Expences(category, category, BigDecimal.ZERO), new ArrayList<>());
                    innerMap.entrySet().iterator().next().getValue().add(new Expences(category, name_score, BigDecimal.ZERO));
                    results.put(category, innerMap);
                }
                Map<Expences, List<Expences>> summ = results.entrySet().iterator().next().getValue();
                List<Expences> totalsumm = summ.entrySet().iterator().next().getValue();
                BigDecimal total2 = totalsumm.iterator().next().getDifference();
                a.addEntry(total2);
                total = total.add(a.getTotal());
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       TreeItem<Expences> rootItem = new TreeItem(new Expences(" ", "Данные", amount));
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
    public  void Ui(){

    }

    public void HashMapInUsd(){
        String MapInf = "SELECT category, name_score, difference_usd FROM consolid";
        Map<String, Map<ExpencesUSD, List<ExpencesUSD>>> results = new HashMap<>();
        MathContext mc = new MathContext(10);
        BigDecimal amount = BigDecimal.ZERO;
        try {
            Connection Map = DatabaseHandler.getDbConnection();
            PreparedStatement FirstMap = Map.prepareStatement(MapInf);
            ResultSet rs = FirstMap.executeQuery();
            while (rs.next()) {
                String category = rs.getString(1);
                String name_score = rs.getString(2);
                String differenceUsd = rs.getString(3);
                BigDecimal total = new BigDecimal(differenceUsd);
                amount = amount.add(total, mc);

                if (results.containsKey(category)) {
                    Map<ExpencesUSD, List<ExpencesUSD>> innerMap = results.get(category);
                    innerMap.entrySet().iterator().next().getValue().add(new ExpencesUSD(category, name_score, total));
                } else {
                    Map<ExpencesUSD, List<ExpencesUSD>> innerMap = new HashMap<>();
                    innerMap.put(new ExpencesUSD(category, category, BigDecimal.TEN), new ArrayList<>());
                    innerMap.entrySet().iterator().next().getValue().add(new ExpencesUSD(category, name_score, total));
                    results.put(category, innerMap);
                }

            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        TreeItem<ExpencesUSD> rootItem = new TreeItem(new ExpencesUSD("Данные USD", "Данные USD", amount));
        results.entrySet().forEach(entry -> {
            Map<ExpencesUSD, List<ExpencesUSD>> innerMap = entry.getValue();
            TreeItem<ExpencesUSD> categoryItem;
            categoryItem = new TreeItem(innerMap.entrySet().iterator().next().getKey());
            rootItem.getChildren().add(categoryItem);
            innerMap.entrySet().iterator().next().getValue().forEach(expenceInList -> {
                TreeItem<ExpencesUSD> scoreItem = new TreeItem(expenceInList);
                categoryItem.getChildren().add(scoreItem);
            });
        });
        expences_usd.setRoot(rootItem);
    }
}
