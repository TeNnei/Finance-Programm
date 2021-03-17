package sample;

public class ConsolidInfin {
   private int code;
   private String category;
   private String adittional_score;
   private String name_of_score;
   private int debit;
   private int saldo_in_som;
   private int kredit;
   private int saldo_out_som;
   private int difference;
   private int debit_usd;
   private int saldo_in_usd;
   private int kredit_usd;
   private int saldo_out_usd;
   private int difference_usd;

   public ConsolidInfin(int code,  String adittional_score, String category, String name_of_score, int debit, int saldo_in_som, int kredit, int saldo_out_som, int difference, int debit_usd, int saldo_in_usd , int kredit_usd,
                             int saldo_out_usd, int difference_usd)

   {
       this.code = code;
       this.category = category;
       this.adittional_score = adittional_score;
       this.name_of_score = name_of_score;
       this.debit = debit;
       this.saldo_in_som = saldo_in_som;
       this.kredit = kredit;
       this.saldo_in_som = saldo_out_som;
       this.difference = difference;
       this.debit_usd = debit_usd;
       this.saldo_in_usd = saldo_in_usd;
       this.kredit_usd = kredit_usd;
       this.saldo_out_usd = saldo_out_usd;
       this.difference_usd = difference_usd;
   }

    public ConsolidInfin() {

    }

    public ConsolidInfin(int debit){
       this.debit = debit;
       this.kredit = debit;
       this.debit_usd = debit;
       this.kredit_usd = debit;
    }

    public int getDebit() {
        return debit;
    }
    public int getDebit_usd() {
        return debit_usd;
    }
    public int getDifference() {
        return difference;
    }
    public int getDifference_usd() {
        return difference_usd;
    }
    public int getKredit() {
        return kredit;
    }
    public int getKredit_usd() {
        return kredit_usd;
    }
    public int getSaldo_in_som() {
        return saldo_in_som;
    }
    public int getSaldo_in_usd() {
        return saldo_in_usd;
    }
    public int getSaldo_out_som() {
        return saldo_out_som;
    }
    public int getSaldo_out_usd() {
        return saldo_out_usd;
    }
    public String getAdittional_score() {
        return adittional_score;
    }
    public String getCategory() {
        return category;
    }
    public int getCode() {
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
    public void setCode(int code) {
        this.code = code;
    }
    public void setDebit(int debit) {
        this.debit = debit;
    }
    public void setDebit_usd(int debit_usd) {
        this.debit_usd = debit_usd;
    }
    public void setDifference(int difference) {
        this.difference = difference;
    }
    public void setDifference_usd(int difference_usd) {
        this.difference_usd = difference_usd;
    }
    public void setKredit(int kredit) {
        this.kredit = kredit;
    }
    public void setKredit_usd(int kredit_usd) {
        this.kredit_usd = kredit_usd;
    }
    public void setName_of_score(String name_of_score) {
        this.name_of_score = name_of_score;
    }
    public void setSaldo_in_som(int saldo_in_som) {
        this.saldo_in_som = saldo_in_som;
    }
    public void setSaldo_in_usd(int saldo_in_usd) {
        this.saldo_in_usd = saldo_in_usd;
    }
    public void setSaldo_out_som(int saldo_out_som) {
        this.saldo_out_som = saldo_out_som;
    }
    public void setSaldo_out_usd(int saldo_out_usd) {
        this.saldo_out_usd = saldo_out_usd;
    }
}

