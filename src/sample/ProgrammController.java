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

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class ProgrammController {

    @FXML private TableView<ObservableList> tableView;
    @FXML private Button Continue;
    private ObservableList<ObservableList> data;
    @FXML private Button consolidated;

    @FXML void initialize(){
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
