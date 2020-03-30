package com.android.tupple.robot.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

public class FileManager {

    private static final String TAG = "SPlannerLibrary";
    private static final String LOG_PREFIX = "FileManager";

    public FileManager() { // NOPMD
        super();
    }

    public boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState)
                && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
    }

    public File createOrGetDirectoryFileFromExternalStorage(Context context, String dirName) {
        final File dir = new File(context.getExternalFilesDir(""), dirName);
        if (!dir.exists()) {
            final boolean result = dir.mkdirs();
            if (result) {
                Log.d(TAG, LOG_PREFIX + "Created directory : " + dir.getAbsolutePath());
            } else {
                Log.d(TAG, LOG_PREFIX + "Failed to create a directory.");
            }
        }
        return dir;
    }

    public File createOrGetDirectoryFileFromSandBoxSpace(Context context, String dirName) {
        final File dir = new File(context.getFilesDir(), dirName);
        if (!dir.exists()) {
            final boolean result = dir.mkdirs();
            if (result) {
                Log.d(TAG, LOG_PREFIX + "Created directory : " + dir.getAbsolutePath());
            } else {
                Log.d(TAG, LOG_PREFIX + "Failed to create a directory.");
            }
        }
        return dir;
    }

    public boolean deleteFile(final Uri fileUri) {
        if (fileUri == null) {
            Log.e(TAG, LOG_PREFIX + "File uri to be deleted is null");
            return false;
        }
        File file = new File(fileUri.getPath());
        return file.exists() && file.delete();
    }

    public boolean deleteFile(File directory, String fileName) {
        File file = new File(directory, fileName);
        return file.exists() && file.delete();
    }

    public static long getLastModified(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return 0;
        }
        File file = new File(filePath);
        if (file.exists()) {
            return file.lastModified();
        }
        return 0;
    }
}

