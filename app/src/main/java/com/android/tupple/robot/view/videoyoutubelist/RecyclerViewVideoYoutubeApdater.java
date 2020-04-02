package com.android.tupple.robot.view.videoyoutubelist;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
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
import com.android.tupple.robot.data.file.FileUtils;
import com.android.tupple.robot.utils.GlideUtils;
import com.android.tupple.robot.utils.RxUtils;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class RecyclerViewVideoYoutubeApdater extends RecyclerView.Adapter<RecyclerViewVideoYoutubeApdater.ItemViewHolder> {
    Context mContext;
    List<Media> mMediaItems = new ArrayList<>();
    private ItemVideoClickedListener mOnItemVideClickedListener;

    public RecyclerViewVideoYoutubeApdater(Context mContext) {
        this.mContext = mContext;
    }

    public interface ItemVideoClickedListener {
        void onClicked(int pos);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewVideoYoutubeApdater.ItemViewHolder(
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
            titleVideo.setText(media.getTitle());

            File pictureFile = new File(mContext.getCacheDir(), media.getTitle() + ".png");
            if (pictureFile.exists()) {
                Log.d("MInhtest", "khacnull");
                Glide.with(mContext).load(pictureFile.getAbsolutePath()).into(thumbnailVideo);
            } else {
                Observable.fromCallable(() -> {
                    Bitmap b = null;
                    try {
                        b = retriveVideoFrameFromVideo(videoURL);
                        b = Bitmap.createScaledBitmap(b, 130, 100, false);
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    return b;
                }).doOnNext(b -> {
                    FileUtils.storeImage(mContext, b, media.getTitle());
                })
                        .compose(RxUtils.async())
                        .subscribe(
                                b -> GlideUtils.getRequestManager(itemView.getContext()).load(b).into(thumbnailVideo),
                                Throwable::printStackTrace);
            }
            //Glide.with(mContext).load(media.getThumbnail()).into(thumbnailVideo);
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
            if (mediaMetadataRetriever != null)
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
