package sample;
import java.math.BigDecimal;
import java.sql.Date;

public class ProgramData {
    private BigDecimal som;
    private BigDecimal usd;
    private String debit;
    private String kredit;
    private String contract;
    private String contract_number;
    private String comments;
    private Date date;
    private Date date2;

    public ProgramData(Date From, Date To){
        this.date = From;
        this.date2 = To;
    }

    public ProgramData(String contract_number, String contract, String debit, String kredit, Date data, String comments, BigDecimal som, BigDecimal usd){
        this.contract_number = contract_number;
        this.contract = contract;
        this.debit = debit;
        this.kredit = kredit;
        this.comments = comments;
        this.date = data;
        this.som = som;
        this.usd = usd;
}

    public String getDebit() {
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
    public BigDecimal getSom() {
        return som;
    }
    public String getKredit() {
        return kredit;
    }
    public BigDecimal getUsd() {
        return usd;
    }
    public Date getDate() {
        return date;
    }
}
