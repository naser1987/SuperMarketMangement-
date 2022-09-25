package com.alsaleh.naser.supermarketmanagment;

public class MarketDetails {
    private  String marketName;
    private  String marketAddress;
    private  String marketPhoneNumber;

    public MarketDetails(String marketName, String marketAddress, String marketPhoneNumber) {
        this.marketName = marketName;
        this.marketAddress = marketAddress;
        this.marketPhoneNumber = marketPhoneNumber;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketAddress() {
        return marketAddress;
    }

    public void setMarketAddress(String marketAddress) {
        this.marketAddress = marketAddress;
    }

    public String getMarketPhoneNumber() {
        return marketPhoneNumber;
    }

    public void setMarketPhoneNumber(String marketPhoneNumber) {
        this.marketPhoneNumber = marketPhoneNumber;
    }
}
