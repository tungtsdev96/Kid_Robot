package com.android.tupple.robot.view.englishtopic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.utils.GlideUtils;
import com.android.tupple.robot.utils.ResourceUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by tungts on 2020-02-15.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private Context mContext;
    private List<Topic> mItems = new ArrayList<>();

    private ItemTopicClickedListener mOnItemTopicClickedListener;

    public interface ItemTopicClickedListener {
        void onClicked(int pos);
    }

    public TopicAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemTopicClickedListener(ItemTopicClickedListener onItemTopicClickedListener) {
        this.mOnItemTopicClickedListener = onItemTopicClickedListener;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopicViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_topic, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Topic getTopicByPosition(int pos) {
        if (pos < 0 || pos >= mItems.size()) {
            return null;
        }

        return mItems.get(pos);
    }

    public void setListData(List<Topic> listTopic) {
        mItems.clear();
        mItems.addAll(listTopic);
        notifyDataSetChanged();
    }

    class TopicViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgTopic;
        private TextView textTitleTopic;
        private ImageView imgMask;

        private LinearLayout containerDownload;
        private ProgressBar progressDownload;
        private TextView btnDownload;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTopic = itemView.findViewById(R.id.image_topic);
            textTitleTopic = itemView.findViewById(R.id.text_title_topic);
            itemView.setOnClickListener(v -> {
                if (mOnItemTopicClickedListener != null) {
                    mOnItemTopicClickedListener.onClicked(getAdapterPosition());
                }
            });
        }

        void bind(Topic topic) {
            textTitleTopic.setText(topic.getTopicTitle());
            initImageTopic(topic.getTopicTitle());
        }

        private void initImageTopic(String topicTitle) {
            RequestOptions options = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .override(imgTopic.getWidth(), imgTopic.getHeight())
                    .error(R.color.err_image);
            int drawable = ResourceUtils.convertNameToDrawableRes(mContext, "ic_" + topicTitle.replace(' ', '_').toLowerCase());

            Objects.requireNonNull(
                    GlideUtils.getRequestManager(mContext))
                    .load(drawable)
                    .apply(options)
                    .into(imgTopic);
        }
    }

}
