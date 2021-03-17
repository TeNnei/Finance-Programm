package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;


public class ProgramIn {
    @FXML
    private TextField contract_number;
    @FXML
    private TextField debit;
    @FXML
    private TextField kredit;
    @FXML
    private TextField comments;
    @FXML
    private TextField som;
    @FXML
    private Button contin;
    @FXML
    private TextField contract;
    @FXML
    private DatePicker date;
    @FXML
    private TextField usd;

    @FXML
    void initialize() {
        contin.setOnAction(actionEvent -> {
            try {
                registOperation();

            } catch (ParseException e) {
                e.printStackTrace();
            }

            int codeIn = Integer.parseInt(debit.getText());
            checkCodeIn(codeIn);
            updateConsolidInf();
            updateConsolidInf1();
            updateConsoliddebit();
            updateConsolidkredit();
            updateConsolidUpdate();
        });

    }

    public void registOperation() throws ParseException {
        DatabaseHandler db = new DatabaseHandler();
        String contract_number1 = contract_number.getText();
        String contract1 = contract.getText();
        int debit1 = Integer.parseInt(debit.getText());
        int credit1 = Integer.parseInt(kredit.getText());
        Date date1 = Date.valueOf(date.getValue());
        String comment = comments.getText();
        int som1 = Integer.parseInt(som.getText());
        int usd1 = Integer.parseInt(usd.getText());
        ProgramData inf = new ProgramData(contract_number1, contract1, debit1, credit1, date1, comment, som1, usd1);
        db.writeInProgram(inf);
    }

    public void registConsolidatedInf() {
        DatabaseHandler db = new DatabaseHandler();
        int code1 = Integer.parseInt(debit.getText());
        String category1 = " ";
        String adittional_score1 = " ";
        String name_of_score1 = " ";
        int debit1 = 0;
        int saldo_in_som1 = 0;
        int kredit1 = 0;
        int saldo_out_som1 = 0;
        int difference1 = 0;
        int debit_usd1 = 0;
        int saldo_in_usd1 = 0;
        int kredit_usd1 = 0;
        int saldo_out_usd1 = 0;
        int difference_usd1 = 0;
        ConsolidInfin input = new ConsolidInfin(code1, category1, adittional_score1, name_of_score1, debit1,
                saldo_in_som1, kredit1, saldo_out_som1, difference1, debit_usd1, saldo_in_usd1, kredit_usd1, saldo_out_usd1, difference_usd1);
        db.writeInProgram2(input);
    }

    public void checkCodeIn(int code) {
        DatabaseHandler read = new DatabaseHandler();
        ConsolidInfin pr = new ConsolidInfin();
        pr.setCode(code);
        ResultSet first = read.checkCode(pr);
        int fg = 0;
        try {
            while (first.next()) {
                fg++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (fg >= 1) {
            System.out.println("Success!");
        } else {
            registConsolidatedInf();
        }
    }

    public void updateConsolidInf() {
        DatabaseHandler db = new DatabaseHandler();
        int debit2 = Integer.parseInt(debit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdate(inf);
    }

    public void updateConsolidInf1() {
        DatabaseHandler db = new DatabaseHandler();
        int debit2 = Integer.parseInt(kredit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdate(inf);
    }

    public void updateConsoliddebit() {
        DatabaseHandler db = new DatabaseHandler();
        int debit2 = Integer.parseInt(debit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdatedebit(inf);
    }

    public void updateConsolidkredit() {
        DatabaseHandler db = new DatabaseHandler();
        int debit2 = Integer.parseInt(kredit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdatekredit(inf);
    }

    public void updateConsolidUpdate() {
        DatabaseHandler db = new DatabaseHandler();
        int debit2 = Integer.parseInt(debit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdateOut(inf);
    }
}


