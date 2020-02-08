package com.android.tupple.robot.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-01-15.
 */

public class Vocabulary {

    public int vocabId;
    public String vocabEn;
    public String vocabVi;
    public String image;
    public String audio;
    public int scoreCorrect;
    public int scoreWrong;
    public int topicId = -1;
    public int lessonId = -1;

    public static List<Vocabulary> fake() {
        List<Vocabulary> vocabularies = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Vocabulary vocabulary = new Vocabulary();
            vocabulary.vocabId = 1;
            vocabulary.vocabVi = "ads " + i;
            vocabulary.vocabEn = "vocabEn " + i;
            vocabularies.add(vocabulary);
        }

        return vocabularies;
    }

}
