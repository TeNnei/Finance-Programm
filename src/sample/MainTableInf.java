package sample;
import java.sql.Date;
public  class MainTableInf {

    private int som;
    private int usd;
    private int debit;
    private int kredit;
    private String contract;
    private String contract_number;
    private String comments;
    private Date date;

    public MainTableInf(String contract_number, String contract, int debit, int kredit, Date data, String comments, int som, int usd) {
        this.contract_number = contract_number;
        this.contract = contract;
        this.debit = debit;
        this.kredit = kredit;
        this.comments = comments;
        this.date = data;
        this.som = som;
        this.usd = usd;
    }
    public MainTableInf(){
    }

    public int getSom(int som) {
        return this.som;
    }
    public int getUsd(int usd) {
        return this.usd;
    }
    public int getDebit(int debit) {
        return this.debit ;
    }
    public int getKredit(int kredit) {
        return this.kredit;
    }
    public String getContract(String contract) {
        return this.contract;
    }
    public String getContract_number(String contract_number) {
        return this.contract_number;
    }
    public String getComments(String coments) {
        return comments;
    }
    public Date getDate(Date date_of) {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }
    public void setSom1(int som) {
        this.som = som;
    }
    public void setUsd(int usd) {
        this.usd = usd;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public void setContract_number(String contract_number) {
        this.contract_number = contract_number;
    }
    public void setContract1(String contract) {
        this.contract = contract;
    }
    public void setKredit(int kredit) {
        this.kredit = kredit;
    }
    public void setDebit(int debit) {
        this.debit = debit;
    }

    public String getContract() { return contract;
    }
    public int getKredit() {
        return kredit;
    }
    public int getDebit() {
        return debit;
    }
    public Date getDate() {
        return date;
    }
    public int getSom1() {
        return som;
    }
    public int getUsd() {
        return usd;
    }
    public String getComments() {
        return comments;
    }
    public String getContract_number() {
        return contract_number;
    }
}

