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

import vivek.client.youtube.com.youtubeclient.Keys;
import vivek.client.youtube.com.youtubeclient.R;
import vivek.client.youtube.com.youtubeclient.activities.VideoListActivity;
import vivek.client.youtube.com.youtubeclient.models.Item;
import vivek.client.youtube.com.youtubeclient.utils.AdapterUtils;

/**
 * Created by vivekneel on 12/18/16.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> {

    private List<Item> channelItems;
    private final Context context;

    public ChannelAdapter(List<Item> channelItems, Context context) {
        this.channelItems = channelItems;
        this.context = context;
    }


    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_channel, parent, false);
        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder holder, int position) {
        setupChannelViews(position, holder);
    }

    @Override
    public int getItemCount() {

        return channelItems.size();
    }

    public class ChannelViewHolder extends RecyclerView.ViewHolder {

        private TextView title, desc;
        private ImageView videoImage;
        private String playlistId, channelName;

        public ChannelViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            videoImage = (ImageView) itemView.findViewById(R.id.videoImage);
            videoImage.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        private void bindView(final String playlistID, final String channelName) {
            this.playlistId = playlistID;

        }

        {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, VideoListActivity.class);
                    intent.putExtra(Keys.PLAYLIST_ID.name(), playlistId);
                    intent.putExtra(Keys.CHANNEL_NAME.name(), channelName);
                    context.startActivity(intent);
                }
            });
        }
    }

    private void setupChannelViews(int position, ChannelViewHolder holder) {
        String title = channelItems.get(position).getSnippet().getTitle();
        String playlistId = channelItems.get(position).getId();
        String desc = channelItems.get(position).getSnippet().getDescription();
        String channelName = channelItems.get(position).getSnippet().getChannelTitle();
        String imageUrl = channelItems.get(position).getSnippet().getThumbnails().getMedium().getUrl();
        if (title != null && !title.isEmpty()) {
            holder.title.setText(title);
        } else {
            holder.title.setVisibility(View.GONE);
        }
        if (desc != null && !desc.isEmpty()) {
            holder.desc.setText(desc);
        } else {
            holder.desc.setVisibility(View.GONE);
        }
        AdapterUtils.setImage(imageUrl, context, holder.videoImage);
        holder.bindView(playlistId, channelName);
    }

}
