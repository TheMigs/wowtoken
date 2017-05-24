package com.migs.wowtoken;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Miguel on 5/22/2017.
 */

public class WowTokenLoader extends AsyncTaskLoader<List<WowToken>> {

    private static final String LOG_TAG = WowTokenLoader.class.getName();

    private String mUrl;

    public WowTokenLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<WowToken> loadInBackground() {
        if (mUrl == null)
            return null;

        List<WowToken> tokens = QueryUtils.fetchWowTokenData(mUrl);

        return tokens;
    }
}
