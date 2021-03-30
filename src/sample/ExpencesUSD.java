package sample;

import java.math.BigDecimal;

public class ExpencesUSD {
    private String category;
    private String name_of_score;
    private BigDecimal differenceUsd;


    public ExpencesUSD(String category, String name_of_score, BigDecimal differenceUsd) {
        this.category = category;
        this.name_of_score = name_of_score;
        this.differenceUsd = differenceUsd;
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

    public BigDecimal getDifferenceUsd() {
        return differenceUsd;
    }

    public void setDifferenceUsd(BigDecimal differenceUsd) {
        this.differenceUsd = differenceUsd;
    }
}
