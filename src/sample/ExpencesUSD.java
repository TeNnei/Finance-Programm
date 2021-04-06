package sample;

import java.math.BigDecimal;
import java.util.Map;

public class ExpencesUSD{
    private String treeLevel;
    private String category;
    private String additional_score;
    private String name_of_score;
    private BigDecimal totalUsd;
    public Map<String, ExpencesUSD> child;

    public ExpencesUSD(String name_of_score, BigDecimal totalUsd) {
        this.name_of_score = name_of_score;
        this.totalUsd = totalUsd;
    }

    public ExpencesUSD(String additional_score, String name_of_score) {
        this.additional_score = additional_score;
        this.name_of_score = name_of_score;
    }

    public ExpencesUSD(String category, String additional_score, String name_of_score) {
        this.category = category;
        this.additional_score = additional_score;
        this.name_of_score = name_of_score;
    }

    public ExpencesUSD(String category, String name_of_score, BigDecimal differenceUsd) {
        this.category = category;
        this.name_of_score = name_of_score;
        this.totalUsd = differenceUsd;
    }

    public ExpencesUSD(String score) {
      this.name_of_score = score;
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

    public ExpencesUSD(String category, String name_of_score, String additional_score , BigDecimal totalUsd) {
        this.category = category;
        this.additional_score = additional_score;
        this.name_of_score = name_of_score;
        this.totalUsd = totalUsd;
    }

    public BigDecimal getTotalUsd() {
        return totalUsd;
    }

    public String getAdditional_score() {
        return additional_score;
    }

    public void setAdditional_score(String additional_score) {
        this.additional_score = additional_score;
    }

    public void setTotalUsd(BigDecimal differenceUsd) {
        this.totalUsd = differenceUsd;
    }
}
