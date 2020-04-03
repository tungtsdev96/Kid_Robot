package com.android.tupple.robot.view.listvideos;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.file.FileUtils;
import com.android.tupple.robot.utils.GlideUtils;
import com.android.tupple.robot.utils.RxUtils;
import com.android.tupple.robot.utils.downloadutils.DownloadInterface;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class RecyclerViewVideoAdapter extends RecyclerView.Adapter<RecyclerViewVideoAdapter.ItemViewHolder> implements DownloadInterface {
    Context mContext;
    List<Media> mMediaItems = new ArrayList<>();
    private ItemVideoClickedListener mOnItemVideClickedListener;
    int progress = 0;
    int positionItemWhenStartDownload = -1;
    int positionItemWhenFinishDownload = -1;

    public RecyclerViewVideoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void showDownloadProgress(double progress) {
        this.progress = (int) progress;
    }

    public void setPositionForItemWhenFinishDownload(String title) {
        for (int i = 0; i < mMediaItems.size(); i++) {
            if (mMediaItems.get(i).getTitle() == title) {
                positionItemWhenFinishDownload = i;
                break;
            }
        }
        notifyDataSetChanged();
    }

    public void setPositionForItemWhenStartDownload(Media media) {
        for (int i = 0; i < mMediaItems.size(); i++) {
            if (mMediaItems.get(i).getId() == media.getId()) {
                positionItemWhenStartDownload = i;
                break;
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public void onDownloadSuccess(String filePath, String fileName) {

    }

    @Override
    public void onDownloadFail() {

    }

    public void setProgress(double progress) {
        this.progress = (int) progress;
        notifyDataSetChanged();
    }

    public interface ItemVideoClickedListener {
        void onClicked(int pos);
    }



    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(
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
        //holder.progressDownload.setProgress(progress);
        if (positionItemWhenStartDownload == position) {
            updateLayoutItemWhenStartDownload(holder);
        } else {
            holder.progressDownload.setVisibility(View.GONE);
        }
        if (positionItemWhenFinishDownload==position){
            updateLayoutItemWhenFinishDownload(holder);
        }
    }
    private void updateLayoutItemWhenStartDownload(ItemViewHolder holder){
        holder.progressDownload.setVisibility(View.VISIBLE);
        holder.titleVideo.setVisibility(View.GONE);
        holder.progressDownload.setProgress(progress);
    }
    private void updateLayoutItemWhenFinishDownload(ItemViewHolder holder){
        holder.progressDownload.setVisibility(View.GONE);
        holder.imgIconDownload.setVisibility(View.GONE);
        holder.titleVideo.setVisibility(View.VISIBLE);
        holder.relativeIcon.setBackgroundColor(mContext.getResources().getColor(R.color.text_btn_answer_enable));
    }
    @Override
    public int getItemCount() {
        return mMediaItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnailVideo;
        private TextView titleVideo;
        private ImageView imgIconDownload;
        private ProgressBar progressDownload;
        private RelativeLayout relativeIcon;

        public ProgressBar getProgressDownload() {
            return progressDownload;
        }

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailVideo = itemView.findViewById(R.id.icon_thumbnail_video);
            titleVideo = itemView.findViewById(R.id.txt_title_video);
            imgIconDownload = itemView.findViewById(R.id.img_icon_download);
            progressDownload = itemView.findViewById(R.id.progress_download_media);
            relativeIcon = itemView.findViewById(R.id.relative_icon);
            itemView.setOnClickListener(v -> {
                if (mOnItemVideClickedListener != null) {
                    mOnItemVideClickedListener.onClicked(getAdapterPosition());
                }
            });
        }

        void bind(Media media) {
            String videoURL = media.getMedia_url();
            titleVideo.setText(media.getTitle());
            if (media.isLocal()) {
                File pictureFile = new File(mContext.getCacheDir(), media.getTitle() + ".png");
                if (pictureFile.exists()) {
                    Glide.with(mContext).load(pictureFile.getAbsolutePath()).into(thumbnailVideo);
                } else {
                    Observable.fromCallable(() -> {
                        Bitmap b = null;
                        try {
                            b = retriveVideoFrameFromVideo(videoURL);
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
            } else {
                String imageUrl = media.getThumbnail_video();
                Glide.with(mContext).load(imageUrl).into(thumbnailVideo);
                relativeIcon.setBackgroundColor(mContext.getResources().getColor(R.color.color_relative_icon_download));
            }
            imgIconDownload.setVisibility(media.isLocal() ? View.GONE : View.VISIBLE);
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
