package com.android.tupple.robot.view.learningvocab.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.utils.constant.LearnVocabConstant;
import com.android.tupple.robot.view.learningvocab.item.LearnVocabItem;

/**
 * Created by tungts on 2020-01-18.
 */

public class ItemViewholderFactory {

    public abstract static class LearnVocabBaseViewHolder extends RecyclerView.ViewHolder {

        LearnVocabBaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void bind(LearnVocabItem learnVocabItem);
    }

    static class TextVocabViewHolder extends LearnVocabBaseViewHolder {

        TextVocabViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bind(LearnVocabItem learnVocabItem) {

        }
    }

    static class ImageVocabViewHolder extends LearnVocabBaseViewHolder {

        ImageVocabViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bind(LearnVocabItem learnVocabItem) {

        }
    }

   static class TextAndImageVocabViewHolder extends LearnVocabBaseViewHolder {

        TextAndImageVocabViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bind(LearnVocabItem learnVocabItem) {

        }
    }

    public static LearnVocabBaseViewHolder create(Context context, ViewGroup parent, int learnType) {
        switch (learnType) {
            default:
            case LearnVocabConstant.LEARN_TYPE.IMAGE:
                return new ImageVocabViewHolder(LayoutInflater.from(context).inflate(R.layout.item_learn_vocab_image, parent, false));
            case LearnVocabConstant.LEARN_TYPE.TEXT:
                return new TextVocabViewHolder(LayoutInflater.from(context).inflate(R.layout.item_learn_vocab_text, parent, false));
            case LearnVocabConstant.LEARN_TYPE.TEXT_AND_IMAGE:
                return new TextAndImageVocabViewHolder(LayoutInflater.from(context).inflate(R.layout.item_learn_vocab_text_and_image, parent, false));
        }
    }

}