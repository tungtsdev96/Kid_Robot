package com.android.tupple.robot.data.file;

/**
 * Created by tungts on 2020-03-04.
 */

public class ImageUtils {

    public static String getUrlImageTopic(String name, int idTopic) {
        return FileConstants.FOLDER_DATA + "/topic/" + idTopic + "/" + name.toLowerCase() + "/image/" + name.toLowerCase() + ".jpg";
    }

}
