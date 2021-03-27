package sample;

public class Expences {
    private String expens = null;
    private String code = null;
    private String total =null;

    public Expences() {
    }

    public Expences(String expens, String code, String total) {
        this.expens = expens;
        this.code = code;
        this.total = total;
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
    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }
}
