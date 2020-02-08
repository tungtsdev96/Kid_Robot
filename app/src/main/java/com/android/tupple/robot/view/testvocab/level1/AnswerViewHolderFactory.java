package com.android.tupple.robot.view.testvocab.level1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.utils.GlideUtils;
import com.android.tupple.robot.utils.constant.TestVocabConstant;
import com.android.tupple.robot.view.testvocab.level1.item.AnswerItem;
import com.android.tupple.robot.view.testvocab.level1.item.AnswerTextAndImageItem;
import com.android.tupple.robot.view.testvocab.level1.item.AnswerTextItem;

import java.util.Objects;

/**
 * Created by tungts on 2020-02-08.
 */

public class AnswerViewHolderFactory {

    abstract static class AnswerItemViewHolder extends RecyclerView.ViewHolder {

        AnswerAdapter.AnswerClickListener mOnAnswerClickListener;
        FrameLayout mAnswerItem;

        AnswerItemViewHolder(@NonNull View itemView, AnswerAdapter.AnswerClickListener onAnswerClickListener) {
            super(itemView);
            mAnswerItem = itemView.findViewById(R.id.item_answer);
            this.mOnAnswerClickListener = onAnswerClickListener;
            mAnswerItem.setOnClickListener(v -> {
                if (mOnAnswerClickListener != null) {
                    mOnAnswerClickListener.onClick(getAdapterPosition());
                }
            });
        }

        abstract void bind(AnswerItem item);

    }

    static class AnswerTextItemViewHolder extends AnswerItemViewHolder {

        private ImageView imageAnswer;

        AnswerTextItemViewHolder(@NonNull View itemView, AnswerAdapter.AnswerClickListener onAnswerClickListener) {
            super(itemView, onAnswerClickListener);
            imageAnswer = itemView.findViewById(R.id.image_answer);
            imageAnswer.setVisibility(View.VISIBLE);
        }

        @Override
        void bind(AnswerItem item) {
            Objects.requireNonNull(GlideUtils.getRequestManager(itemView.getContext())).load(R.drawable.a).into(imageAnswer);
        }

    }

    static class AnswerImageItemViewHolder extends AnswerItemViewHolder {

        private TextView textAnswer;

        AnswerImageItemViewHolder(@NonNull View itemView, AnswerAdapter.AnswerClickListener onAnswerClickListener) {
            super(itemView, onAnswerClickListener);
            textAnswer = itemView.findViewById(R.id.text_answer);
            textAnswer.setVisibility(View.VISIBLE);
        }

        @Override
        void bind(AnswerItem item) {
            textAnswer.setText(((AnswerTextItem) item).getText());
        }

    }

    static class AnswerTextAndImageItemViewHolder extends AnswerItemViewHolder {

        private TextView textAnswer;
        private ImageView imageAnswer;

        AnswerTextAndImageItemViewHolder(@NonNull View itemView, AnswerAdapter.AnswerClickListener onAnswerClickListener) {
            super(itemView, onAnswerClickListener);
            textAnswer = itemView.findViewById(R.id.text_answer);
            imageAnswer = itemView.findViewById(R.id.image_answer);
            textAnswer.setVisibility(View.VISIBLE);
            imageAnswer.setVisibility(View.VISIBLE);
        }

        @Override
        void bind(AnswerItem item) {
            AnswerTextAndImageItem textAndImageItem = (AnswerTextAndImageItem) item;

            if (textAndImageItem == null) {
                return;
            }

            textAnswer.setText(textAndImageItem.getVocabulary().vocabEn);
            Objects.requireNonNull(GlideUtils.getRequestManager(itemView.getContext())).load(R.drawable.a).into(imageAnswer);
        }

    }

    public static AnswerItemViewHolder create(ViewGroup parent, int type, AnswerAdapter.AnswerClickListener onAnswerClickListener) {
        switch (type) {
            default:
            case TestVocabConstant.ANSWER_TYPE.TEXT:
                return new AnswerTextItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_answer_level_1, parent, false), onAnswerClickListener);
            case TestVocabConstant.ANSWER_TYPE.IMAGE:
                return new AnswerImageItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_answer_level_1, parent, false), onAnswerClickListener);
            case TestVocabConstant.ANSWER_TYPE.TEXT_AND_IMAGE:
                return new AnswerTextAndImageItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_answer_level_1, parent, false), onAnswerClickListener);
        }
    }

}
