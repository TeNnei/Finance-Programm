package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consolidated {

    @FXML private TableView ConsolidatedTable;
    private ObservableList data;

    @FXML private TableColumn Code;
    @FXML private TableColumn Category;
    @FXML private TableColumn Additional_Score;
    @FXML private TableColumn Score;
    @FXML private TableColumn SaldoInSom;
    @FXML private TableColumn DebitSom;
    @FXML private TableColumn CreditSom;
    @FXML private TableColumn SaldoOutSom;
    @FXML private TableColumn Difference;
    @FXML private TableColumn SaldoInUsd;
    @FXML private TableColumn DebitUsd;
    @FXML private TableColumn CreditUsd;
    @FXML private TableColumn SaldoOutUsd;
    @FXML private TableColumn DifferenceUsd;
    @FXML private Button excel;
    @FXML private DatePicker From;
    @FXML private DatePicker To;
    @FXML private Button back;
    @FXML private Button total_button;

    private static final String CODE = "Код";
    private static final String CATEGORY = "Категория";
    private static final String ADDITIONAL_SCORE = "Дополнительный счет";
    private static final String NAME_OF_SCORE = "Счет";
    private static final String SALDO_IN_SOM = "Сальдо вход сумм";
    private static final String DEBIT = "Дебит";
    private static final String CREDIT = "Кредит";
    private static final String SALDO_OUT_SOM = "Сальдо выход сумм";
    private static final String DIFFERENCE = "Разница";
    private static final String SALDO_IN_USD = "Сальдо вход USD";
    private static final String DEBIT_USD = "Дебит USD";
    private static final String CREDIT_USD = "Кредит USD";
    private static final String SALDO_OUT_USD = "Cальдо выход USD";
    private static final String DIFFERENCE_USD = "Разница USD";



    @FXML void initialize (){
        buildTable();

        total_button.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/TotalView.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent first = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(first));
            stage.show();
        });

        back.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Programm.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent first = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(first));
            stage.show();
            back.getScene().getWindow().hide();
        });

        To.setOnAction(actionEvent -> {
            try {
                DateSet();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            buildTable();
        });

        excel.setOnAction(actionEvent -> {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet spreadsheet = workbook.createSheet("Main_Information");
            HSSFRow row;
            HSSFRow row1;
            HSSFCell cell;

            row1 = spreadsheet.createRow(0);
            cell = row1.createCell(0, CellType.STRING);
            cell.setCellValue(CODE);
            cell = row1.createCell(1,CellType.STRING);
            cell.setCellValue(CATEGORY);
            cell = row1.createCell(2, CellType.STRING);
            cell.setCellValue(ADDITIONAL_SCORE);
            cell = row1.createCell(3, CellType.STRING);
            cell.setCellValue(NAME_OF_SCORE);
            cell = row1.createCell(4, CellType.NUMERIC);
            cell.setCellValue(SALDO_IN_SOM);
            cell = row1.createCell(5, CellType.NUMERIC);
            cell.setCellValue(DEBIT);
            cell = row1.createCell(6, CellType.NUMERIC);
            cell.setCellValue(CREDIT);
            cell = row1.createCell(7, CellType.NUMERIC);
            cell.setCellValue(SALDO_OUT_SOM);
            cell = row1.createCell(8, CellType.NUMERIC);
            cell.setCellValue(DIFFERENCE);
            cell = row1.createCell(9, CellType.NUMERIC);
            cell.setCellValue(SALDO_IN_USD);
            cell = row1.createCell(10, CellType.NUMERIC);
            cell.setCellValue(DEBIT_USD);
            cell = row1.createCell(11, CellType.NUMERIC);
            cell.setCellValue(CREDIT_USD);
            cell = row1.createCell(12, CellType.NUMERIC);
            cell.setCellValue(SALDO_OUT_USD);
            cell = row1.createCell(13, CellType.NUMERIC);
            cell.setCellValue(DIFFERENCE_USD);

            for (int i = 0; i < ConsolidatedTable.getItems().size(); i++) {
                spreadsheet.setDefaultColumnWidth(20);
                ConsolidInfin currentRow = (ConsolidInfin) ConsolidatedTable.getItems().get(i);
                row = spreadsheet.createRow(i + 1);
                row.createCell(0, CellType.STRING).setCellValue(currentRow.getCode());// Вот здесь я начинаю записывать данные в таблице Excel
                row.createCell(1, CellType.STRING).setCellValue(currentRow.getCategory());
                row.createCell(2, CellType.STRING).setCellValue(currentRow.getAdittional_score());
                row.createCell(3, CellType.STRING).setCellValue(currentRow.getName_of_score());
                row.createCell(4, CellType.NUMERIC).setCellValue(Double.valueOf(String.valueOf(currentRow.getSaldo_in_som())));
                row.createCell(5, CellType.NUMERIC).setCellValue(Double.valueOf(String.valueOf(currentRow.getDebit())));
                row.createCell(6, CellType.NUMERIC).setCellValue(Double.valueOf(String.valueOf(currentRow.getKredit())));
                row.createCell(7, CellType.NUMERIC).setCellValue(Double.valueOf(String.valueOf(currentRow.getSaldo_in_usd())));
                row.createCell(8, CellType.NUMERIC).setCellValue(Double.valueOf(String.valueOf(currentRow.getDifference())));
                row.createCell(9, CellType.NUMERIC).setCellValue(Double.valueOf(String.valueOf(currentRow.getSaldo_in_usd())));
                row.createCell(10, CellType.NUMERIC).setCellValue(Double.valueOf(String.valueOf(currentRow.getDebit_usd())));
                row.createCell(11, CellType.NUMERIC).setCellValue(Double.valueOf(String.valueOf(currentRow.getKredit_usd())));
                row.createCell(12, CellType.NUMERIC).setCellValue(Double.valueOf(String.valueOf(currentRow.getSaldo_out_usd())));
                row.createCell(13, CellType.NUMERIC).setCellValue(Double.valueOf(String.valueOf(currentRow.getDifference_usd())));
            }
            if (!(data == null))
                spreadsheet.setAutoFilter(CellRangeAddress.valueOf("A" + 0 + ":N" + data.size()));
            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream("ConsolidInformation.xls");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert fileOut != null;
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void buildTable (){
        Connection table;
        ResultSet rs;
        table = DatabaseHandler.getDbConnection();
        data = FXCollections.observableArrayList();
        String PostSQL = "SELECT * from " + ConsolidInf.TABLE_OF_SUMMARY + " ORDER BY code";
        try {
            rs = table.createStatement().executeQuery(PostSQL);
            while (rs.next()){
                String first = rs.getString("code");
                String second = rs.getString("category");
                String second1 = rs.getString("additional_score");
                String third = rs.getString("name_score");
                BigDecimal fouth = rs.getBigDecimal("saldo_in_som");
                BigDecimal fifth = rs.getBigDecimal("debet");
                BigDecimal sixth = rs.getBigDecimal("kredit");
                BigDecimal seventh = rs.getBigDecimal("saldo_out_som");
                BigDecimal eight = rs.getBigDecimal("defference");
                BigDecimal nine = rs.getBigDecimal("saldo_in_usd");
                BigDecimal ten = rs.getBigDecimal("debit_usd");
                BigDecimal eleven = rs.getBigDecimal("credit_usd");
                BigDecimal twelve = rs.getBigDecimal("saldo_out_usd");
                BigDecimal thirteen = rs.getBigDecimal("difference_usd");
                ConsolidInfin ConsolidInfTable = new ConsolidInfin(first, second, second1, third, fouth, fifth, sixth, seventh, eight,
                        nine, ten, eleven, twelve, thirteen);

                data.add(ConsolidInfTable);

                Code.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, Integer>("code"));

                Category.setCellValueFactory(new PropertyValueFactory<ConsolidInfin, String>("category"));
                Category.setCellFactory(TextFieldTableCell.forTableColumn());
                Category.setOnEditCommit(
                        new EventHandler<TableColumn.CellEditEvent<ConsolidInfin, String>>() {
                            @Override
                            public void handle(TableColumn.CellEditEvent<ConsolidInfin, String> consolidInfinStringCellEditEvent) {
                                DatabaseHandler db = new DatabaseHandler();
                                // вот это шляпа считывает данные из редактируемой ячейки
                                (consolidInfinStringCellEditEvent.getTableView().getItems().get
                                        (consolidInfinStringCellEditEvent.getTablePosition().getRow()))
                                        .setName_of_score(consolidInfinStringCellEditEvent.getNewValue());
                                String a = String.valueOf(consolidInfinStringCellEditEvent.getNewValue());
                                ConsolidInfin c  = new ConsolidInfin(a);
                                c.setCode(String.valueOf(Integer.parseInt((consolidInfinStringCellEditEvent.getTableView().getItems().get
                                        (consolidInfinStringCellEditEvent.getTablePosition().getRow())).getCode())));
                                db.consolidUpdateCategory(c);
                                buildTable ();
                            }
                        }
                );

                Additional_Score.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, String>("adittional_score"));
                Additional_Score.setCellFactory(TextFieldTableCell.forTableColumn());
                Additional_Score.setOnEditCommit(
                        new EventHandler<TableColumn.CellEditEvent<ConsolidInfin, String>>() {
                            @Override
                            public void handle(TableColumn.CellEditEvent<ConsolidInfin, String> consolidInfinStringCellEditEvent) {
                                DatabaseHandler db = new DatabaseHandler();
                                // вот это шляпа считывает данные из редактируемой ячейки
                                (consolidInfinStringCellEditEvent.getTableView().getItems().get
                                        (consolidInfinStringCellEditEvent.getTablePosition().getRow()))
                                        .setName_of_score(consolidInfinStringCellEditEvent.getNewValue());
                                String a = String.valueOf(consolidInfinStringCellEditEvent.getNewValue());
                                ConsolidInfin c  = new ConsolidInfin(a);
                                c.setCode(String.valueOf(Integer.parseInt((consolidInfinStringCellEditEvent.getTableView().getItems().get
                                        (consolidInfinStringCellEditEvent.getTablePosition().getRow())).getCode())));
                                db.consolidUpdateAdditionalScore(c);
                                buildTable ();
                            }
                        }
                );

                Score.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, String>("name_of_score"));
                Score.setCellFactory(TextFieldTableCell.forTableColumn());
                Score.setOnEditCommit(
                        new EventHandler<TableColumn.CellEditEvent<ConsolidInfin, String>>() {
                            @Override
                            public void handle(TableColumn.CellEditEvent<ConsolidInfin, String> consolidInfinStringCellEditEvent) {
                                DatabaseHandler db = new DatabaseHandler();
                                // вот это шляпа считывает данные из редактируемой ячейки
                                (consolidInfinStringCellEditEvent.getTableView().getItems().get
                                        (consolidInfinStringCellEditEvent.getTablePosition().getRow()))
                                        .setName_of_score(consolidInfinStringCellEditEvent.getNewValue());
                                String a = String.valueOf(consolidInfinStringCellEditEvent.getNewValue());
                                ConsolidInfin c  = new ConsolidInfin(a);
                                c.setCode(String.valueOf(Integer.parseInt((consolidInfinStringCellEditEvent.getTableView().getItems().get
                                        (consolidInfinStringCellEditEvent.getTablePosition().getRow())).getCode())));
                                db.consolidUpdateScore(c);
                                buildTable ();
                            }
                        }
                );
                Code.setCellValueFactory(new PropertyValueFactory<ConsolidInfin, Integer>("code"));
                Category.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, String>("category"));
                Additional_Score.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, String>("adittional_score"));
                Score.setCellValueFactory(new PropertyValueFactory<ConsolidInfin, String>("name_of_score"));
                SaldoInSom.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, Integer>("saldo_in_som"));
                DebitSom.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, Integer>("debit"));
                CreditSom.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, Integer>("kredit"));
                SaldoOutSom.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, Integer>("saldo_out_som"));
                Difference.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, Integer>("difference"));
                SaldoInUsd.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, Integer>("saldo_in_usd"));
                DebitUsd.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, Integer>("debit_usd"));
                CreditUsd.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, Integer>("kredit_usd"));
                SaldoOutUsd.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, Integer>("saldo_out_usd"));
                DifferenceUsd.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, Integer>("difference_usd"));
            }
            ConsolidatedTable.setItems(data);
            ConsolidatedTable.setEditable(true);
            ConsolidatedTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    private void DateSet() throws SQLException {
        DatabaseHandler dateSet = new DatabaseHandler();
        Date From1 = Date.valueOf(From.getValue());
        Date To2 = Date.valueOf(To.getValue());
        SubTotalGetterSetter a = new SubTotalGetterSetter(From1, To2);
        dateSet.consolidinfDate(a);
        dateSet.consolidinfDateCredit(a);
        dateSet.consolidinfDateSaldoInSom(a);
        dateSet.consolidinfDateSaldoInUsd(a);
        dateSet.consolidinfDateDebitUsd(a);
        dateSet.consolidinfDateCreditUsd(a);
    }
}
