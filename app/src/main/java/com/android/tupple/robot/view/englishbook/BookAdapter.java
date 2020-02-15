package com.android.tupple.robot.view.englishbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.SchoolBook;

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

    public interface OnBookFragmentItemClickListener {

        void onItemBookClicked(int position);

        void onItemBookLongClicked(int positon);

        void onButtonDownloadClicked(int position);

    }

    private OnBookFragmentItemClickListener mOnBookFragmentItemClickListener;

    public void setOnBookFragmentItemClickListener(OnBookFragmentItemClickListener onBookFragmentItemClickListener) {
        this.mOnBookFragmentItemClickListener = onBookFragmentItemClickListener;
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
        holder.bind(mListBooks.get(position));

        holder.itemContainer.setOnClickListener(v -> {
            if (mOnBookFragmentItemClickListener != null) {
                mOnBookFragmentItemClickListener.onItemBookClicked(position);
            }
        });

        holder.btnDownload.setOnClickListener(v -> {
            if (mOnBookFragmentItemClickListener != null) {
                mOnBookFragmentItemClickListener.onButtonDownloadClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListBooks.size();
    }

    public SchoolBook getBookByPosition(int position) {
        if (position < 0) {
            return null;
        }
        return mListBooks.get(position);
    }

    public void setListBooks(List<SchoolBook> listBooks) {
        mListBooks.clear();
        mListBooks.addAll(listBooks);
        notifyDataSetChanged();
    }

    class ItemBookViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageBook;
        private Button btnDownload;
        private View itemContainer;
        private TextView textTitleBook;
        private ProgressBar progressDownload;

        ItemBookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.image_school_book);
            btnDownload = itemView.findViewById(R.id.btn_download_school_book);
            itemContainer = itemView.findViewById(R.id.image_school_book_mask);
            textTitleBook = itemView.findViewById(R.id.text_title_school_book);
            progressDownload = itemView.findViewById(R.id.progress_download);
        }

        void bind(SchoolBook book) {
            textTitleBook.setText(book.getNameBook());
        }

    }

}
