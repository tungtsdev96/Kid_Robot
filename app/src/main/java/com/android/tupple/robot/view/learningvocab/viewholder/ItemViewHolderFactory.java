package com.android.tupple.robot.view.learningvocab.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.utils.GlideUtils;
import com.android.tupple.robot.utils.constant.LearnVocabConstant;
import com.android.tupple.robot.view.learningvocab.item.LearnVocabItem;

/**
 * Created by tungts on 2020-01-18.
 */

public class ItemViewHolderFactory {

    public abstract static class LearnVocabBaseViewHolder extends RecyclerView.ViewHolder {

        public ImageView btnPronounce;

        LearnVocabBaseViewHolder(@NonNull View itemView) {
            super(itemView);
            btnPronounce = itemView.findViewById(R.id.btn_pronounce);
        }

        public abstract void bind(LearnVocabItem learnVocabItem);
    }

    static class TextVocabViewHolder extends LearnVocabBaseViewHolder {

        private TextView textVocab;

        TextVocabViewHolder(@NonNull View itemView) {
            super(itemView);
            textVocab = itemView.findViewById(R.id.text_vocabulary);
        }

        @Override
        public void bind(LearnVocabItem learnVocabItem) {
            textVocab.setText(learnVocabItem.getVocabEn());
        }
    }

    static class ImageVocabViewHolder extends LearnVocabBaseViewHolder {

        private ImageView imageVocabulary;

        ImageVocabViewHolder(@NonNull View itemView) {
            super(itemView);
            imageVocabulary = itemView.findViewById(R.id.image_vocabulary);
        }

        @Override
        public void bind(LearnVocabItem learnVocabItem) {
            GlideUtils.loadImageFromStorage(itemView.getContext(), learnVocabItem.getVocabImage(), imageVocabulary);
        }
    }

   static class TextAndImageVocabViewHolder extends LearnVocabBaseViewHolder {

        private TextView textVocab;
        private ImageView imageVocab;

        TextAndImageVocabViewHolder(@NonNull View itemView) {
            super(itemView);
            textVocab = itemView.findViewById(R.id.text_vocabulary);
            imageVocab = itemView.findViewById(R.id.image_vocabulary);
        }

        @Override
        public void bind(LearnVocabItem learnVocabItem) {
            textVocab.setText(learnVocabItem.getVocabEn());
            GlideUtils.loadImageFromStorage(itemView.getContext(), learnVocabItem.getVocabImage(), imageVocab);
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