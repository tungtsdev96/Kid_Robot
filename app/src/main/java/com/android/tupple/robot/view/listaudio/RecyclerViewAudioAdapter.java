package com.android.tupple.robot.view.listaudio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Media;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAudioAdapter extends RecyclerView.Adapter<RecyclerViewAudioAdapter.ItemViewHolder> {
    Context mContext;
    List<Media> mMediaItems = new ArrayList<>();
    private ItemAudioClickedListener mOnItemAudioClickedListener;
    int index = -1;

    public RecyclerViewAudioAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public interface ItemAudioClickedListener {
        void onClicked(int pos);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_audio_entertainment, parent, false)
        );
    }

    public void setOnItemAudioClickedListener(ItemAudioClickedListener onItemAudioClickedListener) {
        this.mOnItemAudioClickedListener = onItemAudioClickedListener;
    }

    public void changeBackgroundItemClicked(Media media) {
        for (int i = 0 ; i < mMediaItems.size() ; i++){
            if (mMediaItems.get(i).getId() == media.getId()){
                index = i;
                break;
            }
        }
        notifyDataSetChanged();
    }

    public void setListData(List<Media> listMedia) {
        mMediaItems.clear();
        mMediaItems.addAll(listMedia);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mMediaItems.get(position));
        holder.background.setBackgroundColor(index == position ? mContext.getResources().getColor(R.color.color_item_focus) : mContext.getResources().getColor(R.color.color_item));
    }

    @Override
    public int getItemCount() {
        return mMediaItems.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView titleAudio;
        private RelativeLayout background;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleAudio = itemView.findViewById(R.id.txt_title_audio);
            background = itemView.findViewById(R.id.background);
            itemView.setOnClickListener(v -> {
                if (mOnItemAudioClickedListener != null) {
                    mOnItemAudioClickedListener.onClicked(getAdapterPosition());
                }
            });
        }

        void bind(Media media) {
            titleAudio.setText(media.getTitle());
        }

    }

    public Media getAudioByPosition(int pos) {
        if (pos < 0 || pos >= mMediaItems.size()) {
            return null;
        }
        return mMediaItems.get(pos);
    }
    public int getPositionByMedia(Media media){
        return mMediaItems.indexOf(media);
    }
}
