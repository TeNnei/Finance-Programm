//package sample;
//
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.TableColumn.CellDataFeatures;
//import javafx.stage.Stage;
//import javafx.util.Callback;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//
//public class Controller {
//    @FXML
//    private ResourceBundle resources;
//    @FXML
//    private URL location;
//    @FXML
//    private TextField orgnization;
//    @FXML
//    private TableView tableView;
//    @FXML
//    private TextField money;
//    @FXML
//    private Button Continue;
//    @FXML
//    private Button Reload;
//    @FXML
//    private Button Excel;
//    @FXML
//    private Button Edit;
//    @FXML
//    private Button Operation;
//    @FXML
//    private RadioButton Som;
//    @FXML
//    private RadioButton Value;
//    private ObservableList<ObservableList> data;
//
//    @FXML
//    void initialize() {
//
//        ToggleGroup group = new ToggleGroup();
//        Value.setToggleGroup(group);
//        Som.setToggleGroup(group);
//
//        Operation.setOnAction(actionEvent -> {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/Operation.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Operation.getScene().getWindow().hide();
//            Parent first = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(first));
//            stage.setMinWidth(1250);
//            stage.setMinHeight(720);
//            stage.show();
//        });
//
//        buildData();
//        Reload.setOnAction(actionEvent -> {
//            tableView.getItems().clear();
//            buildData1();
//        });
//
//        Continue.setOnAction(actionEvent -> {
//            String firstp = orgnization.getText();
//            String secondp = money.getText();
//
//            if (firstp.equals("") && secondp.equals("")) {
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
//            } else if (firstp.equals("")) {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/sample/Error5.fxml"));
//                try {
//                    loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Parent first = loader.getRoot();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(first));
//                stage.showAndWait();
//            } else if (secondp.equals("")) {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/sample/Error6.fxml"));
//                try {
//                    loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Parent first = loader.getRoot();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(first));
//                stage.showAndWait();
//            } else {
//                checkIn(firstp);
//                buildData1();
//            }
//        });
//
//        Excel.setOnAction(actionEvent -> {
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet spreadsheet = workbook.createSheet("sample");
//
//            HSSFRow row;
//
//            for (int i = 0; i < tableView.getItems().size(); i++) {
//                spreadsheet.setDefaultColumnWidth(30);
//                row = spreadsheet.createRow(i);
//                ObservableList<String> currentRow = (ObservableList<String>) tableView.getItems().get(i);
//                for (int j = 0; j < currentRow.size(); j++) {
//                    row.createCell(j).setCellValue(currentRow.get(j));
//                }
//            }
//            FileOutputStream fileOut = null;
//            try {
//                fileOut = new FileOutputStream("DanWeek.xls");
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            try {
//                workbook.write(fileOut);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                fileOut.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        Edit.setOnAction(actionEvent -> {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/Edit.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Parent first = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(first));
//            stage.setMinWidth(800);
//            stage.setMinHeight(600);
//            stage.setMaxHeight(600);
//            stage.setMaxWidth(800);
//            stage.showAndWait();
//            stage.setOnCloseRequest(we -> buildData1());
//        });
//    }
//
//    public void buildData() {
//        Connection table;
//        ResultSet rs;
//        table = DatabaseHandler.getDbConnection();
//        data = FXCollections.observableArrayList();
//        String PostSQL = "SELECT * from " + Constant.TABLE_OF_ORGANIZATION + " ORDER BY " + Constant.NAME_OF_ORGANIZATIONS;
//        try {
//            rs = table.createStatement().executeQuery(PostSQL);
//            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                final int j = i;
//                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                col.setCellValueFactory((Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
//
//                tableView.getColumns().addAll(col);
//                System.out.println("Column [" + i + "] ");
//            }
//
//            while (rs.next()) {
//                ObservableList<String> row = FXCollections.observableArrayList();
//                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                    row.add(rs.getString(i));
//                }
//                System.out.println("Row [1] added " + row);
//                data.add(row);
//            }
//            tableView.setItems(data);
//            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
//    }
//
//    public void buildData1() {
//        Connection table;
//        ResultSet rs;
//        table = DatabaseHandler.getDbConnection();
//        data = FXCollections.observableArrayList();
//        String PostSQL = "SELECT * from " + Constant.TABLE_OF_ORGANIZATION + " ORDER BY " + Constant.NAME_OF_ORGANIZATIONS;
//        try {
//            rs = table.createStatement().executeQuery(PostSQL);
//            while (rs.next()) {
//                ObservableList<String> row = FXCollections.observableArrayList();
//                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                    row.add(rs.getString(i));
//                }
//                System.out.println("Row [1] UPDATED " + row);
//                data.add(row);
//            }
//            tableView.setItems(data);
//            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
//    }
//
//    private void registrationOrganization1() {
//        int mon = 0;
//        int mon1 = 0;
//        DatabaseHandler reg = new DatabaseHandler();
//        String organ = orgnization.getText();
//        if (!Som.isSelected() && !Value.isSelected()){
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/Error7.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Parent first = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(first));
//            stage.showAndWait();
//        }else{
//            if(Som.isSelected()){
//                mon1 = Integer.parseInt(money.getText());
//            }else {
//                mon = Integer.parseInt(money.getText());
//            }
//            User org = new User(organ, mon, mon1);
//            reg.writerUser(org);
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/Gotit.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Parent first = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(first));
//            stage.showAndWait();
//            orgnization.setText("");
//            money.setText("");
//        }
//    }
//    public void checkIn(String loginProve) {
//        DatabaseHandler read = new DatabaseHandler();
//        User pr = new User();
//        pr.setOrganization(loginProve);
//        ResultSet first = read.getInformation(pr);
//
//        int fg = 0;
//        while (true) {
//            try {
//                if (!first.next()) break;
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//            fg++;
//        }
//        if (fg >= 1) {
//            System.out.println("IsHere!");
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/Error8.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Parent own = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(own));
//            stage.showAndWait();
//        } else{
//            registrationOrganization1();
//            System.out.println("NoOrg!");
//        }
//    }
//}