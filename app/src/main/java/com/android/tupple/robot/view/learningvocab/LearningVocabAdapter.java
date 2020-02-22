package com.android.tupple.robot.view.learningvocab;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.utils.constant.LearnVocabConstant;
import com.android.tupple.robot.view.learningvocab.item.LearnVocabImage;
import com.android.tupple.robot.view.learningvocab.item.LearnVocabItem;
import com.android.tupple.robot.view.learningvocab.item.LearnVocabText;
import com.android.tupple.robot.view.learningvocab.item.LearnVocabTextAndImage;
import com.android.tupple.robot.view.learningvocab.viewholder.ItemViewHolderFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-01-18.
 */

public class LearningVocabAdapter extends RecyclerView.Adapter<ItemViewHolderFactory.LearnVocabBaseViewHolder> {

    private Context mContext;
    private List<LearnVocabItem> mLearnVocabItem = new ArrayList<>();


    LearningVocabAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ItemViewHolderFactory.LearnVocabBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ItemViewHolderFactory.create(mContext, parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolderFactory.LearnVocabBaseViewHolder holder, int position) {
        holder.bind(mLearnVocabItem.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return LearnVocabConstant.LEARN_TYPE.IMAGE;
            case 1:
                return LearnVocabConstant.LEARN_TYPE.TEXT;
            case 2:
                return LearnVocabConstant.LEARN_TYPE.TEXT_AND_IMAGE;
        }

        return -1;
    }

    @Override
    public int getItemCount() {
        return mLearnVocabItem.size();
    }

    void setCurrentVocabLearning(Vocabulary currentVocab) {
        mLearnVocabItem.clear();
        mLearnVocabItem.add(new LearnVocabImage(currentVocab));
        mLearnVocabItem.add(new LearnVocabText(currentVocab));
        mLearnVocabItem.add(new LearnVocabTextAndImage(currentVocab));
        notifyDataSetChanged();
    }
}
