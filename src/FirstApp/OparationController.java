//package sample;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//import javafx.collections.ObservableList;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.stage.Stage;
//import javafx.util.Callback;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//
//
//public class OparationController {
//
//    @FXML
//    private ResourceBundle resources;
//    @FXML
//    private URL location;
//
//    @FXML private TableView<Object> tableView;
//    @FXML private TextField Money1;
//    @FXML private Button HowMuch;
//    @FXML private Button Reload;
//    @FXML private ComboBox<String> Box1;
//    @FXML private ComboBox<String> Box2;
//    @FXML private TextField Coment1;
//    @FXML private Button Excel1;
//    @FXML private RadioButton Som;
//    @FXML private RadioButton Value;
//    @FXML private Button Back;
//    @FXML private ComboBox<String> Filtr;
//    private ObservableList<Object> data;
//
//    @FXML
//    void initialize() {
//
//        List<String> strings = new ArrayList<>();
//        strings.add("За месяц");
//        strings.add("За неделю");
//        Filtr.setItems(FXCollections.observableArrayList(strings));
//
//
//        Filtr.setOnAction(actionEvent -> {
//            String filltr = String.valueOf(Filtr.getValue());
//            String firstofmonth = "За месяц";
//            if(filltr == firstofmonth){
//                buildDatabyMonth();
//            }
//        });
//
//
//        ToggleGroup group = new ToggleGroup();
//        Som.setToggleGroup(group);
//        Value.setToggleGroup(group);
//
//        buildData();
//
//
//        BoxEnter1();
//        BoxEnter2();
//
//        Excel1.setOnAction(actionEvent -> {
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
//                fileOut = new FileOutputStream("Operation.xls");
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
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/Gotit2.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Parent first = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(first));
//            stage.showAndWait();
//        });
//
//        HowMuch.setOnAction(actionEvent -> {
//            String prove = Box1.getValue();
//            String prove1 = Box2.getValue();
//            String prove2 = Money1.getText();
//
//            if (prove == null || prove1 == null || prove2.isEmpty()) {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/sample/Error4.fxml"));
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
//                if (!Som.isSelected() && !Value.isSelected()){
//                    FXMLLoader loader = new FXMLLoader();
//                    loader.setLocation(getClass().getResource("/sample/Error7.fxml"));
//                    try {
//                        loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Parent first = loader.getRoot();
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(first));
//                    stage.showAndWait();
//                }else{
//                    if(Som.isSelected()){
//                        registOP1();
//                    }
//                    else{
//                        registOP();
//                    }
//                    check(prove);
//                    check2(prove1);
//                    FXMLLoader loader = new FXMLLoader();
//                    loader.setLocation(getClass().getResource("/sample/Gotit2.fxml"));
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
//
//            }
//            Money1.setText("");
//            Coment1.setText("");
//            buildData1();
//        });
//        Back.setOnAction(actionEvent -> {
//            Back.getScene().getWindow().hide();
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/MainScene.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Parent One = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(One));
//            stage.show();
//        });
//
//
//    }
//    public ObservableList<String> BoxEnter1() {
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
//        Box1.setItems(data);
//        return data;
//    }
//    public ObservableList<String> BoxEnter2() {
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
//        Box2.setItems(data);
//        return data;
//    }
//
//    public void check2(String loginProve) {
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
//
//        if (fg >= 1) {
//            UpdateBase1();
//            System.out.println("Организация 2 найдена");
//        } else
//            System.out.println("Такой организации нет!");
//    }
//    public void check(String loginProve) {
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
//            UpdateBase();
//            System.out.println("Организация 1 найдена");
//        } else
//            System.out.println("Такой организации нет!");
//    }
//
//    private void UpdateBase() {
//        DatabaseHandler reg1 = new DatabaseHandler();
//        String organ1 = String.valueOf(Box1.getValue());
//        int mon = Integer.parseInt(Money1.getText());
//        User org = new User(organ1, mon);
//        if(Som.isSelected()){
//            reg1.setInformation(org);
//        }
//        else{
//            reg1.setInformation2(org);
//        }
//
//    }
//    private void UpdateBase1() {
//        DatabaseHandler reg1 = new DatabaseHandler();
//        String organ1 = String.valueOf(Box2.getValue());
//        int mon = Integer.parseInt(Money1.getText());
//        User org = new User(organ1, mon);
//        if(Som.isSelected()){
//            reg1.setInformation1(org);
//        }else{
//            reg1.setInformation3(org);
//        }
//
//    }
//
//    public void buildData() { // Заполнение таблицы
//        Connection table;
//        ResultSet rs;
//        table = DatabaseHandler.getDbConnection();
//        ObservableList<Object> data1 = FXCollections.observableArrayList();
//        String PostSQL = "SELECT * from " + Constant2.TABLE_OF_ORGANIZATION + " ORDER BY " + Constant2.DATE_OF_TRANSACTION + " DESC";
//        try {
//            rs = table.createStatement().executeQuery(PostSQL);
//            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                final int j = i;
//                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
//
//                tableView.getColumns().addAll(col);
//                System.out.println("Column [" + i + "] ");
//            }
//            while (rs.next()) {
//                ObservableList<String> row = FXCollections.observableArrayList();
//                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                    row.add(rs.getString(i));
//                }
//                System.out.println("Row [1] added " + row);
//                data1.add(row);
//            }
//            tableView.setItems(data1);
//            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
//    }
//    public void buildData1() { // Обновление таблицы
//        Connection table;
//        ResultSet rs;
//        table = DatabaseHandler.getDbConnection();
//        data = FXCollections.observableArrayList();
//        String PostSQL = "SELECT * from " + Constant2.TABLE_OF_ORGANIZATION + " ORDER BY " + Constant2.DATE_OF_TRANSACTION + " DESC";
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
//    private void registOP() {
//        DatabaseHandler reg1 = new DatabaseHandler();
//        String FromWhom = String.valueOf(Box1.getValue());
//        String toWhom = String.valueOf(Box2.getValue());
//        String Coment = Coment1.getText();
//        String mon1 = Money1.getText() + " $";
//        User2 org2 = new User2 (FromWhom, toWhom, mon1, Coment);
//        reg1.WriteOperation(org2);
//    }
//    private void registOP1() {
//        DatabaseHandler reg1 = new DatabaseHandler();
//        String FromWhom = String.valueOf(Box1.getValue());
//        String toWhom = String.valueOf(Box2.getValue());
//        String Coment = Coment1.getText();
//        String mon1 = Money1.getText() + " So'm";
//        User2 org2 = new User2 (FromWhom, toWhom, mon1, Coment);
//        reg1.WriteOperation(org2);
//    }
//
//    public void buildDatabyMonth() { // Обновление таблицы за промежуток времени
//        Connection table;
//        ResultSet rs;
//        table = DatabaseHandler.getDbConnection();
//        data = FXCollections.observableArrayList();
//        String PostSQL = "SELECT * FROM operation WHERE ДатаТранзакции BETWEEN  date_trunc('month', now()) AND date_trunc('month', now()) + ('1 month')::interval - ('1 days')::interval";
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
//}
