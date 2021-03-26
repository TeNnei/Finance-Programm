package sample;

import java.math.BigDecimal;
import java.sql.Date;

public class SubTotalGetterSetter {
    private String code;
    private BigDecimal debit;
    private BigDecimal saldo_in_som;
    private BigDecimal kredit;
    private BigDecimal saldo_out_som;
    private BigDecimal debit_usd;
    private BigDecimal saldo_in_usd;
    private BigDecimal kredit_usd;
    private BigDecimal saldo_out_usd;
    private Date todayFrom;
    private Date todayTo;

    public SubTotalGetterSetter(Date todayFrom, Date todayTo) {
        this.todayFrom = todayFrom;
        this.todayTo = todayTo;
    }

    public SubTotalGetterSetter(String code, BigDecimal saldo_in_som, BigDecimal debit, BigDecimal kredit, BigDecimal saldo_out_som, BigDecimal saldo_in_usd, BigDecimal debit_usd, BigDecimal kredit_usd, BigDecimal saldo_out_usd) {
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
    public String getCode() {
        return code;
    }
    public BigDecimal getDebit() {
        return debit;
    }
    public BigDecimal getSaldo_in_som() {
        return saldo_in_som;
    }
    public BigDecimal getKredit() {
        return kredit;
    }
    public BigDecimal getSaldo_out_som() {
        return saldo_out_som;
    }
    public BigDecimal getDebit_usd() {
        return debit_usd;
    }
    public BigDecimal getSaldo_in_usd() {
        return saldo_in_usd;
    }
    public BigDecimal  getKredit_usd() {
        return kredit_usd;
    }
    public BigDecimal getSaldo_out_usd() {
        return saldo_out_usd;
    }

    public void setTodayTo(Date todayTo) {
        this.todayTo = todayTo;
    }
    public void setToday(Date today) {
        this.todayFrom = today;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }
    public void setSaldo_in_som(BigDecimal saldo_in_som) {
        this.saldo_in_som = saldo_in_som;
    }
    public void setKredit(BigDecimal kredit) {
        this.kredit = kredit;
    }
    public void setSaldo_out_som(BigDecimal saldo_out_som) {
        this.saldo_out_som = saldo_out_som;
    }
    public void setDebit_usd(BigDecimal debit_usd) {
        this.debit_usd = debit_usd;
    }
    public void setSaldo_in_usd(BigDecimal saldo_in_usd) {
        this.saldo_in_usd = saldo_in_usd;
    }
    public void setKredit_usd(BigDecimal kredit_usd) {
        this.kredit_usd = kredit_usd;
    }
    public void setSaldo_out_usd(BigDecimal saldo_out_usd) {
        this.saldo_out_usd = saldo_out_usd;
    }
}
