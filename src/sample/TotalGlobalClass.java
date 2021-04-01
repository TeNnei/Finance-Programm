package sample;

import java.math.BigDecimal;

public class TotalGlobalClass {
    private String category;
    private String name_of_score;
    private BigDecimal saldo_in_som;
    private BigDecimal saldo_out_som;
    private BigDecimal differenceSom;
    private BigDecimal saldo_in_usd;
    private BigDecimal saldo_out_usd;
    private BigDecimal differenceUsd;

    public TotalGlobalClass() {
    }

    public TotalGlobalClass(String category, String name_of_score, BigDecimal saldo_in_som, BigDecimal saldo_out_som, BigDecimal differenceSom, BigDecimal saldo_in_usd, BigDecimal saldo_out_usd, BigDecimal differenceUsd) {
        this.name_of_score = name_of_score;
        this.saldo_in_som = saldo_in_som;
        this.saldo_out_som = saldo_out_som;
        this.differenceSom = differenceSom;
        this.saldo_in_usd = saldo_in_usd;
        this.saldo_out_usd = saldo_out_usd;
        this.differenceUsd = differenceUsd;
    }

    public String getName_of_score() {
        return name_of_score;
    }
    public BigDecimal getSaldo_in_som() {
        return saldo_in_som;
    }
    public BigDecimal getSaldo_out_som() {
        return saldo_out_som;
    }
    public BigDecimal getDifferenceSom() {
        return differenceSom;
    }
    public BigDecimal getSaldo_in_usd() {
        return saldo_in_usd;
    }
    public BigDecimal getSaldo_out_usd() {
        return saldo_out_usd;
    }
    public BigDecimal getDifferenceUsd() {
        return differenceUsd;
    }

    public void setName_of_score(String name_of_score) {
        this.name_of_score = name_of_score;
    }
    public void setSaldo_in_som(BigDecimal saldo_in_som) {
        this.saldo_in_som = saldo_in_som;
    }
    public void setSaldo_out_som(BigDecimal saldo_out_som) {
        this.saldo_out_som = saldo_out_som;
    }
    public void setDifferenceSom(BigDecimal differenceSom) {
        this.differenceSom = differenceSom;
    }
    public void setSaldo_in_usd(BigDecimal saldo_in_usd) {
        this.saldo_in_usd = saldo_in_usd;
    }
    public void setSaldo_out_usd(BigDecimal saldo_out_usd) {
        this.saldo_out_usd = saldo_out_usd;
    }
    public void setDifferenceUsd(BigDecimal differenceUsd) {
        this.differenceUsd = differenceUsd;
    }
}
