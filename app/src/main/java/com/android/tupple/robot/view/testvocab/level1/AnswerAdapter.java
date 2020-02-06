package com.android.tupple.robot.view.testvocab.level1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.utils.constant.TestVocabConstant;
import com.android.tupple.robot.view.testvocab.level1.item.AnswerItem;
import com.android.tupple.robot.view.testvocab.level1.item.AnswerTextAndImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-02-06.
 */

public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<AnswerItem> mItems = new ArrayList<>();

    public AnswerAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnswerTextItemViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_answer_level_1, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            if (mOnAnswerClickListener != null) {
                mOnAnswerClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setListAnswer(List<Vocabulary> listAnswer, int positionAnswer, int typeAnswer) {
        mItems.clear();
        for (int i = 0; i < listAnswer.size(); i++) {
            switch (typeAnswer) {
                case TestVocabConstant.ANSWER_TYPE.TEXT:
                case TestVocabConstant.ANSWER_TYPE.IMAGE:
                case TestVocabConstant.ANSWER_TYPE.TEXT_AND_IMAGE:
                    AnswerItem item = AnswerTextAndImageItem.create(listAnswer.get(i), i == positionAnswer);
                    mItems.add(item);
            }
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
