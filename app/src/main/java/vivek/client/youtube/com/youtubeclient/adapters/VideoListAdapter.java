package vivek.client.youtube.com.youtubeclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vivek.client.youtube.com.youtubeclient.Keys;
import vivek.client.youtube.com.youtubeclient.R;
import vivek.client.youtube.com.youtubeclient.activities.VideoViewActivity;
import vivek.client.youtube.com.youtubeclient.models.Item;
import vivek.client.youtube.com.youtubeclient.models.Snippet;
import vivek.client.youtube.com.youtubeclient.utils.AdapterUtils;

/**
 * Created by vivekneel on 12/19/16.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder> {

    private List<Item> videoList;
    private Context context;
    private List<vivek.client.youtube.com.youtubeclient.models.latestvideos.Item> latestVideos;
    private boolean isLatestVideos;

    public VideoListAdapter(List<Item> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    public VideoListAdapter(List<vivek.client.youtube.com.youtubeclient.models.latestvideos.Item> latestVideos, Context context, boolean isLatestVideos) {
        this.latestVideos = latestVideos;
        this.context = context;
        this.isLatestVideos = isLatestVideos;
    }

    @Override
    public VideoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_playlist_video, parent, false);
        return new VideoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoListViewHolder holder, int position) {
        if (!isLatestVideos) {
            setupPlaylistVideos(position, holder);
        } else {
            setupLatestVideos(position, holder);
        }


    }

    @Override
    public int getItemCount() {
        return (isLatestVideos ? latestVideos.size() : videoList.size());
    }

    public class VideoListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.videoTitle)
        TextView videoTitle;
        @BindView(R.id.videoDesc)
        TextView videoDesc;
        @BindView(R.id.videoThumbnail)
        ImageView videoThumbnail;
        private String videoId;

        public VideoListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            videoThumbnail.setScaleType(ImageView.ScaleType.FIT_XY);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, VideoViewActivity.class);
                    intent.putExtra(Keys.VIDEO_ID.name(), videoId);
                    context.startActivity(intent);
                }
            });
        }

        private void bindView(String videoId) {
            this.videoId = videoId;
        }

    }

    private void setupPlaylistVideos(int position, VideoListViewHolder holder) {
        Snippet snippet = videoList.get(position).getSnippet();
        String videoTitle = snippet.getTitle();
        String videoDesc = snippet.getDescription();
        String imageUrl = snippet.getThumbnails().getDefault().getUrl();
        String videoId = videoList.get(position).getContentDetails().getVideoId();
        holder.videoTitle.setText(videoTitle);
        holder.videoDesc.setText(videoDesc);
        AdapterUtils.setImage(imageUrl, context, holder.videoThumbnail);
        holder.bindView(videoId);
    }

    private void setupLatestVideos(int position, VideoListViewHolder holder) {
        vivek.client.youtube.com.youtubeclient.models.latestvideos.Snippet snippet = latestVideos.get(position).getSnippet();
        String videoTitle = snippet.getTitle();
        String videoDesc = snippet.getDescription();
        String imageUrl = snippet.getThumbnails().getDefault().getUrl();
        String videoId = latestVideos.get(position).getId().getVideoId();
        holder.videoTitle.setText(videoTitle);
        holder.videoDesc.setText(videoDesc);
        AdapterUtils.setImage(imageUrl, context, holder.videoThumbnail);
        holder.bindView(videoId);
    }
}
