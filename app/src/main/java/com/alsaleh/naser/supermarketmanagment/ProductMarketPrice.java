package com.alsaleh.naser.supermarketmanagment;


public class ProductMarketPrice implements Comparable<ProductMarketPrice> {
    private String productName;
    private  String marketName;
    private String price;

    public ProductMarketPrice() {
    }

    public ProductMarketPrice(String productName, String marketName, String price) {
        this.productName = productName;
        this.marketName = marketName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int compareTo(ProductMarketPrice productMarketPrice) {
        int price1 = Integer.parseInt(productMarketPrice.getPrice().trim() );
        int price2 = Integer.parseInt(this.getPrice().trim());
        return  (price2 - price1 );

    }
}
