package sample;

import javafx.scene.control.TreeItem;

import java.math.BigDecimal;
import java.util.List;

public class Expences extends TreeItem<Expences> {
    private String expens;
    private String code;
    private BigDecimal total;


    public Expences(String expens, String code, BigDecimal total) {
        this.expens = expens;
        this.code = code;
        this.total = total;
    }

    private List <Expences> inner;

    public Expences(String category) {
        this.expens = category;
    }


    public String getExpens() {
        return expens;
    }
    public void setExpens(String expens) {
        this.expens = expens;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
