package sample;

import java.math.BigDecimal;

public class ExpencesUSD {
    private String category;
    private String name_of_score;
    private BigDecimal totalUsd;


    public ExpencesUSD(String category, String name_of_score, BigDecimal differenceUsd) {
        this.category = category;
        this.name_of_score = name_of_score;
        this.totalUsd = differenceUsd;
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

    public BigDecimal getTotalUsd() {
        return totalUsd;
    }

    public void setTotalUsd(BigDecimal differenceUsd) {
        this.totalUsd = differenceUsd;
    }
}
