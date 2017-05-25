package com.migs.wowtokenprice;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Miguel on 5/22/2017.
 */

public class RegionAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public RegionAdapter(Context context, FragmentManager fm){
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new NAFragment();
        else if (position == 1)
            return  new EUFragment();
        else if(position == 2)
            return  new CNFragment();
        else if(position == 3)
            return  new TWFragment();
        else
            return new KRFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return mContext.getString(R.string.na);
        else if(position == 1)
            return mContext.getString(R.string.eu);
        else if(position == 2)
            return mContext.getString(R.string.cn);
        else if(position == 3)
            return mContext.getString(R.string.tw);
        else
            return mContext.getString(R.string.kr);
    }
}
