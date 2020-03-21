package com.android.tupple.robot.utils.downloadutils;

public interface DownloadInterface {
    void showDownloadProgress(double progress);
    void onDownloadSuccess(String filePath, String fileName);
    void onDownloadFail();
}

