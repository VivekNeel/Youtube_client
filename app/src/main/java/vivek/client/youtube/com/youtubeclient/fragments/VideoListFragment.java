package vivek.client.youtube.com.youtubeclient.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vivek.client.youtube.com.youtubeclient.R;

/**
 * Created by vivekneel on 12/19/16.
 */

public class VideoListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_item_playlist_video, container, false);
        return rootView;
    }

    public static VideoListFragment newInstance() {

        Bundle args = new Bundle();

        VideoListFragment fragment = new VideoListFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
