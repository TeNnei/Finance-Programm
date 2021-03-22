package sample;
import java.sql.Date;

public class ProgramData {
    private int som;
    private int usd;
    private int debit;
    private int kredit;
    private String contract;
    private String contract_number;
    private String comments;
    private Date date;
    private Date date2;

    public ProgramData(Date From, Date To){
        this.date = From;
        this.date2 = To;
    }

public ProgramData(String contract_number, String contract, int debit, int kredit, Date data, String comments, int som, int usd){
this.contract_number = contract_number;
this.contract = contract;
this.debit = debit;
this.kredit = kredit;
this.comments = comments;
this.date = data;
this.som = som;
this.usd = usd;
}
    public int getDebit() {
        return debit;
    }
    public String getComments() {
        return comments;
    }
    public String getContract_number() {
        return contract_number;
    }
    public String getContract() {
        return contract;
    }
    public int getSom() {
        return som;
    }
    public int getKredit() {
        return kredit;
    }
    public Date getDate() {
        return date;
    }
    public int getUsd() {
        return usd;
    }
    public Date getDate2() {
        return date2;
    }
}
