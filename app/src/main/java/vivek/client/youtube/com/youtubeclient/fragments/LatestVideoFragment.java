package vivek.client.youtube.com.youtubeclient.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import vivek.client.youtube.com.youtubeclient.R;
import vivek.client.youtube.com.youtubeclient.adapters.ChannelAdapter;
import vivek.client.youtube.com.youtubeclient.adapters.VideoListAdapter;
import vivek.client.youtube.com.youtubeclient.callbacks.INetworkCallback;
import vivek.client.youtube.com.youtubeclient.models.Channel;
import vivek.client.youtube.com.youtubeclient.models.Item;
import vivek.client.youtube.com.youtubeclient.network.NetworkCalls;
import vivek.client.youtube.com.youtubeclient.utils.AdapterUtils;

/**
 * Created by vivekneel on 12/19/16.
 */

public class LatestVideoFragment extends Fragment implements INetworkCallback.IVideoListCallback {

    private static final String TAG = "ChannelListFragment";
    @BindView(R.id.channelRV)
    RecyclerView channelRV;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avLoadingIndicatorView;
    private VideoListAdapter channelAdapter;
    private Context context;
    private Subscription channelSubsription;
    private List<vivek.client.youtube.com.youtubeclient.models.latestvideos.Item> channels = new ArrayList<>();
    private IChannelSubscription iChannelSubscription;


    public static LatestVideoFragment newInstance() {
        Bundle args = new Bundle();
        LatestVideoFragment fragment = new LatestVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_channel, container, false);
        ButterKnife.bind(this , rootView);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        channelRV.setLayoutManager(new LinearLayoutManager(context));
            new NetworkCalls(this).fetchLatestVideos();
      }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            iChannelSubscription = (IChannelSubscription) context;
        } catch (ClassCastException e){
            Log.e(TAG, "onAttach: ", e.getCause() );
        }
    }

    @Override
    public void willFetchVideos() {
        avLoadingIndicatorView.show();
    }

    @Override
    public void onFetchedVideos(vivek.client.youtube.com.youtubeclient.models.latestvideos.Channel channel, Subscription subscription) {
        avLoadingIndicatorView.hide();
        channelSubsription = subscription;
        channels = channel.getItems();
        channelAdapter = new VideoListAdapter(channels, context , true);
        channelRV.setAdapter(channelAdapter);

    }

    public interface IChannelSubscription{
        void sendSubscriptionInstance();
    }
}
