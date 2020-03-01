package com.android.tupple.robot.view.entertainment;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
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
import java.util.HashMap;
import java.util.List;

public class RecyclerViewVideoAdapter extends RecyclerView.Adapter<RecyclerViewVideoAdapter.ItemViewHolder> {
    Context mContext;
    List<Media> mMediaItems = new ArrayList<>();
    private ItemVideoClickedListener mOnItemVideClickedListener;

    public RecyclerViewVideoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public interface ItemVideoClickedListener {
        void onClicked(int pos);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewVideoAdapter.ItemViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_video_entertainment, parent, false)
        );
    }

    public void setOnItemVideoClickedListener(ItemVideoClickedListener onItemVideoClickedListener) {
        this.mOnItemVideClickedListener = onItemVideoClickedListener;
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

        private ImageView thumbnailVideo;
        private TextView titleVideo;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailVideo = itemView.findViewById(R.id.icon_thumbnail_video);
            titleVideo = itemView.findViewById(R.id.txt_title_video);
            itemView.setOnClickListener(v -> {
                if (mOnItemVideClickedListener != null) {
                    mOnItemVideClickedListener.onClicked(getAdapterPosition());
                }
            });
        }

        void bind(Media media) {
            String videoURL = media.getMedia_url();
            Bitmap bitmap = null;
            new GetThumbnail().execute(videoURL);
            //Glide.with(mContext).load(media.getThumbnail()).into(thumbnailVideo);
            titleVideo.setText(media.getTitle());
        }

        private class GetThumbnail extends AsyncTask<String, Integer, Bitmap> {
            Bitmap bitmap = null;

            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    bitmap = retriveVideoFrameFromVideo(strings[0]);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                bitmap = Bitmap.createScaledBitmap(bitmap, 130, 100, false);
                thumbnailVideo.setImageBitmap(bitmap);
            }
        }

    }

    public Media getVideoByPosition(int pos) {
        if (pos < 0 || pos >= mMediaItems.size()) {
            return null;
        }
        return mMediaItems.get(pos);
    }

    public static Bitmap retriveVideoFrameFromVideo(String videoPath)
            throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(videoPath);

            bitmap = mediaMetadataRetriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable(
                    "Exception in retriveVideoFrameFromVideo(String videoPath)"
                            + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

}
