///*
// * Copyright (C) 2015 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.android.tupple.robot.utils;
//
//import android.content.Context;
//import android.net.Uri;
//import android.os.Environment;
//import android.text.TextUtils;
//
//import java.io.File;
//import java.util.logging.Logger;
//
///**
// * [Library Class] DO NOT add code snippets related with Client Application.
// */
//public class FileManager {
//
//    private static final String TAG = "SPlannerLibrary";
//
//    public FileManager() { // NOPMD
//        super();
//    }
//
//    public boolean isExternalStorageAvailable() {
//        String extStorageState = Environment.getExternalStorageState();
//        return Environment.MEDIA_MOUNTED.equals(extStorageState)
//                && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
//    }
//
//    public File createOrGetDirectoryFileFromExternalStorage(Context context, String dirName) {
//        final File dir = new File(context.getExternalFilesDir(""), dirName);
//        if (!dir.exists()) {
//            final boolean result = dir.mkdirs();
//            if (result) {
//                Logger.secD(TAG, LOG_PREFIX + "Created directory : " + dir.getAbsolutePath());
//            } else {
//                Logger.secE(TAG, LOG_PREFIX + "Failed to create a directory.");
//            }
//        }
//        return dir;
//    }
//
//    public File createOrGetDirectoryFileFromSandBoxSpace(Context context, String dirName) {
//        final File dir = new File(context.getFilesDir(), dirName);
//        if (!dir.exists()) {
//            final boolean result = dir.mkdirs();
//            if (result) {
//                Logger.secD(TAG, LOG_PREFIX + "Created directory : " + dir.getAbsolutePath());
//            } else {
//                Logger.secE(TAG, LOG_PREFIX + "Failed to create a directory.");
//            }
//        }
//        return dir;
//    }
//
//    public boolean deleteFile(final Uri fileUri) {
//        if (fileUri == null) {
//            Logger.secE(TAG, LOG_PREFIX + "File uri to be deleted is null");
//            return false;
//        }
//        File file = new File(fileUri.getPath());
//        return file.exists() && file.delete();
//    }
//
//    public boolean deleteFile(File directory, String fileName) {
//        File file = new File(directory, fileName);
//        return file.exists() && file.delete();
//    }
//
//    public static long getLastModified(String filePath) {
//        if (TextUtils.isEmpty(filePath)) {
//            return 0;
//        }
//        File file = new File(filePath);
//        if (file.exists()) {
//            return file.lastModified();
//        }
//        return 0;
//    }
//}
