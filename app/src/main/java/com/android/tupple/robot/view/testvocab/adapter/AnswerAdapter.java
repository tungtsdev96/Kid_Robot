package com.android.tupple.robot.view.testvocab.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.common.sound.SoundPoolManagement;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.utils.constant.TestVocabConstant;
import com.android.tupple.robot.view.testvocab.adapter.item.AnswerChoose;
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
    private boolean mIsCanSelected = true;

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
        holder.bind(mItems.get(position));
        if (mIsCanSelected) {
            holder.mAnswerItem.setOnClickListener(v -> {
                SoundPoolManagement.getInstance().playSound(mItems.get(position).getVocabId());
                if (mOnAnswerClickListener != null) {
                    mOnAnswerClickListener.onClick(position);
                }
            });
        } else {
            holder.mAnswerItem.setOnClickListener(null);
        }
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
        mIsCanSelected = true;
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

    public void updateAnswerSelected(int position) {
        if (mItems.get(position).getType() == TestVocabConstant.ANSWER_TYPE.TEXT_AND_IMAGE) {
            return;
        }

        for (int i = 0; i < mItems.size(); i++) {
            AnswerChoose item = (AnswerChoose) mItems.get(i);
            item.setChoose(position == i);
        }

        notifyDataSetChanged();
    }

    public void updateAnswer(boolean isRight, int resultPosition) {
        if (mItems.get(resultPosition).getType() == TestVocabConstant.ANSWER_TYPE.TEXT_AND_IMAGE) {
            return;
        }

        mIsCanSelected = false;
        ((AnswerChoose) mItems.get(resultPosition)).setAnswer(true);
        notifyDataSetChanged();
    }

    public interface AnswerClickListener {
        void onClick(int position);
    }

    private AnswerClickListener mOnAnswerClickListener;

    public void setOnAnswerClickListener(AnswerClickListener onAnswerClickListener) {
        this.mOnAnswerClickListener = onAnswerClickListener;
    }
}
