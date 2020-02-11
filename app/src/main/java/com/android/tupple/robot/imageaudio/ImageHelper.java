package com.android.tupple.robot.imageaudio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageHelper {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public byte[] ConverImageToByteArray(String filePath) {
        byte[] data = {};
        File imageFile = new File(filePath);
        Path path = Paths.get(imageFile.getAbsolutePath());
        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void saveByteArrayToTempFile(Context context, String filename, byte[] data) {
        try {
            OutputStream fileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory() + filename);
            fileOutputStream.write(data);
            fileOutputStream.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] readByteArrayFromTempFile(Context context, String filename) {
        byte[] fileContent = {};
        try {
            File file = new File(Environment.getExternalStorageDirectory() + filename);
            InputStream fileInputStream = new FileInputStream(Environment.getExternalStorageDirectory() + filename);

            fileContent = new byte[(int) file.length()];
            fileInputStream.read(fileContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent;
    }

    public void loadImage(Context context, ImageView imageView, String filePath) {
        Glide.with(context).load(filePath).into(imageView);
    }
}
