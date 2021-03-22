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
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

public class ProgrammController {
    @FXML private TableView tableView;
    @FXML private Button Continue;
    private ObservableList data; // незнаю правильно или нет но переменные столбцов и тд решил объявить глобально, что бы код был поменьше
    @FXML private Button consolidated;
    @FXML private Button update;
    @FXML private Button excel;
    @FXML private TableColumn Debit;
    @FXML private TableColumn Credit;
    @FXML private TableColumn Data;
    @FXML private TableColumn Coment;
    @FXML private TableColumn Som;
    @FXML private TableColumn Dollars;
    @FXML private TableColumn ContractNumber;
    @FXML private TableColumn Number;


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
                MainTableInf currentRow = (MainTableInf) tableView.getItems().get(i);
                row = spreadsheet.createRow(i + 1);
                row.createCell(0, CellType.STRING).setCellValue(currentRow.getContract_number());// Вот здесь я начинаю записывать данные в таблице Excel
                row.createCell(1, CellType.STRING).setCellValue(currentRow.getContract());
                row.createCell(2, CellType.NUMERIC).setCellValue(currentRow.getDebit());
                row.createCell(3, CellType.NUMERIC).setCellValue(currentRow.getKredit());
                row.createCell(4, CellType.STRING).setCellValue(currentRow.getDate());
                row.createCell(5, CellType.STRING).setCellValue(currentRow.getComments());
                row.createCell(6, CellType.NUMERIC).setCellValue(currentRow.getSom());
                row.createCell(7, CellType.NUMERIC).setCellValue(currentRow.getUsd());
            }
            if (!(data == null))
                spreadsheet.setAutoFilter(CellRangeAddress.valueOf("A" + 0 + ":H" + data.size()));
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
                assert fileOut != null;
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        update.setOnAction(actionEvent -> buildData());
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
            buildData();
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
        String PostSQL = "SELECT * FROM " + MainInf.TABLE_OF_INF + " ORDER BY debit";
        try {
           rs = table.createStatement().executeQuery(PostSQL);
            while (rs.next()){
                String first = rs.getString("contract_number");
                String second = rs.getString("contract");
                int third = rs.getInt("debit");
                int fouth = rs.getInt("kredit");
                String fifth = rs.getString("coments");
                Date sixth = rs.getDate("date_of");
                int seventh = rs.getInt("som");
                int eght = rs.getInt("usd");
                // MainTableInf считайте тотже самый класс что и ProgrammData просто я путаться начал и создал отдельный класс
                MainTableInf TableColumInf = new MainTableInf(first, second, third, fouth, sixth, fifth, seventh, eght);

                data.add(TableColumInf);

                Debit.setCellValueFactory( new PropertyValueFactory<MainTableInf, Integer>("debit"));

                Credit.setCellValueFactory( new PropertyValueFactory<MainTableInf, Integer>("kredit"));

                Coment.setCellValueFactory( new PropertyValueFactory<MainTableInf, String>("comments"));
                Coment.setCellFactory(TextFieldTableCell.forTableColumn());
                Coment.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MainTableInf, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<MainTableInf, String> mainTableInfStringCellEditEvent) {
                        DatabaseHandler cd = new DatabaseHandler();
                        (mainTableInfStringCellEditEvent.getTableView().getItems().get
                                (mainTableInfStringCellEditEvent.getTablePosition().getRow()))
                                .setContract_number(mainTableInfStringCellEditEvent.getNewValue());
                        String a = String.valueOf(mainTableInfStringCellEditEvent.getNewValue());
                        MainTableInf c  = new MainTableInf(a);
                        c.setDebit((mainTableInfStringCellEditEvent.getTableView().getItems().get(mainTableInfStringCellEditEvent.getTablePosition().getRow())).getDebit());
                        c.setSom((mainTableInfStringCellEditEvent.getTableView().getItems().get(mainTableInfStringCellEditEvent.getTablePosition().getRow())).getSom());
                        c.setUsd((mainTableInfStringCellEditEvent.getTableView().getItems().get(mainTableInfStringCellEditEvent.getTablePosition().getRow())).getUsd());
                        cd.mainTableUpdateComments(c);
                        buildData();
                    }
                });

                Data.setCellValueFactory( new PropertyValueFactory<MainTableInf, Date>("date"));

                Som.setCellValueFactory( new PropertyValueFactory<MainTableInf, Integer>("som"));

                Dollars.setCellValueFactory( new PropertyValueFactory<MainTableInf, Integer>("usd"));

                ContractNumber.setCellValueFactory( new PropertyValueFactory<MainTableInf, String>("contract_number"));
                ContractNumber.setCellFactory(TextFieldTableCell.forTableColumn());
                ContractNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MainTableInf, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<MainTableInf, String> mainTableInfStringCellEditEvent) {
                        DatabaseHandler cd = new DatabaseHandler();
                        (mainTableInfStringCellEditEvent.getTableView().getItems().get
                                (mainTableInfStringCellEditEvent.getTablePosition().getRow()))
                                .setContract_number(mainTableInfStringCellEditEvent.getNewValue());
                        String a = String.valueOf(mainTableInfStringCellEditEvent.getNewValue());
                        MainTableInf c  = new MainTableInf(a);
                        c.setDebit((mainTableInfStringCellEditEvent.getTableView().getItems().get(mainTableInfStringCellEditEvent.getTablePosition().getRow())).getDebit());
                        c.setSom((mainTableInfStringCellEditEvent.getTableView().getItems().get(mainTableInfStringCellEditEvent.getTablePosition().getRow())).getSom());
                        c.setUsd((mainTableInfStringCellEditEvent.getTableView().getItems().get(mainTableInfStringCellEditEvent.getTablePosition().getRow())).getUsd());
                        cd.mainTableUpdateContractNumber(c);
                        buildData();
                    }
                });

                Number.setCellValueFactory( new PropertyValueFactory<MainTableInf, String>("contract"));
                Number.setCellFactory(TextFieldTableCell.forTableColumn());
                Number.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MainTableInf, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<MainTableInf, String> mainTableInfStringCellEditEvent) {
                        DatabaseHandler cd = new DatabaseHandler();
                        (mainTableInfStringCellEditEvent.getTableView().getItems().get
                                (mainTableInfStringCellEditEvent.getTablePosition().getRow()))
                                .setContract_number(mainTableInfStringCellEditEvent.getNewValue());
                        String a = String.valueOf(mainTableInfStringCellEditEvent.getNewValue());
                        MainTableInf c  = new MainTableInf(a);
                        c.setDebit((mainTableInfStringCellEditEvent.getTableView().getItems().get(mainTableInfStringCellEditEvent.getTablePosition().getRow())).getDebit());
                        c.setSom((mainTableInfStringCellEditEvent.getTableView().getItems().get(mainTableInfStringCellEditEvent.getTablePosition().getRow())).getSom());
                        c.setUsd((mainTableInfStringCellEditEvent.getTableView().getItems().get(mainTableInfStringCellEditEvent.getTablePosition().getRow())).getUsd());
                        cd.mainTableUpdateContract(c);
                        buildData();
                    }
                });
            }
            tableView.setItems(data);
            tableView.setEditable(true);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}
