package com.android.tupple.robot.utils.downloadutils;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.android.tupple.robot.KidRobotApplication;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.model.mediaobject.MediaModelImpl;
import com.android.tupple.robot.utils.SharedPrefs;

import java.io.File;
import java.lang.ref.WeakReference;

/*
1: implement interface DownloadInterface in your Activity
2: constructor of this class require Activity, Url, filename
3: after create a instance of DownloadUtils, call method download
file path and file name in method onDownloadSuccess();
example url: https://www.androidtutorialpoint.com/wp-content/uploads/2016/09/Beauty.jpg
 */
public class DownloadMediaUtils {
    private Context mContext;
    private Activity mActivity;
    private Media media;
    private long downloadId;
    KidRobotApplication kidRobotApplication;

    public DownloadMediaUtils(Context context, Activity mActivity, Media media) {
        this.mContext = context;
        this.mActivity = mActivity;
        this.media = media;
    }

    private static volatile DownloadMediaUtils instance;


//    public static DownloadMediaUtils getInstance(Context context, Activity mActivity, Media media) {
//        if (instance == null) {
//            synchronized (DownloadMediaUtils.class) {
//                if (instance == null) {
//                    instance = new DownloadMediaUtils(context, mActivity, media);
//                }
//            }
//        }
//        return instance;
//    }

    public void download() {
        Log.d("testtest", media.getId() + " ");
        new DownloadAsyncTask(mContext).execute(media.getMedia_url(), media.getTitle());
    }

    private class DownloadAsyncTask extends AsyncTask<String, String, String> {
        private WeakReference<Context> mContext;

        public DownloadAsyncTask(Context mContext) {
            this.mContext = new WeakReference<>(mContext);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected String doInBackground(String... strings) {
            if (mContext.get() != null) {
                downloadFile(mContext.get(), strings[0], strings[1]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void downloadFile(final Context mContext, final String url, final String fileName) {
        try {
            if (url != null && !url.isEmpty()) {
                Uri uri = Uri.parse(url);
                mContext.registerReceiver(DownloadCompleteReceive, new IntentFilter(
                        DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setDescription("Downloading...");
                request.setTitle("Download " + fileName);
                // in order for this if to run, you must use the android 3.2 to compile your app
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            .setMimeType(getMimeType(uri.toString()))
                            .setRequiresCharging(false)// Set if charging is required to begin the download
                            .setAllowedOverMetered(true)// Set if download is allowed on Mobile network
                            .setAllowedOverRoaming(true);
                }
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);


                DownloadManager manager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
                downloadId = manager.enqueue(request);
                SharedPrefs.getInstance().put(media.getId() + "", downloadId);
                boolean downloading = true;
                while (downloading) {
                    DownloadManager.Query q = new DownloadManager.Query();
                    q.setFilterById(downloadId);

                    Cursor cursor = manager.query(q);
                    cursor.moveToFirst();
                    int bytes_downloaded = cursor.getInt(cursor
                            .getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));

                    int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {

                        cursor.close();
                        // downloadInterface.showDownloadProgress(100.0);
                        downloading = false;
                    }
                    final double dl_progress = (int) ((bytes_downloaded * 100l) / bytes_total);
                    //Log.d("DownloadUtils", dl_progress + " ");
//                    activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //downloadInterface.showDownloadProgress(dl_progress);
//                        }
//                    });

                }
            }
        } catch (IllegalStateException e) {
            //Toast.makeText(fragment.getActivity(), "Please insert an SD card to download file", Toast.LENGTH_SHORT).show();
        }
    }

    private String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            type = mime.getMimeTypeFromExtension(extension);
        }
        return type;
    }

    BroadcastReceiver DownloadCompleteReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                long id = intent.getLongExtra(
                        DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                // Log.d("testtest", downloadId + " " + id);
                if (downloadId == id) {
                    kidRobotApplication = (KidRobotApplication) mContext;
                    new DialogUtils(kidRobotApplication.getCurrentActivity(), "Thông báo", "Tải xuống " + media.getTitle() + " thành công", R.drawable.ic_success).showDialog();
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), media.getTitle());
                    Media newMedia = new Media(media.getId(), media.getTitle(), media.getMedia_type(), file.getAbsolutePath(), media.getDescription(), true, media.getThumbnail_video());
                    updateMediaToDB(newMedia);
                    mContext.unregisterReceiver(DownloadCompleteReceive);
                } else {
                    //downloadInterface.onDownloadFail();
                }
            }
        }
    };

    private void updateMediaToDB(Media media) {
        new MediaModelImpl(mContext).updateVideo(media).subscribe(this::showToast);
    }

    private void showToast() {
        kidRobotApplication = (KidRobotApplication) mContext;
        Toast.makeText(kidRobotApplication.getCurrentActivity(), "update thành công, " + media.getTitle(), Toast.LENGTH_LONG).show();
    }
}
