package vivek.client.youtube.com.youtubeclient.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vivek.client.youtube.com.youtubeclient.Constants;
import vivek.client.youtube.com.youtubeclient.fragments.ChannelListFragment;
import vivek.client.youtube.com.youtubeclient.fragments.LatestVideoFragment;

/**
 * Created by vivekneel on 12/19/16.
 */


public class CustomActivityViewPagerAdapter extends FragmentPagerAdapter {

    Bundle bundle = new Bundle();


    public CustomActivityViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = ChannelListFragment.newInstance();
                break;
            case 1:
                fragment = LatestVideoFragment.newInstance();
                break;
            default:
                fragment = ChannelListFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return Constants.pageTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.pageTitle[position];
    }
}
