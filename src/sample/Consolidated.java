package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


import java.sql.Connection;
import java.sql.ResultSet;

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

    @FXML void initialize (){
        buildTable();
    }
    private void buildTable (){
        Connection table;
        ResultSet rs;
        table = DatabaseHandler.getDbConnection();
        data = FXCollections.observableArrayList();
        String PostSQL = "SELECT * from " + ConsolidInf.TABLE_OF_SUMMARY;
        try {
            rs = table.createStatement().executeQuery(PostSQL);
            while (rs.next()){
                int first = rs.getInt("code");
                String second = rs.getString("category");
                String second1 = rs.getString("additional_score");
                String third = rs.getString("name_score");
                int fouth = rs.getInt("saldo_in_som");
                int fifth = rs.getInt("debet");
                int sixth = rs.getInt("kredit");
                int seventh = rs.getInt("saldo_out_som");
                int eight = rs.getInt("defference");
                int nine = rs.getInt("saldo_in_usd");
                int ten = rs.getInt("debit_usd");
                int eleven = rs.getInt("credit_usd");
                int twelve = rs.getInt("saldo_out_usd");
                int thirteen = rs.getInt("difference_usd");
                // MainTableInf считайте тотже самый класс что и ProgrammData просто я путаться начал и создал отдельный класс
                ConsolidInfin ConsolidInfTable = new ConsolidInfin(first, second, second1, third, fouth, fifth, sixth, seventh, eight,
                        nine, ten, eleven, twelve, thirteen);

                ConsolidInfTable.getCode();
                ConsolidInfTable.getCategory();
                ConsolidInfTable.getAdittional_score();
                ConsolidInfTable.getName_of_score();
                ConsolidInfTable.getSaldo_in_som();
                ConsolidInfTable.getDebit();
                ConsolidInfTable.getKredit();
                ConsolidInfTable.getSaldo_out_som();
                ConsolidInfTable.getDifference();
                ConsolidInfTable.getSaldo_in_usd();
                ConsolidInfTable.getDebit_usd();
                ConsolidInfTable.getKredit_usd();
                ConsolidInfTable.getSaldo_out_usd();
                ConsolidInfTable.getDifference_usd();
                data.add(ConsolidInfTable);

                Code.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, Integer>("code"));

                Category.setCellValueFactory(new PropertyValueFactory<ConsolidInfin, String>("category"));
                Category.setCellFactory(TextFieldTableCell.forTableColumn());

                Additional_Score.setCellValueFactory( new PropertyValueFactory<ConsolidInfin, String>("adittional_score"));
                Additional_Score.setCellFactory(TextFieldTableCell.forTableColumn());

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
                                ConsolidInfin c  = new ConsolidInfin(a); // и вот таким вль образом я все это кидаю в БД
                                // но указать именно в какую строку он должен это делать я немогу
                                db.consolidUpdateScore(c);
                                buildTable ();
                            }
                        }
                );

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
}
