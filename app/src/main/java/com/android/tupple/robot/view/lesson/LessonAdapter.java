package com.android.tupple.robot.view.lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.LessonData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-01-18.
 */

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ItemLessonViewHolder> {

    private Context mContext;
    private List<LessonData> mItems = new ArrayList<>();

    public LessonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public interface ItemLessonListener {

        void onClicked(int position);

        void onLongClicked(int position);

    }

    private ItemLessonListener mOnItemLessonListener;

    public void setOnItemLessonListener(ItemLessonListener onItemLessonListener) {
        this.mOnItemLessonListener = onItemLessonListener;
    }

    @NonNull
    @Override
    public ItemLessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemLessonViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_lesson, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLessonViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setListData(List<LessonData> lessons) {
        mItems.clear();
        mItems.addAll(lessons);
        notifyDataSetChanged();
    }

    public LessonData getLessonByPosition(int pos) {
        if (pos < 0) {
            return null;
        }
        return mItems.get(pos);
    }

    class ItemLessonViewHolder extends RecyclerView.ViewHolder {

        private TextView lessonNumber;
        private ImageView imgLock;
        private TextView totalVocab;

        public ItemLessonViewHolder(@NonNull View itemView) {
            super(itemView);
            lessonNumber = itemView.findViewById(R.id.lesson_number);
            imgLock = itemView.findViewById(R.id.img_lock);
            totalVocab = itemView.findViewById(R.id.total_vocab);
        }

        void bind(LessonData lessonData) {
            boolean isLearning = lessonData.isLearning();
            imgLock.setVisibility(!isLearning ? View.VISIBLE: View.GONE);
            itemView.setEnabled(isLearning);
            totalVocab.setText(String.format(mContext.getString(R.string.text_total_vocabulary), lessonData.getTotalVocab()));
            lessonNumber.setBackgroundResource(isLearning ? R.drawable.bg_item_lesson_enable : R.drawable.bg_item_lesson_disable);
            lessonNumber.setText(isLearning ? String.format(mContext.getString(R.string.text_lesson_number), lessonData.getLessonPriority()): "");

            itemView.setOnClickListener(v -> {
                if(mOnItemLessonListener != null) {
                    mOnItemLessonListener.onClicked(getAdapterPosition());
                }
            });
        }
    }

}
