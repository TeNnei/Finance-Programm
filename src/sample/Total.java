package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
    @FXML private TreeTableView<TotalGlobalClass> TotalGlobalView = new TreeTableView<TotalGlobalClass>();
    @FXML private ComboBox<String> filtrbox;

    TreeTableColumn<Expences, String> treeTableColumn2 = new TreeTableColumn<>("SOM");
    TreeTableColumn<Expences, BigDecimal> treeTableColumn3 = new TreeTableColumn<>("Итог");

    TreeTableColumn<ExpencesUSD, String> treeTableColumn4 = new TreeTableColumn<>("USD");
    TreeTableColumn<ExpencesUSD, BigDecimal> treeTableColumn5 = new TreeTableColumn<>("Итог USD");

    TreeTableColumn<TotalGlobalClass, String> treeTableColumn7 = new TreeTableColumn<>("Информация");
    TreeTableColumn<TotalGlobalClass, BigDecimal> treeTableColumn8 = new TreeTableColumn<>("Сальдо вход");
    TreeTableColumn<TotalGlobalClass, BigDecimal> treeTableColumn9 = new TreeTableColumn<>("Сальдо выход");
    TreeTableColumn<TotalGlobalClass, BigDecimal> treeTableColumn10 = new TreeTableColumn<>("Разница");
    TreeTableColumn<TotalGlobalClass, BigDecimal> treeTableColumn11 = new TreeTableColumn<>("Сальдо вход USD");
    TreeTableColumn<TotalGlobalClass, BigDecimal> treeTableColumn12 = new TreeTableColumn<>("Сальдо выход USD");
    TreeTableColumn<TotalGlobalClass, BigDecimal> treeTableColumn13 = new TreeTableColumn<>("Разница USD");

    Map<String, Map<Expences, List<Expences>>> results;

    Map<String, Map<TotalGlobalClass, List<TotalGlobalClass>>> TotalInf;
    BigDecimal amount = BigDecimal.ZERO;

    @FXML
    void initialize() {

        filtrbox.setOnAction(actionEvent -> {
            HashMapInFiltr();
        });

        HashMapIn();
    HashMapInUsd();
    TotalTableFill();
    boxFiltr ();

//        try (Writer writer = new FileWriter("Output2.json")) {
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            gson.toJson(resultsUSD, writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("name_of_score"));
    treeTableColumn3.setCellValueFactory(new TreeItemPropertyValueFactory<>("total"));

    treeTableColumn4.setCellValueFactory(new TreeItemPropertyValueFactory<>("name_of_score"));
    treeTableColumn5.setCellValueFactory(new TreeItemPropertyValueFactory<>("totalUsd"));

    treeTableColumn7.setCellValueFactory(new TreeItemPropertyValueFactory<>("name_of_score"));
    treeTableColumn8.setCellValueFactory(new TreeItemPropertyValueFactory<>("saldo_in_som"));
    treeTableColumn9.setCellValueFactory(new TreeItemPropertyValueFactory<>("saldo_out_som"));
    treeTableColumn10.setCellValueFactory(new TreeItemPropertyValueFactory<>("differenceSom"));
    treeTableColumn11.setCellValueFactory(new TreeItemPropertyValueFactory<>("saldo_in_usd"));
    treeTableColumn12.setCellValueFactory(new TreeItemPropertyValueFactory<>("saldo_out_usd"));
    treeTableColumn13.setCellValueFactory(new TreeItemPropertyValueFactory<>("differenceUsd"));


    TotalGlobalView.getColumns().add(treeTableColumn7);
    TotalGlobalView.getColumns().add(treeTableColumn8);
    TotalGlobalView.getColumns().add(treeTableColumn9);
    TotalGlobalView.getColumns().add(treeTableColumn10);
    TotalGlobalView.getColumns().add(treeTableColumn11);
    TotalGlobalView.getColumns().add(treeTableColumn12);
    TotalGlobalView.getColumns().add(treeTableColumn13);


    expences_usd.getColumns().add(treeTableColumn4);
    expences_usd.getColumns().add(treeTableColumn5);

    expences.getColumns().add(treeTableColumn2);
    expences.getColumns().add(treeTableColumn3);

  }

    public void HashMapIn (){
        String MapInf = "SELECT category, name_score, defference FROM consolid";
        results = new HashMap<>();
       MathContext mc = new MathContext(10);
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
                    innerMap.entrySet().iterator().next().getValue().add(new Expences(category, name_score, difference));
                    results.put(category, innerMap);
                }
                Map.Entry<Expences, List<Expences>> innerMap = results.get(category).entrySet().iterator().next();
                innerMap.getKey().setTotal(innerMap.getKey().getTotal().add(difference));
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

    public void HashMapInUsd(){
        String MapInf = "SELECT category, additional_score, name_score, difference_usd FROM consolid";
        Map<String, Map<ExpencesUSD, List<ExpencesUSD>>> resultsUSD;
        resultsUSD = new HashMap<>();
        MathContext mc = new MathContext(10);
        BigDecimal amount = BigDecimal.ZERO;
        try {
            Connection Map = DatabaseHandler.getDbConnection();
            PreparedStatement FirstMap = Map.prepareStatement(MapInf);
            ResultSet rs = FirstMap.executeQuery();
            while (rs.next()) {
                String category = rs.getString(1);
                String additional_score = rs.getString(2);
                String name_score = rs.getString(3);
                String differenceUsd = rs.getString(4);
                BigDecimal total = new BigDecimal(differenceUsd);
                amount = amount.add(total, mc);

                if (resultsUSD.containsKey(category)) {
                    Map<ExpencesUSD, List<ExpencesUSD>> innerMap = resultsUSD.get(category);
                    innerMap.entrySet().iterator().next().getValue().add(new ExpencesUSD(category, additional_score, name_score, total));
                } else
                    {
                    Map<ExpencesUSD, List<ExpencesUSD>> innerMap = new HashMap<>();
                    innerMap.put(new ExpencesUSD(category, category, additional_score, BigDecimal.ZERO), new ArrayList<>());
                    innerMap.entrySet().iterator().next().getValue().add(new ExpencesUSD(category, additional_score, name_score, total));
                    resultsUSD.put(category, innerMap);
                }
                Map.Entry<ExpencesUSD,List<ExpencesUSD>> innerMap = resultsUSD.get(category).entrySet().iterator().next();
                innerMap.getKey().setTotalUsd(innerMap.getKey().getTotalUsd().add(total));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        TreeItem<ExpencesUSD> rootItem = new TreeItem(new ExpencesUSD("Данные USD", "Данные USD", amount));
        resultsUSD.entrySet().forEach(entry -> {
            Map<ExpencesUSD, List<ExpencesUSD>> innerMap = entry.getValue();
            TreeItem<ExpencesUSD> categoryItem;
            categoryItem = new TreeItem(innerMap.entrySet().iterator().next().getKey());
            rootItem.getChildren().add(categoryItem);

            innerMap.entrySet().iterator().next().getValue().forEach(expenceInList -> {
                TreeItem<ExpencesUSD> scoreItem = new TreeItem(expenceInList);
                categoryItem.getChildren().add(scoreItem);

                innerMap.entrySet().iterator().next().getValue().forEach(second -> {
                    TreeItem<ExpencesUSD> secondItems2 = new TreeItem(second);
                    scoreItem.getChildren().add(secondItems2);
                });
            });
        });
        expences_usd.setRoot(rootItem);
    }

    public void TotalTableFill(){
        String informationFromBD = "SELECT category, name_score, saldo_in_som, saldo_out_som, defference, saldo_in_usd, saldo_out_usd, difference_usd FROM consolid";
        TotalInf = new HashMap<>();
        MathContext mc = new MathContext(10);
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalAmount2 = BigDecimal.ZERO;
        BigDecimal totalAmount3= BigDecimal.ZERO;
        BigDecimal totalAmount4 = BigDecimal.ZERO;
        BigDecimal totalAmount5 = BigDecimal.ZERO;
        BigDecimal totalAmount6 = BigDecimal.ZERO;
        try {
            Connection totalView = DatabaseHandler.getDbConnection();
            PreparedStatement Map = totalView.prepareStatement(informationFromBD);
            ResultSet rs = Map.executeQuery();
            while ((rs.next())){
                String category = rs.getString(1);
                String name_score = rs.getString(2);
                BigDecimal saldo_in_som = new BigDecimal(rs.getString(3));
                BigDecimal saldo_out_som = new BigDecimal(rs.getString(4));
                BigDecimal difference = new BigDecimal(rs.getString(5));
                BigDecimal saldo_in_usd = new BigDecimal(rs.getString(6));
                BigDecimal saldo_out_usd = new BigDecimal(rs.getString(7));
                BigDecimal difference_usd = new BigDecimal(rs.getString(8));

                totalAmount = totalAmount.add(saldo_in_som, mc);
                totalAmount2 = totalAmount2.add(saldo_out_som, mc);
                totalAmount3 = totalAmount3.add(difference,mc);
                totalAmount4 = totalAmount4.add(saldo_in_usd, mc);
                totalAmount5 = totalAmount5.add(saldo_out_usd, mc);
                totalAmount6 = totalAmount6.add(difference_usd, mc);

                if (TotalInf.containsKey(category)){
                    Map<TotalGlobalClass, List<TotalGlobalClass>> innerMap = TotalInf.get(category);
                    innerMap.entrySet().iterator().next().getValue().add(new TotalGlobalClass(category, name_score, saldo_in_som, saldo_out_som, difference,
                            saldo_in_usd, saldo_out_usd, difference_usd));
                }else{
                    Map<TotalGlobalClass, List<TotalGlobalClass>> innerMap = new HashMap<>();
                    innerMap.put(new TotalGlobalClass(category, category, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                            BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO), new ArrayList<>());
                    innerMap.entrySet().iterator().next().getValue().add(new TotalGlobalClass(category, name_score, saldo_in_som, saldo_out_som, difference,
                            saldo_in_usd, saldo_out_usd, difference_usd));
                    TotalInf.put(category, innerMap);
                }
                Map.Entry<TotalGlobalClass, List<TotalGlobalClass>> innerMap = TotalInf.get(category).entrySet().iterator().next();
                innerMap.getKey().setSaldo_in_som(innerMap.getKey().getSaldo_in_som().add(saldo_in_som));
                innerMap.getKey().setSaldo_out_som(innerMap.getKey().getSaldo_out_som().add(saldo_out_som));
                innerMap.getKey().setDifferenceSom(innerMap.getKey().getDifferenceSom().add(difference));
                innerMap.getKey().setSaldo_in_usd(innerMap.getKey().getSaldo_in_usd().add(saldo_in_usd));
                innerMap.getKey().setSaldo_out_usd(innerMap.getKey().getSaldo_out_usd().add(saldo_out_usd));
                innerMap.getKey().setDifferenceUsd(innerMap.getKey().getDifferenceSom().add(difference));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        TreeItem<TotalGlobalClass> rootItem = new TreeItem(new TotalGlobalClass("", "Итог", totalAmount, totalAmount2, totalAmount3, totalAmount4, totalAmount5, totalAmount6));
        TotalInf.entrySet().forEach(entry -> {
            Map<TotalGlobalClass, List<TotalGlobalClass>> innerMap = entry.getValue();
            TreeItem<TotalGlobalClass> categoryItem;
            categoryItem = new TreeItem(innerMap.entrySet().iterator().next().getKey());
            rootItem.getChildren().add(categoryItem);
            innerMap.entrySet().iterator().next().getValue().forEach(expenceInList -> {
                TreeItem<TotalGlobalClass> scoreItem = new TreeItem(expenceInList);
                categoryItem.getChildren().add(scoreItem);
            });
        });
        TotalGlobalView.setRoot(rootItem);
    }

    public ObservableList<String> boxFiltr (){
        ObservableList<String> dataIn;
        Connection con;
        ResultSet rs;
        con = DatabaseHandler.getDbConnection();
        dataIn = FXCollections.observableArrayList();
        String comboBoxIn = "SELECT category FROM consolid";
            try {
                rs = con.createStatement().executeQuery(comboBoxIn);
                while (rs.next()){
                 dataIn.add(rs.getString(1));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
    }
            filtrbox.setItems(dataIn);
            return dataIn;
    }

    public void HashMapInFiltr(){
        String MapInf = "SELECT category, name_score, defference FROM consolid WHERE category = ?";
        results = new HashMap<>();
        MathContext mc = new MathContext(10);
        String filtr = filtrbox.getValue();
        try {
            Connection Map = DatabaseHandler.getDbConnection();
            PreparedStatement FirstMap = Map.prepareStatement(MapInf);
            FirstMap.setString(1, filtr);

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
                    innerMap.entrySet().iterator().next().getValue().add(new Expences(category, name_score, difference));
                    results.put(category, innerMap);
                }
                Map.Entry<Expences, List<Expences>> innerMap = results.get(category).entrySet().iterator().next();
                innerMap.getKey().setTotal(innerMap.getKey().getTotal().add(difference));
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

}
