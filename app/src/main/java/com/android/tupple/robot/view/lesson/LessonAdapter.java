package com.android.tupple.robot.view.lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.data.LessonData;

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

        void onClick(int position);

        void onLongClick(int position);

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

        public ItemLessonViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                if (mOnItemLessonListener != null) {
                    mOnItemLessonListener.onClick(getAdapterPosition());
                }
            });
        }
    }

}
