package com.migs.wowtoken;

/**
 * Created by Miguel on 5/21/2017.
 */

public class WowToken {
    //Region of Token
    private String mRegion;

    //current price of WoW Token
    private String mCurrentPrice;

    //current price of WoW Token
    private String mPriceHigh;

    //current price of WoW Token
    private String mPriceLow;

    //Last update
    private long mLastUpdate;

    public WowToken(String region, String currentPrice, String priceHigh, String priceLow, long lastUpdate){
        mRegion = region;
        mCurrentPrice = currentPrice;
        mPriceHigh = priceHigh;
        mPriceLow = priceLow;
        mLastUpdate = lastUpdate;
    }

    public String getRegion() {
        return mRegion;
    }

    public String getCurrentPrice() {
        return mCurrentPrice;
    }

    public String getPriceHigh() {
        return mPriceHigh;
    }

    public String getPriceLow() {
        return mPriceLow;
    }

    public long getLastUpdate() {
        return mLastUpdate;
    }
}
