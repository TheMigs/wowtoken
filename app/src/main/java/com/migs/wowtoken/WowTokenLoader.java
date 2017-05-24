package com.migs.wowtoken;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by Miguel on 5/22/2017.
 */

public class WowTokenLoader extends AsyncTaskLoader<WowToken> {

    private static final String LOG_TAG = WowTokenLoader.class.getName();

    private String mUrl;

    private int mRegion;

    public WowTokenLoader(Context context, String url, int region){
        super(context);
        mUrl = url;
        mRegion = region;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public WowToken loadInBackground() {
        if (mUrl == null)
            return null;

        return QueryUtils.fetchWowTokenData(mUrl, mRegion);
    }
}
