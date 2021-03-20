package sample;

import java.util.Date;

public class MainTableInf {
    private int som;
    private int usd;
    private int debit;
    private int kredit;
    private String contract;
    private String contract_number;
    private String comments;
    private Date date;

    public MainTableInf(String contract_number, String contract, int debit, int kredit, Date date, String comments, int som, int usd) {
        this.som = som;
        this.usd = usd;
        this.debit = debit;
        this.kredit = kredit;
        this.contract = contract;
        this.contract_number = contract_number;
        this.comments = comments;
        this.date = date;
    }

    public int getSom1() {
        return som;
    }
    public void setSom1(int som) {
        this.som = som;
    }
    public int getUsd() {
        return usd;
    }
    public void setUsd(int usd) {
        this.usd = usd;
    }
    public int getDebit1() {
        return debit;
    }
    public void setDebit(int debit) {
        this.debit = debit;
    }
    public int getKredit1() {
        return kredit;
    }
    public void setKredit(int kredit) {
        this.kredit = kredit;
    }
    public String getContract1() {
        return contract;
    }
    public void setContract1(String contract) {
        this.contract = contract;
    }
    public String getContract_number1() {
        return contract_number;
    }
    public void setContract_number(String contract_number) {
        this.contract_number = contract_number;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}