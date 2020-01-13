//package com.android.tupple.robot.utils;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.CursorIndexOutOfBoundsException;
//import android.net.Uri;
//import android.os.Debug;
//import android.provider.OpenableColumns;
//import android.text.TextUtils;
//
//import com.samsung.android.calendarutils.Logger;
//
//import java.io.Closeable;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.channels.FileChannel;
//import java.util.Arrays;
//import java.util.List;
//
//public class FileUtils {
//    private final static String TAG = "FileUtils";
//
//    private static boolean fileExist(String dstFilePath) {
//        boolean isExistFile = false;
//        try {
//            isExistFile = new File(dstFilePath).exists();
//        } catch (Exception e) {
//            Logger.secE(TAG, "Error occur while exist file. ");
//        }
//        return isExistFile;
//    }
//
//    public static boolean copyFile(String srcFilePath, String dstFilePath, boolean overwrite) throws IOException {
//        FileInputStream srcFile = null;
//        FileOutputStream dstFile = null;
//        FileChannel srcFileChannel = null;
//        FileChannel dstFileChannel = null;
//        try {
//            if (!overwrite && FileUtils.fileExist(dstFilePath)) {
//                throw new IOException("A target already exists : " + dstFilePath);
//            }
//            srcFile = new FileInputStream(srcFilePath);
//            dstFile = new FileOutputStream(dstFilePath);
//            srcFileChannel = srcFile.getChannel();
//            dstFileChannel = dstFile.getChannel();
//            if (srcFileChannel == null || dstFileChannel == null) {
//                return false;
//            }
//            long size = srcFileChannel.size();
//            if (size != 0) {
//                srcFileChannel.transferTo(0, size, dstFileChannel);
//            }
//        } finally {
//            closeSilently(srcFileChannel);
//            closeSilently(dstFileChannel);
//            closeSilently(srcFile);
//            closeSilently(dstFile);
//        }
//
//        return true;
//    }
//
//    private static void closeSilently(Closeable c) {
//        if (c == null)
//            return;
//        try {
//            c.close();
//        } catch (Throwable t) {
//            Logger.secE(TAG, "close fail", t);
//        }
//    }
//
//    public static List<File> getDirFileList(String dirPath) {
//        List<File> dirFileList = null;
//        File dir = new File(dirPath);
//
//        if (dir.exists()) {
//            File[] files = dir.listFiles();
//            dirFileList = Arrays.asList(files);
//        }
//        return dirFileList;
//
//    }
//
//    public static boolean clearFile(String path) {
//        File target = new File(path);
//        return clearFile(target);
//    }
//
//    private static boolean clearFile(File target) {
//        Logger.secI(TAG, "clearFile target:" + (target != null ? target.getName() : " null"));
//        if (target != null && target.exists()) {
//            boolean result = false;
//            if (target.isDirectory()) {
//                final File[] list = target.listFiles();
//                if (list == null) {
//                    return true;
//                }
//                for (File f : list) {
//                    result = clearFile(f);
//                    if (result) {
//                        result = target.delete();
//                    }
//                    Logger.secI(TAG, "clearFile listFiles result:" + result);
//                }
//            } else {
//                result = target.delete();
//            }
//            Logger.secI(TAG, "clearFile result:" + result);
//            return result;
//        } else {
//            Logger.secI(TAG, "clearFile target not exists");
//            return true;
//        }
//    }
//
//    public static String getFileNameFromFileUri(Context context, Uri fileUri) {
//        Logger.secD(TAG, "getFileNameFromFileUri: started, fileUri : " + (Debug.semIsProductDev() ? fileUri : "fileUri"));
//
//        if (fileUri == null) {
//            Logger.secE(TAG, "getFileNameFromFileUri uri is null");
//            return null;
//        }
//
//        Cursor fileInfoCursor = null;
//        String fileName = null;
//        try {
//            fileInfoCursor = context.getContentResolver().query(fileUri, null, null, null, null);
//            fileName = getFileNameFromCursor(fileInfoCursor);
//        } catch (CursorIndexOutOfBoundsException e) {
//            Logger.secE(TAG, "copyFileFromUri: e :" + e.toString());
//        } finally {
//            if (fileInfoCursor != null) {
//                fileInfoCursor.close();
//            }
//        }
//        return !TextUtils.isEmpty(fileName) ? fileName : FileUtils.getFileNameFromPath(fileUri.toString());
//    }
//
//    private static String getFileNameFromCursor(Cursor fileInfoCursor) {
//        String fileName = null;
//        try {
//            int nameIndex;
//            if (fileInfoCursor != null) {
//                nameIndex = fileInfoCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//                fileInfoCursor.moveToFirst();
//
//                if (nameIndex != -1) {
//                    fileName = fileInfoCursor.getString(nameIndex);
//                }
//            }
//        } catch (CursorIndexOutOfBoundsException e) {
//            Logger.secE(TAG, "copyFileFromUri: e :" + e.toString());
//        }
//        if (!TextUtils.isEmpty(fileName)) {
//            fileName = fileName.replace("/", "");
//        }
//        return fileName;
//    }
//
//    private static String getFileNameFromPath(String path) {
//        return path.substring(path.lastIndexOf(File.separator) + 1);
//    }
//}
