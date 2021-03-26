package sample;

import java.math.BigDecimal;
import java.util.Date;
// Новый класс решил делать не через пустой конструктор
public class MainTableInf {
    private BigDecimal som;
    private BigDecimal usd;
    private String debit;
    private String kredit;
    private String contract;
    private String contract_number;
    private String comments;
    private Date date;

    public MainTableInf(String inf){
        this.contract_number = inf;
        this.comments = inf;
        this.contract = inf;
    }

    public MainTableInf(String contract_number, String contract, String debit, String kredit, Date date, String comments, BigDecimal som, BigDecimal usd) {
        this.som = som;
        this.usd = usd;
        this.debit = debit;
        this.kredit = kredit;
        this.contract = contract;
        this.contract_number = contract_number;
        this.comments = comments;
        this.date = date;
    }

    public BigDecimal getSom() {
        return som;
    }
    public void setSom(BigDecimal som) {
        this.som = som;
    }
    public BigDecimal getUsd() {
        return usd;
    }
    public void setUsd(BigDecimal usd) {
        this.usd = usd;
    }
    public String getDebit() {
        return debit;
    }
    public void setDebit(String debit) {
        this.debit = debit;
    }
    public String getKredit() {
        return kredit;
    }
    public void setKredit(String kredit) {
        this.kredit = kredit;
    }
    public String getContract() {
        return contract;
    }
    public void setContract1(String contract) {
        this.contract = contract;
    }
    public String getContract_number() {
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