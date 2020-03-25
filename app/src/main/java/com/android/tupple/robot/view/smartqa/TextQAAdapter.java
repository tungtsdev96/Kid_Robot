package com.android.tupple.robot.view.smartqa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.view.smartqa.item.TextQAItem;
import com.android.tupple.robot.view.smartqa.item.TextQARobot;
import com.android.tupple.robot.view.smartqa.item.TextQAUser;
import com.android.tupple.robot.view.smartqa.item.TypingItem;

import java.util.ArrayList;
import java.util.List;

public class TextQAAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<TextQAItem> mItems = new ArrayList<>();

    public TextQAAdapter(Context context, RecyclerView recyclerView) {
        this.mContext = context;
        this.mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        switch (viewType) {
            case 0:
                layout = R.layout.item_smartqa_left;
                break;
            case 1:
                layout = R.layout.item_smartqa_right;
            break;
            default:
            case 2:
                layout = R.layout.item_typing;
                break;
        }
        return new TextQAItemViewHolder(
                LayoutInflater.from(mContext).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextQAItem item = mItems.get(position);
        if (item instanceof TypingItem) {
            return;
        }
        ((TextQAItemViewHolder) holder).bind(item);
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addItem(int type, String textContent) {
        if (type == 0) {
            int lastPosition = mItems.isEmpty() ? -1 : mItems.size() - 1;
            if (lastPosition >= 0 && mItems.get(lastPosition) instanceof TypingItem) {
                mItems.remove(lastPosition);
                notifyItemRemoved(lastPosition);
            }
            mItems.add(new TextQARobot(textContent));
        } else if (type == 1) {
            mItems.add(new TextQAUser(textContent));
        } else {
            mItems.add(new TypingItem(textContent));
        }
        notifyItemInserted(mItems.size());
        mRecyclerView.smoothScrollToPosition(mItems.size() - 1);
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    static class TextQAItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textContent;

        public TextQAItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textContent = itemView.findViewById(R.id.text_content_qa);
        }

        void bind(TextQAItem item) {
            textContent.setText(item.getText());
        }
    }

}
