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
import vivek.client.youtube.com.youtubeclient.callbacks.INetworkCallback;
import vivek.client.youtube.com.youtubeclient.models.Channel;
import vivek.client.youtube.com.youtubeclient.models.Item;
import vivek.client.youtube.com.youtubeclient.network.NetworkCalls;
import vivek.client.youtube.com.youtubeclient.utils.AdapterUtils;

/**
 * Created by vivekneel on 12/19/16.
 */

public class ChannelListFragment extends Fragment implements INetworkCallback {

    private static final String TAG = "ChannelListFragment";
    @BindView(R.id.channelRV)
    RecyclerView channelRV;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avLoadingIndicatorView;
    private ChannelAdapter channelAdapter;
    private Context context;
    private Subscription channelSubsription;
    private List<Item> channels = new ArrayList<>();
    private IChannelSubscription iChannelSubscription;


    public static ChannelListFragment newInstance() {
        Bundle args = new Bundle();
        ChannelListFragment fragment = new ChannelListFragment();
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
        avLoadingIndicatorView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        return rootView;
    }

    @Override
    public void willFetchChannel() {
        //AdapterUtils.manageAlertDialog(context, true);
        avLoadingIndicatorView.show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        channelRV.setLayoutManager(new LinearLayoutManager(context));
            new NetworkCalls(this).fetchChannelDetails();
    }

    @Override
    public void onFetchedChannels(Channel channel, Subscription subscription) {
       // AdapterUtils.manageAlertDialog(context, false);
        avLoadingIndicatorView.hide();
        channelSubsription = subscription;
        channels = channel.getItems();
        channelAdapter = new ChannelAdapter(channels, context);
        channelRV.setAdapter(channelAdapter);
    }

    @Override
    public void onCompleted() {

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

    public interface IChannelSubscription{
        void sendSubscriptionInstance();
    }
}
