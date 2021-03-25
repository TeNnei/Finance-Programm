package sample;

import java.sql.Date;

public class SubTotalGetterSetter {
    private int code;
    private int debit;
    private int saldo_in_som;
    private int kredit;
    private int saldo_out_som;
    private int debit_usd;
    private int saldo_in_usd;
    private int kredit_usd;
    private int saldo_out_usd;
    private Date todayFrom;
    private Date todayTo;

    public SubTotalGetterSetter(Date todayFrom, Date todayTo) {
        this.todayFrom = todayFrom;
        this.todayTo = todayTo;
    }

    public SubTotalGetterSetter(int code, int saldo_in_som, int debit, int kredit, int saldo_out_som, int saldo_in_usd, int debit_usd, int kredit_usd, int saldo_out_usd) {
        this.code = code;
        this.debit = debit;
        this.saldo_in_som = saldo_in_som;
        this.kredit = kredit;
        this.saldo_out_som = saldo_out_som;
        this.debit_usd = debit_usd;
        this.saldo_in_usd = saldo_in_usd;
        this.kredit_usd = kredit_usd;
        this.saldo_out_usd = saldo_out_usd;
    }

    public Date getTodayTo() {
        return todayTo;
    }
    public Date getTodayFrom() {
        return todayFrom;
    }
    public int getCode() {
        return code;
    }
    public int getDebit() {
        return debit;
    }
    public int getSaldo_in_som() {
        return saldo_in_som;
    }
    public int getKredit() {
        return kredit;
    }
    public int getSaldo_out_som() {
        return saldo_out_som;
    }
    public int getDebit_usd() {
        return debit_usd;
    }
    public int getSaldo_in_usd() {
        return saldo_in_usd;
    }
    public int getKredit_usd() {
        return kredit_usd;
    }
    public int getSaldo_out_usd() {
        return saldo_out_usd;
    }

    public void setTodayTo(Date todayTo) {
        this.todayTo = todayTo;
    }
    public void setToday(Date today) {
        this.todayFrom = today;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setDebit(int debit) {
        this.debit = debit;
    }
    public void setSaldo_in_som(int saldo_in_som) {
        this.saldo_in_som = saldo_in_som;
    }
    public void setKredit(int kredit) {
        this.kredit = kredit;
    }
    public void setSaldo_out_som(int saldo_out_som) {
        this.saldo_out_som = saldo_out_som;
    }
    public void setDebit_usd(int debit_usd) {
        this.debit_usd = debit_usd;
    }
    public void setSaldo_in_usd(int saldo_in_usd) {
        this.saldo_in_usd = saldo_in_usd;
    }
    public void setKredit_usd(int kredit_usd) {
        this.kredit_usd = kredit_usd;
    }
    public void setSaldo_out_usd(int saldo_out_usd) {
        this.saldo_out_usd = saldo_out_usd;
    }
}
