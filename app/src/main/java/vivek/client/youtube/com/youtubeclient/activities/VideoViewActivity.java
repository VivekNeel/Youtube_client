package vivek.client.youtube.com.youtubeclient.activities;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vivek.client.youtube.com.youtubeclient.Constants;
import vivek.client.youtube.com.youtubeclient.Keys;
import vivek.client.youtube.com.youtubeclient.R;

/**
 * Created by vivekneel on 12/19/16.
 */

public class VideoViewActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {


    private static final int REQUEST_CODE = 22;
    @BindView(R.id.youtube_player_view)
    YouTubePlayerView youTubePlayerView;
    private String videoId;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.video_view_activity);
        ButterKnife.bind(this);
        youTubePlayerView.initialize(Constants.API_KEY, this);
        Bundle bundle = getIntent().getExtras();
        videoId = bundle.getString(Keys.VIDEO_ID.name(), "");


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.loadVideo(videoId);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE);
        }

    }
}
