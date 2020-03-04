package com.android.tupple.robot.view.testvocab;

import android.util.Log;
import android.util.TypedValue;
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
import com.android.tupple.robot.view.testvocab.adapter.AnswerAdapter;
import com.android.tupple.robot.view.testvocab.adapter.item.AnswerChoose;
import com.android.tupple.robot.view.testvocab.adapter.item.AnswerImageItem;
import com.android.tupple.robot.view.testvocab.adapter.item.AnswerItem;
import com.android.tupple.robot.view.testvocab.adapter.item.AnswerTextAndImageItem;
import com.android.tupple.robot.view.testvocab.adapter.item.AnswerTextItem;

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
        }

        abstract void bind(AnswerItem item);

    }

    static class AnswerTextItemViewHolder extends AnswerItemViewHolder {

        private TextView textAnswer;

        AnswerTextItemViewHolder(@NonNull View itemView, AnswerAdapter.AnswerClickListener onAnswerClickListener) {
            super(itemView, onAnswerClickListener);
            textAnswer = itemView.findViewById(R.id.text_answer);
            textAnswer.setVisibility(View.VISIBLE);
        }

        @Override
        void bind(AnswerItem item) {
            textAnswer.setText(((AnswerImageItem) item).getText());
            boolean isChoose = ((AnswerChoose) item).isChoose();
            boolean isAnswer = item.isAnswerTrue();
            if (isChoose) mAnswerItem.setBackgroundResource(R.drawable.border_quiz_choose);
            else {
                TypedValue outValue = new TypedValue();
                itemView.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                mAnswerItem.setBackgroundResource(outValue.resourceId);
            }

            if (isAnswer) {
                mAnswerItem.setBackgroundResource(R.drawable.border_quiz_true);
                mAnswerItem.setOnClickListener(null);
            }
        }

    }

    static class AnswerImageItemViewHolder extends AnswerItemViewHolder {

        private ImageView imageAnswer;

        AnswerImageItemViewHolder(@NonNull View itemView, AnswerAdapter.AnswerClickListener onAnswerClickListener) {
            super(itemView, onAnswerClickListener);
            imageAnswer = itemView.findViewById(R.id.image_answer);
            imageAnswer.setVisibility(View.VISIBLE);
        }

        @Override
        void bind(AnswerItem item) {
            Objects.requireNonNull(GlideUtils.getRequestManager(itemView.getContext())).load(R.drawable.a).into(imageAnswer);
            boolean isChoose = ((AnswerChoose) item).isChoose();
            boolean isAnswer = item.isAnswerTrue();
            if (isChoose) mAnswerItem.setBackgroundResource(R.drawable.border_quiz_choose);
            else {
                TypedValue outValue = new TypedValue();
                itemView.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                mAnswerItem.setBackgroundResource(outValue.resourceId);
            }

            if (isAnswer) {
                mAnswerItem.setBackgroundResource(R.drawable.border_quiz_true);
                mAnswerItem.setOnClickListener(null);
            }
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

            textAnswer.setText(textAndImageItem.getVocabulary().getVocabEn());
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
