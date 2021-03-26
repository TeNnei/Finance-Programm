package sample;

import java.math.BigDecimal;

public class ConsolidInfin {
   private String code;
   private String category;
   private String adittional_score;
   private String name_of_score;
   private BigDecimal debit;
   private BigDecimal saldo_in_som;
   private BigDecimal kredit;
   private BigDecimal saldo_out_som;
   private BigDecimal difference;
   private BigDecimal debit_usd;
   private BigDecimal saldo_in_usd;
   private BigDecimal kredit_usd;
   private BigDecimal saldo_out_usd;
   private BigDecimal difference_usd;

   public ConsolidInfin(String code,  String category, String adittional_score, String name_of_score, BigDecimal saldo_in_som, BigDecimal debit, BigDecimal kredit, BigDecimal saldo_out_som, BigDecimal difference, BigDecimal saldo_in_usd, BigDecimal debit_usd , BigDecimal kredit_usd,
                        BigDecimal saldo_out_usd, BigDecimal difference_usd)

   {
       this.code = code;
       this.category = category;
       this.adittional_score = adittional_score;
       this.name_of_score = name_of_score;
       this.debit = debit;
       this.saldo_in_som = saldo_in_som;
       this.kredit = kredit;
       this.saldo_out_som = saldo_out_som;
       this.difference = difference;
       this.debit_usd = debit_usd;
       this.saldo_in_usd = saldo_in_usd;
       this.kredit_usd = kredit_usd;
       this.saldo_out_usd = saldo_out_usd;
       this.difference_usd = difference_usd;
   }

   public ConsolidInfin(){

   }

    public ConsolidInfin(String score) {
       this.adittional_score = score;
       this.name_of_score = score;
       this.category = score;
    }

    public ConsolidInfin(BigDecimal debit){
       this.debit = debit;
       this.kredit = debit;
       this.debit_usd = debit;
       this.kredit_usd = debit;
    }

    public BigDecimal getDebit() {
        return debit;
    }
    public BigDecimal getDebit_usd() {
        return debit_usd;
    }
    public BigDecimal getDifference() {
        return difference;
    }
    public BigDecimal getDifference_usd() {
        return difference_usd;
    }
    public BigDecimal getKredit() {
        return kredit;
    }
    public BigDecimal getKredit_usd() {
        return kredit_usd;
    }
    public BigDecimal getSaldo_in_som() {
        return saldo_in_som;
    }
    public BigDecimal getSaldo_in_usd() {
        return saldo_in_usd;
    }
    public BigDecimal getSaldo_out_som() {
        return saldo_out_som;
    }
    public BigDecimal getSaldo_out_usd() {
        return saldo_out_usd;
    }
    public String getAdittional_score() {
        return adittional_score;
    }
    public String getCategory() {
        return category;
    }
    public String getCode() {
        return code;
    }
    public String getName_of_score() {
        return name_of_score;
    }

    public void setAdittional_score(String adittional_score) {
        this.adittional_score = adittional_score;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }
    public void setDebit_usd(BigDecimal debit_usd) {
        this.debit_usd = debit_usd;
    }
    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }
    public void setDifference_usd(BigDecimal difference_usd) {
        this.difference_usd = difference_usd;
    }
    public void setKredit(BigDecimal kredit) {
        this.kredit = kredit;
    }
    public void setKredit_usd(BigDecimal kredit_usd) {
        this.kredit_usd = kredit_usd;
    }
    public void setName_of_score(String name_of_score) {
        this.name_of_score = name_of_score;
    }
    public void setSaldo_in_som(BigDecimal saldo_in_som) {
        this.saldo_in_som = saldo_in_som;
    }
    public void setSaldo_in_usd(BigDecimal saldo_in_usd) {
        this.saldo_in_usd = saldo_in_usd;
    }
    public void setSaldo_out_som(BigDecimal saldo_out_som) {
        this.saldo_out_som = saldo_out_som;
    }
    public void setSaldo_out_usd(BigDecimal saldo_out_usd) {
        this.saldo_out_usd = saldo_out_usd;
    }

}

