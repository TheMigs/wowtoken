package com.migs.wowtoken;


import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CNFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<WowToken>{

    private static final String WOW_TOKEN_URL =
            "https://data.wowtoken.info/snapshot.json";

    private static final int TOKEN_LOADER_ID = 1;

    private static final int REGION_ID = 2;

    TextView region;
    TextView regionPrice;
    TextView priceHigh;
    TextView priceLow;
    TextView lastDate;
    TextView lastTime;

    public CNFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_region, container, false);

        region = (TextView) rootView.findViewById(R.id.region);
        regionPrice = (TextView) rootView.findViewById(R.id.region_price);
        priceHigh = (TextView) rootView.findViewById(R.id.price_high);
        priceLow = (TextView) rootView.findViewById(R.id.price_low);
        lastDate = (TextView) rootView.findViewById(R.id.last_date);
        lastTime = (TextView) rootView.findViewById(R.id.last_time);

        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(TOKEN_LOADER_ID, null, this);

        return rootView;
    }

    @Override
    public Loader<WowToken> onCreateLoader(int id, Bundle args) {
        Log.e("CN OnCreateLoader :", "new loader created");
        return new WowTokenLoader(getActivity(), WOW_TOKEN_URL, REGION_ID);
    }

    @Override
    public void onLoadFinished(Loader<WowToken> loader, WowToken token) {
        if (token != null){
            long lastUpdate = token.getLastUpdate();
            Date sinceEpoch = new Date(lastUpdate*1000);
            SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yy");
            SimpleDateFormat formattedTime = new SimpleDateFormat("h:mm a z");

            region.setText(token.getRegion());
            regionPrice.setText(token.getCurrentPrice());
            priceHigh.setText(token.getPriceHigh());
            priceLow.setText(token.getPriceLow());
            lastDate.setText(formattedDate.format(sinceEpoch));
            lastTime.setText(formattedTime.format(sinceEpoch));
        }
    }

    @Override
    public void onLoaderReset(Loader<WowToken> loader) {
    }
}
