package com.android.tupple.robot.view.learningvocab;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.utils.constant.LearnVocabConstant;
import com.android.tupple.robot.view.learningvocab.viewholder.ItemViewholderFactory;

/**
 * Created by tungts on 2020-01-18.
 */

public class LearningVocabAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public LearningVocabAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ItemViewholderFactory.create(mContext, parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return LearnVocabConstant.LEARN_TYPE.TEXT;
            case 1:
                return LearnVocabConstant.LEARN_TYPE.IMAGE;
            case 2:
                return LearnVocabConstant.LEARN_TYPE.TEXT_AND_IMAGE;
        }

        return -1;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
