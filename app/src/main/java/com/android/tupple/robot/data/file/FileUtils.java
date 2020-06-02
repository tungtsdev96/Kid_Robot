package com.android.tupple.robot.data.file;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by tungts on 2020-02-17.
 */

public class FileUtils {
    public static void storeImage(Context context, Bitmap image, String name) {
        File pictureFile = new File(context.getCacheDir(), name + ".png");
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            Log.d("FileUtils", "done");
        } catch (FileNotFoundException e) {
            Log.d("FileUtils", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("FileUtils", "Error accessing file: " + e.getMessage());
        }
    }

    public static byte[] readFile(String path) {
        FileInputStream in = null;
        byte[] bytes = null;
        try {
            File file = new File(path);
            bytes = new byte[(int) file.length()];
            in = new FileInputStream(file);
            in.read(bytes);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void writeFile(String path, String name, InputStream in) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(path, name), true);
            byte[] data = new byte[4069];
            int count;
            while ((count = in.read(data)) != -1) {
                out.write(data, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static File createDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            boolean isDir = dir.mkdirs();
            if (!isDir) return null;
        }
        return dir;
    }

    public static boolean deleteFile(File f) {
        if (f.exists()) {
            return f.delete();
        }
        return true;
    }

    public static void convertBytesToFile(File f, byte[] bytes) {
        try {

            // Initialize a pointer
            // in file using OutputStream
            OutputStream
                    os
                    = new FileOutputStream(f);

            // Starts writing the bytes in it
            os.write(bytes);
            System.out.println("Successfully"
                    + " byte inserted");

            // Close the file
            os.close();
        }

        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

}
