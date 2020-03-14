package com.android.tupple.robot.view.audiolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Media;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAudioAdapter extends RecyclerView.Adapter<RecyclerViewAudioAdapter.ItemViewHolder> {
    Context mContext;
    List<Media> mMediaItems = new ArrayList<>();
    private ItemAudioClickedListener mOnItemAudioClickedListener;

    public RecyclerViewAudioAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public interface ItemAudioClickedListener {
        void onClicked(int pos);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewAudioAdapter.ItemViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_audio_entertainment, parent, false)
        );
    }

    public void setOnItemAudioClickedListener(ItemAudioClickedListener onItemAudioClickedListener) {
        this.mOnItemAudioClickedListener = onItemAudioClickedListener;
    }

    public void setListData(List<Media> listMedia) {
        mMediaItems.clear();
        mMediaItems.addAll(listMedia);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mMediaItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mMediaItems.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnailAudio;
        private TextView titleAudio;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailAudio = itemView.findViewById(R.id.icon_thumbnail_audio);
            titleAudio = itemView.findViewById(R.id.txt_title_audio);
            itemView.setOnClickListener(v -> {
                if (mOnItemAudioClickedListener != null) {
                    mOnItemAudioClickedListener.onClicked(getAdapterPosition());
                }
            });
        }

        void bind(Media media) {
            Glide.with(mContext).load(media.getThumbnail()).into(thumbnailAudio);
            titleAudio.setText(media.getTitle());
        }

    }
    public Media getAudioByPosition(int pos) {
        if (pos < 0 || pos >= mMediaItems.size()) {
            return null;
        }
        return mMediaItems.get(pos);
    }
}
