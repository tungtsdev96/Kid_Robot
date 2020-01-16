package com.android.tupple.robot.view.englishbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.commondata.SchoolBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-01-16.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ItemBookViewHolder> {

    private Context mContext;

    private List<SchoolBook> mListBooks = new ArrayList<>();

    public BookAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ItemBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemBookViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_school_book, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ItemBookViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mListBooks.size();
    }

    public void setListBooks(List<SchoolBook> listBooks) {
        mListBooks.clear();
        mListBooks.addAll(listBooks);
        notifyDataSetChanged();
    }

    class ItemBookViewHolder extends RecyclerView.ViewHolder {

        public ItemBookViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
