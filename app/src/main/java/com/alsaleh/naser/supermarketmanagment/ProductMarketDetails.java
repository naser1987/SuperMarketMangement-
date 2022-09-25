package com.alsaleh.naser.supermarketmanagment;

public class ProductMarketDetails {

    private int market_id;
    private String product_code;
    private String price;

    public ProductMarketDetails(int market_id, String product_code, String price) {
        this.market_id = market_id;
        this.product_code = product_code;
        this.price = price;
    }

    public ProductMarketDetails() {
    }

    public int getMarket_id() {
        return market_id;
    }

    public void setMarket_id(int market_id) {
        this.market_id = market_id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
