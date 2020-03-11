package com.android.tupple.robot.data.file;

import com.android.tupple.robot.utils.RandomUtils;

/**
 * Created by tungts on 2020-03-04.
 */

public class ImageUtils {

    public static String getUrlImageTopic(String name, int topicId) {
        return FileConstants.FOLDER_DATA + "/topic/" + topicId + "/" + name.toLowerCase() + "/image/" + name.toLowerCase() + ".jpg";
    }

    public static String getUrlImageLesson(String name, int lessonId, int totalImage) {
        int image = RandomUtils.randInt(1, totalImage);
        return FileConstants.FOLDER_DATA + "/lesson/" + lessonId  + "/" + name.toLowerCase() + "/image/" + image + ".jpg";
    }

}
