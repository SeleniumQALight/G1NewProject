package api;

import com.google.gson.annotations.SerializedName;

public class ExchangeRateDTO {
    @SerializedName("ccy")
    private String ccy;
    @SerializedName("base_ccy")
    private String base_ccy;
    @SerializedName("buy")
    private String buy;
    @SerializedName("sale")
    private String sale;

    public ExchangeRateDTO(String ccy, String base_ccy, String buy, String sale) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.buy = buy;
        this.sale = sale;
    }
    public ExchangeRateDTO(){};

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(String base_ccy) {
        this.base_ccy = base_ccy;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
