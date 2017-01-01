package vivek.client.youtube.com.youtubeclient.callbacks;

import rx.Subscription;
import vivek.client.youtube.com.youtubeclient.models.Channel;

/**
 * Created by vivekneel on 12/19/16.
 */

public interface INetworkCallback {

    void willFetchChannel();
    void onFetchedChannels(Channel channel , Subscription subscription);
    void onCompleted();

    interface IVideoListCallback{
        void willFetchVideos();
        void onFetchedVideos(vivek.client.youtube.com.youtubeclient.models.latestvideos.Channel channel, Subscription subscription);
    }

}
