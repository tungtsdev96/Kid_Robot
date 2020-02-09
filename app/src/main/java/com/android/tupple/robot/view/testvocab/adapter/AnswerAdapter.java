package com.android.tupple.robot.view.testvocab.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.utils.constant.TestVocabConstant;
import com.android.tupple.robot.view.testvocab.adapter.item.AnswerImageItem;
import com.android.tupple.robot.view.testvocab.adapter.item.AnswerItem;
import com.android.tupple.robot.view.testvocab.adapter.item.AnswerTextAndImageItem;
import com.android.tupple.robot.view.testvocab.adapter.item.AnswerTextItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-02-06.
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerViewHolderFactory.AnswerItemViewHolder> {

    private Context mContext;
    private List<AnswerItem> mItems = new ArrayList<>();

    public AnswerAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public AnswerViewHolderFactory.AnswerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AnswerViewHolderFactory.create(parent, viewType, mOnAnswerClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolderFactory.AnswerItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    public void setListAnswer(List<Vocabulary> listAnswer, int typeAnswer) {
        mItems.clear();
        for (Vocabulary vocabulary: listAnswer) {
            AnswerItem item;
            switch (typeAnswer) {
                case TestVocabConstant.ANSWER_TYPE.TEXT:
                    item = AnswerTextItem.create(vocabulary);
                    break;
                case TestVocabConstant.ANSWER_TYPE.IMAGE:
                    item = AnswerImageItem.create(vocabulary);
                    break;
                case TestVocabConstant.ANSWER_TYPE.TEXT_AND_IMAGE:
                    item = AnswerTextAndImageItem.create(vocabulary);
                    break;
                default:
                    item = null;
            }
            mItems.add(item);
        }

        notifyDataSetChanged();
    }

    public AnswerItem getAnswerItemByPosititon(int pos) {
        if (pos < 0 || mItems.isEmpty()) {
            return null;
        }

        return mItems.get(pos);
    }

    public interface AnswerClickListener {
        void onClick(int position);
    }

    private AnswerClickListener mOnAnswerClickListener;

    public void setOnAnswerClickListener(AnswerClickListener onAnswerClickListener) {
        this.mOnAnswerClickListener = onAnswerClickListener;
    }
}
