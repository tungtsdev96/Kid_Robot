package com.android.tupple.robot.view.listvideoyoutube;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecyclerViewVideoYoutubeApdater extends RecyclerView.Adapter<RecyclerViewVideoYoutubeApdater.ItemViewHolder> {
    Context mContext;
    List<Media> mMediaItems = new ArrayList<>();
    private ItemVideoClickedListener mOnItemVideClickedListener;
    String title = "";
    public RecyclerViewVideoYoutubeApdater(Context mContext) {
        this.mContext = mContext;
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
            String media_url = media.getMedia_url();
            //new ReadJson().execute(media_url);
            Log.d("Minhtest" , title);
            titleVideo.setText(media.getTitle());
           String thumbnailURL = "http://img.youtube.com/vi/"+getYouTubeId(media_url)+"/hqdefault.jpg";
           Glide.with(mContext).load(thumbnailURL).into(thumbnailVideo);
        }


    }


    private static String getYouTubeId (String youTubeUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
        }
    }

    class ReadJson extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            title = "";
            StringBuilder content = new StringBuilder();
            URL embededURL = null;
            try {
                embededURL = new URL("http://www.youtube.com/oembed?url=" +
                        strings[0] + "&format=json");
                InputStreamReader inputStreamReader = new InputStreamReader(embededURL.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("Minhtest" , content.toString());
            return content.toString();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                title = jsonObject.getString("title");
            } catch (JSONException e) {
                e.printStackTrace();
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
