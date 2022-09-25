package com.alsaleh.naser.supermarketmanagment;

public class ProductDetails {
    private String code;
    private String name;
    private String supplier;
    private String prodDate;
    private String expireDate;

    public ProductDetails() {
    }

    public ProductDetails(String code, String name, String supplier, String prodDate, String expireDate) {
        this.code = code;
        this.name = name;
        this.supplier = supplier;
        this.prodDate = prodDate;
        this.expireDate = expireDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProdDate() {
        return prodDate;
    }

    public void setProdDate(String prodDate) {
        this.prodDate = prodDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
