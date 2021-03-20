package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.text.ParseException;
import java.sql.Date;

public class ProgramIn {
    @FXML private TextField contract_number;
    @FXML private TextField debit;
    @FXML private TextField kredit;
    @FXML private TextField comments;
    @FXML private TextField som;
    @FXML private Button contin;
    @FXML private TextField contract;
    @FXML private DatePicker date;
    @FXML private TextField usd;

    private static DatabaseHandler db = new DatabaseHandler();

    @FXML
    void initialize (){


        contin.setOnAction(actionEvent -> {
            int codeIn = Integer.parseInt(debit.getText());
            checkCodeIn(codeIn);

            try {
                registOperation();

            } catch (ParseException e) {
                e.printStackTrace();
            }

            updateConsolidInf();
            updateConsolidInf1();
            updateConsoliddebit();
            updateConsolidkredit();
            updateConsolidOut();
            updateConsolidOut1();
            updateConsolidDifference();
            updateConsolidDifference1();
            updateConsolidInfUsd();
            updateConsolidInfUsd1();
            updateConsoliddebitUsd();
            updateConsolidkreditUsd();
            updateConsolidOutUsd();
            updateConsolidOutUsd1();
            updateConsolidDifferenceUsd();
            updateConsolidDifferenceUSd1();

        });

    }
    public void registOperation () throws ParseException {
        String contract_number1 = contract_number.getText();
        String contract1 = contract.getText();
        int som1;
        if (som.getText().equals("")){
            som1 = 0;
        }else
        {
            som1 = Integer.parseInt(som.getText());
        }
        int debit1 = Integer.parseInt(debit.getText());
        int credit1 = Integer.parseInt(kredit.getText());
        Date date1 = Date.valueOf(date.getValue());
        String comment = comments.getText();
        int usd1;
        if (usd.getText().equals("")) {
            usd1 = 0;
        }else
        {
            usd1 = Integer.parseInt(usd.getText());
        }
        ProgramData inf = new ProgramData(contract_number1, contract1, debit1, credit1, date1, comment, som1, usd1);
        db.writeInProgram(inf);
    }
    public void registConsolidatedInf(){
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
    public void checkCodeIn (int code){
        DatabaseHandler read = new DatabaseHandler();
        ConsolidInfin pr = new ConsolidInfin();
        pr.setCode(code);
        Boolean first = read.checkCode(pr);
        if(first)
        {
            System.out.println("Success!");
        }
        else{
            registConsolidatedInf();
        }
    }

    public void updateConsolidInfUsd(){
        int debit2 = Integer.parseInt(debit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdateUsd(inf);
    }

    public void updateConsolidInfUsd1(){
        int debit2 = Integer.parseInt(kredit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdateUsd(inf);
    }

    public void updateConsoliddebitUsd(){
        int debit2 = Integer.parseInt(debit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdatedebitUsd(inf);
    }

    public void updateConsolidkreditUsd(){
        int debit2 = Integer.parseInt(kredit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdatekreditUsd(inf);
    }
    public void updateConsolidOutUsd(){
        int debit2 = Integer.parseInt(debit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdateOutUsd(inf);
    }
    public void updateConsolidOutUsd1(){
        int debit2 = Integer.parseInt(kredit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdateOutUsd(inf);
    }
    public void updateConsolidDifferenceUsd(){
        int debit3 = Integer.parseInt(debit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit3);
        db.consolidUpdateDifferenceUsd(inf);
    }
    public void updateConsolidDifferenceUSd1(){
        int debit3 = Integer.parseInt(kredit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit3);
        db.consolidUpdateDifferenceUsd(inf);
    }
    public void updateConsolidInf(){
        int debit2 = Integer.parseInt(debit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdate(inf);
    }

    public void updateConsolidInf1(){
        int debit2 = Integer.parseInt(kredit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdate(inf);
    }

    public void updateConsoliddebit(){
        int debit2 = Integer.parseInt(debit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdatedebit(inf);
    }

    public void updateConsolidkredit(){
        int debit2 = Integer.parseInt(kredit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdatekredit(inf);
    }
    public void updateConsolidOut(){
        int debit2 = Integer.parseInt(debit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdateOut(inf);
    }
    public void updateConsolidOut1(){
        int debit2 = Integer.parseInt(kredit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit2);
        db.consolidUpdateOut(inf);
    }
    public void updateConsolidDifference(){
        int debit3 = Integer.parseInt(debit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit3);
        db.consolidUpdateDifference(inf);
    }
    public void updateConsolidDifference1(){
        int debit3 = Integer.parseInt(kredit.getText());
        ConsolidInfin inf = new ConsolidInfin(debit3);
        db.consolidUpdateDifference(inf);
    }
}
