package sample;

import javafx.scene.control.TreeItem;

import java.math.BigDecimal;

public class Expences extends TreeItem<Expences> {
    private String category;
    private String name_of_score;
    private BigDecimal difference;


    public Expences(String expens, String code, BigDecimal total) {
        this.category = expens;
        this.name_of_score = code;
        this.difference = total;
    }
    public Expences(String category) {
        this.category = category;
    }


    public String getExpens() {
        return category;
    }
    public void setExpens(String expens) {
        this.category = expens;
    }
    public String getCode() {
        return name_of_score;
    }
    public void setCode(String code) {
        this.name_of_score = code;
    }
    public BigDecimal getTotal() {
        return difference;
    }
    public void setTotal(BigDecimal total) {
        this.difference = total;
    }
}
