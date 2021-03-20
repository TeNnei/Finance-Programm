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

//    public void setKredit(int kredit) {
//        this.kredit = kredit;
//    }
//    public void setСontract(String сontract) {
//       this.contract = contract;
//    }
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//    public void setContract_number(String contract_number) {
//        this.contract_number = contract_number;
//    }
//    public void setDate(Date date) {
//        this.date = date;
//    }
//    public void setDebit(int debit) {
//        this.debit = debit;
//    }
//    public void setSom1(int som) {
//        this.som = som;
//    }
//    public void setUsd(int usd) {
//        this.usd = usd;
//    }

}
