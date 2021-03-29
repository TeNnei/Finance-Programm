package sample;

import java.math.BigDecimal;

public class Expences {
    private String category;
    private String name_of_score;
    private BigDecimal difference;

    public Expences(String expens, String code, BigDecimal total) {
        this.category = expens;
        this.name_of_score = code;
        this.difference = total;
    }
    public Expences(String category, String name_of_score) {
        this.category = category;
        this.name_of_score = name_of_score;
    }


    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getName_of_score() {
        return name_of_score;
    }
    public void setName_of_score(String name_of_score) {
        this.name_of_score = name_of_score;
    }
    public BigDecimal  getDifference() {
        return difference;
    }
    public void setDifference(BigDecimal  difference) {
        this.difference = difference;
    }
}
