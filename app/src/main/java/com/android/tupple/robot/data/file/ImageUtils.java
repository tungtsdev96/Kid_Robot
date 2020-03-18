package com.android.tupple.robot.data.file;

import android.util.Log;

import com.android.tupple.robot.utils.RandomUtils;

import java.io.File;

/**
 * Created by tungts on 2020-03-04.
 */

public class ImageUtils {

    public static String getUrlImageTopic(String name, int topicId) {
        return FileConstants.FOLDER_DATA + "/topic/" + topicId + "/" + name.toLowerCase() + "/image/" + name.toLowerCase() + ".jpg";
    }

    public static String getUrlImageLesson(String name, int lessonId, int totalImage) {
        int image = RandomUtils.randInt(0, totalImage - 1);
        File f = new File(FileConstants.FOLDER_DATA + "/lesson/" + lessonId  + "/" + name.toLowerCase() + "/image");
        if (!f.exists() || f.listFiles() == null) {
            Log.d("ImageUtils", "null");
            return null;
        }
        return f.listFiles()[image].getAbsolutePath();
    }

}
