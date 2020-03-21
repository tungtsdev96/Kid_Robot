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
import android.widget.Toast;

import java.io.File;

/*
1: implement interface DownloadInterface in your Activity
2: constructor of this class require Activity, Url, filename
3: after create a instance of DownloadUtils, call method download
file path and file name in method onDownloadSuccess();
example url: https://www.androidtutorialpoint.com/wp-content/uploads/2016/09/Beauty.jpg
 */
public class DownloadUtils {
    private Activity mActivity;
    private String mDownloadUrl;
    private String mFileName;
    private long downloadId;
    private DownloadInterface downloadInterface;

    public DownloadUtils(Activity mActivity, String mDownloadUrl, String mFileName) {
        this.mActivity = mActivity;
        this.mDownloadUrl = mDownloadUrl;
        this.mFileName = mFileName;
        downloadInterface = (DownloadInterface) mActivity;
    }

    public void download() {
        new DownloadAsyncTask().execute(mDownloadUrl, mFileName);
    }

    private class DownloadAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            downloadFile(mActivity, strings[0], strings[1]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    public void downloadFile(final Activity activity, final String url, final String fileName) {
        try {
            if (url != null && !url.isEmpty()) {
                Uri uri = Uri.parse(url);
                activity.registerReceiver(DownloadCompleteReceive, new IntentFilter(
                        DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setDescription("Downloading...");
                request.setTitle("Download " + fileName);
                // in order for this if to run, you must use the android 3.2 to compile your app
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                }
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
                DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
                downloadId = manager.enqueue(request);
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
                        downloading = false;
                    }
                    final double dl_progress = (int) ((bytes_downloaded * 100l) / bytes_total);
                    downloadInterface.showDownloadProgress(dl_progress);
                    Log.d("DownloadUtils", dl_progress + " ");
                }
            }
        } catch (IllegalStateException e) {
            Toast.makeText(activity, "Please insert an SD card to download file", Toast.LENGTH_SHORT).show();
        }
    }


    BroadcastReceiver DownloadCompleteReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                long id = intent.getLongExtra(
                        DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                if (downloadId == id) {
                    // Toast.makeText(mActivity, "Success" , Toast.LENGTH_SHORT).show();
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), mFileName);
                    downloadInterface.onDownloadSuccess(file.getAbsolutePath(), mFileName);
                    mActivity.unregisterReceiver(DownloadCompleteReceive);
                } else {
                    downloadInterface.onDownloadFail();
                }
            }
        }
    };
}
