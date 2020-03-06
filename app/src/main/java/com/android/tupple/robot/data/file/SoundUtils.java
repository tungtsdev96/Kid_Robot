package com.android.tupple.robot.data.file;

/**
 * Created by tungts on 2020-03-04.
 */

public class SoundUtils {

    public static String getUrlSoundTopic(String name, int idTopic) {
        return FileConstants.FOLDER_DATA + "/topic/" + idTopic + "/" + name.toLowerCase() + "/sound/" + name.toLowerCase() + ".mp3";
    }

}
