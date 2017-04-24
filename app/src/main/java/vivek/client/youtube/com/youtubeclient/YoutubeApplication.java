package vivek.client.youtube.com.youtubeclient;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.jacksonandroidnetworking.JacksonParserFactory;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by vivekneel on 12/18/16.
 */

public class YoutubeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize fabric
        Fabric.with(this, new Crashlytics());
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
    }
}
