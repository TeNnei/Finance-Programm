package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class ProgrammController {
    @FXML private TableView<ObservableList> tableView;
    @FXML private Button Continue;
    private ObservableList<ObservableList> data;
    @FXML private Button consolidated;
    @FXML private Button update;
    @FXML private Button excel;
    private static final String CONTRACT_NUMBER_COLUMN_HEADER = "№ Договора";
    private static final String NUMBER_COLUMN_HEADER = "Договор";
    private static final String DEBIT_COLUMN_HEADER = "Дебит";
    private static final String CREDIT_COLUMN_HEADER = "Кредит";
    private static final String DATA_COLUMN_HEADER = "Дата";
    private static final String COMENTS_COLUMN_HEADER = "Расшифровка";
    private static final String SOM_COLUMN_HEADER = "Сумм";
    private static final String USD_COLUMN_HEADER = "Доллары";

    @FXML void initialize(){

        excel.setOnAction(actionEvent -> {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet spreadsheet = workbook.createSheet("Main_Information");
            HSSFRow row;
            HSSFRow row1;
            HSSFCell cell;

            row1 = spreadsheet.createRow(0); // Вот здесь начинается прописываться шапка

            cell = row1.createCell(0, CellType.STRING);
            cell.setCellValue(CONTRACT_NUMBER_COLUMN_HEADER);

            cell = row1.createCell(1,CellType.STRING);
            cell.setCellValue(NUMBER_COLUMN_HEADER);

            cell = row1.createCell(2, CellType.NUMERIC);
            cell.setCellValue(DEBIT_COLUMN_HEADER);

            cell = row1.createCell(3, CellType.NUMERIC);
            cell.setCellValue(CREDIT_COLUMN_HEADER);

            cell = row1.createCell(4);
            cell.setCellValue(DATA_COLUMN_HEADER);

            cell = row1.createCell(5);
            cell.setCellValue(COMENTS_COLUMN_HEADER);

            cell = row1.createCell(6, CellType.NUMERIC);
            cell.setCellValue(SOM_COLUMN_HEADER);

            cell = row1.createCell(7, CellType.NUMERIC);
            cell.setCellValue(USD_COLUMN_HEADER); // вот здесь она заканчивается

            for (int i = 0; i < tableView.getItems().size(); i++) {
                spreadsheet.setDefaultColumnWidth(30);
                row = spreadsheet.createRow(i + 1); // Вот здесь я начинаю записывать данные в таблице Excel
                ObservableList<String> currentRow = (ObservableList<String>) tableView.getItems().get(i);
                for (int j = 0; j < currentRow.size(); j++) {
                    row.createCell(j).setCellValue(currentRow.get(j));
                }
            }
            if (!(data == null))
                spreadsheet.setAutoFilter(CellRangeAddress.valueOf("A" + String.valueOf(0) + ":H" + String.valueOf(data.size())));
            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream("Main_Information.xls");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        update.setOnAction(actionEvent -> {
            updateData();
        });

        buildData();


        consolidated.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Consolidated.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent first = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(first));
            stage.setMinWidth(1366);
            stage.setMinHeight(768);
            stage.show();
            updateData();
        });

        Continue.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/EditProgram.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent first = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(first));
            stage.setMinWidth(1250);
            stage.setMinHeight(720);
            stage.show();
        });

    }
    public void buildData() {
        Connection table;
        ResultSet rs;
        table = DatabaseHandler.getDbConnection();
        data = FXCollections.observableArrayList();
        String PostSQL = "SELECT * from " + MainInf.TABLE_OF_INF + " ORDER BY " + MainInf.DEBIT;
        try {
            rs = table.createStatement().executeQuery(PostSQL);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                tableView.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }
            tableView.setItems(data);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    public void updateData() {
        Connection table;
        ResultSet rs;
        table = DatabaseHandler.getDbConnection();
        data = FXCollections.observableArrayList();
        String PostSQL = "SELECT * from " + MainInf.TABLE_OF_INF + " ORDER BY " + MainInf.DEBIT;
        try {
            rs = table.createStatement().executeQuery(PostSQL);
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] updated " + row);
                data.add(row);
            }
            tableView.setItems(data);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

}
