package vivek.client.youtube.com.youtubeclient.network;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.rxandroidnetworking.RxAndroidNetworking;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vivek.client.youtube.com.youtubeclient.Constants;
import vivek.client.youtube.com.youtubeclient.callbacks.INetworkCallback;
import vivek.client.youtube.com.youtubeclient.models.Channel;
import vivek.client.youtube.com.youtubeclient.models.Item;

/**
 * Created by vivekneel on 12/19/16.
 */

public class NetworkCalls {

    private static final String TAG = "NetworkCalls";
    private INetworkCallback iNetworkCallback;
    private INetworkCallback.IVideoListCallback iVideoListCallback;
    private Subscription channellListSubsciprtion;

    public NetworkCalls(INetworkCallback iNetworkCallback) {
        this.iNetworkCallback = iNetworkCallback;
    }

    public NetworkCalls(INetworkCallback.IVideoListCallback iVideoListCallback){
        this.iVideoListCallback = iVideoListCallback;
    }


    public void fetchChannelDetails() {
        iNetworkCallback.willFetchChannel();
        channellListSubsciprtion = RxAndroidNetworking.get(Constants.YOUTUBE_API_BASE_URL + "playlists")
                .addQueryParameter("part", "snippet")
                .addQueryParameter("channelId", Constants.CHANNEL_ID)
                .addQueryParameter(Constants.KEY_PARAM, Constants.API_KEY)
                .addQueryParameter(Constants.MAX_RESULTS_PARAM, "50")
                .build()
                .getParseObservable(new TypeToken<Channel>() {

                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Channel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onNext(Channel items) {
                        iNetworkCallback.onFetchedChannels(items, channellListSubsciprtion);
                    }
                });
    }

    public void fetchVideos(String playlistId) {
        iNetworkCallback.willFetchChannel();
        channellListSubsciprtion = RxAndroidNetworking.get(Constants.YOUTUBE_API_BASE_URL + "playlistItems")
                .addQueryParameter("part", "snippet,contentDetails")
                .addQueryParameter("playlistId", playlistId)
                .addQueryParameter(Constants.KEY_PARAM, Constants.API_KEY)
                .addQueryParameter(Constants.MAX_RESULTS_PARAM, "50")
                .build()
                .getParseObservable(new TypeToken<Channel>() {

                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Channel>() {
                    @Override
                    public void onCompleted() {
                        iNetworkCallback.onCompleted();


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onNext(Channel items) {
                        iNetworkCallback.onFetchedChannels(items, channellListSubsciprtion);
                    }
                });

    }

    public void fetchLatestVideos(){
        iVideoListCallback.willFetchVideos();
        channellListSubsciprtion = RxAndroidNetworking.get(Constants.YOUTUBE_API_BASE_URL + "search")
                .addQueryParameter(Constants.KEY_PARAM , Constants.API_KEY)
                .addQueryParameter("part" , "snippet")
                .addQueryParameter(Constants.MAX_RESULTS_PARAM , "50")
                .addQueryParameter("channelId" , Constants.CHANNEL_ID)
                .addQueryParameter("order" , "date")
                .addQueryParameter("type" , "video")
                .build()
                .getParseObservable(new TypeToken<vivek.client.youtube.com.youtubeclient.models.latestvideos.Channel>() {

                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<vivek.client.youtube.com.youtubeclient.models.latestvideos.Channel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");

                    }

                    @Override
                    public void onNext(vivek.client.youtube.com.youtubeclient.models.latestvideos.Channel items) {
                        iVideoListCallback.onFetchedVideos(items, channellListSubsciprtion);
                    }
                });

    }

}
