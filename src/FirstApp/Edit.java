//package sample;
//
//import java.io.IOException;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.ToggleGroup;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.stage.Stage;
//
//public class Edit {
//    @FXML private URL location;
//    @FXML private ResourceBundle resources;
//    @FXML private RadioButton Selector1;
//    @FXML private RadioButton Selector2;
//    @FXML private ComboBox<String> Select1;
//    @FXML private TextField MoneyEdit;
//    @FXML private ComboBox<String> Select2;
//    @FXML private Button Delete;
//    @FXML private Button DeleteAll;
//    @FXML private Button doMONEY;
//    @FXML private RadioButton Selector3;
//    @FXML private RadioButton Selector4;
//
//    @FXML
//    void initialize() {
//        BoxEnter3();
//        ToggleGroup group = new ToggleGroup();
//        ToggleGroup group1 = new ToggleGroup();
//        Selector1.setToggleGroup(group);
//        Selector2.setToggleGroup(group);
//        Selector3.setToggleGroup(group1);
//        Selector4.setToggleGroup(group1);
//
//        doMONEY.setOnAction(actionEvent -> {
//            String test = Select1.getValue();
//            String test1 = MoneyEdit.getText();
//            if(test == null || test1.equals(""))
//            {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/sample/Error1.fxml"));
//                try {
//                    loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Parent first = loader.getRoot();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(first));
//                stage.showAndWait();
//            }
//            else {
//
//                if(!Selector1.isSelected() && !Selector2.isSelected()){
//                    FXMLLoader loader = new FXMLLoader();
//                    loader.setLocation(getClass().getResource("/sample/Error3.fxml"));
//                    try {
//                        loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Parent first = loader.getRoot();
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(first));
//                    stage.showAndWait();
//                }
//                else{
//                    if(!Selector3.isSelected() && !Selector4.isSelected())
//                    {
//                        FXMLLoader loader = new FXMLLoader();
//                        loader.setLocation(getClass().getResource("/sample/Error7.fxml"));
//                        try {
//                            loader.load();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Parent first = loader.getRoot();
//                        Stage stage = new Stage();
//                        stage.setScene(new Scene(first));
//                        stage.showAndWait();
//                    }
//                    else
//                        {
//                        if (Selector1.isSelected())
//                        {
//                        DatabaseHandler reg1 = new DatabaseHandler();
//                        String organ1 = String.valueOf(Select1.getValue());
//                        int mon = Integer.parseInt(MoneyEdit.getText());
//                        User org = new User(organ1, mon);
//                        if (Selector3.isSelected()){
//                            reg1.setInformation1(org);
//                        }else{
//                            reg1.setInformation3(org);
//                        }
//                    }
//                    else {
//                        DatabaseHandler reg1 = new DatabaseHandler();
//                        String organ1 = String.valueOf(Select1.getValue());
//                        int mon = Integer.parseInt(MoneyEdit.getText());
//                        User org = new User(organ1, mon);
//                        if(Selector3.isSelected()){
//                            reg1.setInformation(org);
//                        }else {
//                            reg1.setInformation2(org);
//                        }
//                    }
//                    }
//                }
//            }
//
//
//
//        });
//        Delete.setOnAction(actionEvent -> {
//            String ObjectFromSelect = Select2.getValue();
//            if (ObjectFromSelect == null){
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/sample/Error2.fxml"));
//                try {
//                    loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Parent first = loader.getRoot();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(first));
//                stage.showAndWait();
//            }
//            else
//            CheckDelete(ObjectFromSelect);
//        });
//        DeleteAll.setOnAction(actionEvent -> {
//            DeleteAllData();
//        });
//    }
//    public ObservableList<String> BoxEnter3() {
//        ObservableList<String> data;
//        Connection box;
//        ResultSet rs;
//        box = DatabaseHandler.getDbConnection();
//        data = FXCollections.observableArrayList();
//        String PostSQL = "SELECT Организация FROM " + Constant.TABLE_OF_ORGANIZATION + " ORDER BY " + Constant.NAME_OF_ORGANIZATIONS;
//        try {
//            rs = box.createStatement().executeQuery(PostSQL);
//            while (rs.next()) {
//                data.add(rs.getString(1));
//            }
//        } catch (SQLException ex) {
//            System.err.println("Error" + ex);
//        }
//        Select1.setItems(data);
//        Select2.setItems(data);
//        return data;
//    }
//
//    public void CheckDelete(String loginProve) {
//        DatabaseHandler read = new DatabaseHandler();
//        User pr = new User();
//        pr.setOrganization(loginProve);
//        read.DeleteInformation(pr);
//    }
//    public void DeleteAllData() {
//        Connection deleteAll = DatabaseHandler.getDbConnection();
//        String PostSQL = "DELETE FROM " + Constant.TABLE_OF_ORGANIZATION;
//        try {
//            PreparedStatement Delete = deleteAll.prepareStatement(PostSQL);
//            Delete.executeUpdate();
//        }catch (SQLException throwables){
//            throwables.printStackTrace();
//        }
//    }
//}