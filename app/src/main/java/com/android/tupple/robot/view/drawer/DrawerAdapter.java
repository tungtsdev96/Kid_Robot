package com.android.tupple.robot.view.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.data.MenuItemData;
import com.android.tupple.robot.utils.GlideUtils;
import com.android.tupple.robot.utils.ResourceUtils;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-01-13.
 */

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerItemViewHolder> {

    private List<DrawerMenuItem> mItems = new ArrayList<>();

    private Context mContext;

    public DrawerAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public DrawerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DrawerItemViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_drawer, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerItemViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onViewRecycled(@NonNull DrawerItemViewHolder holder) {
        super.onViewRecycled(holder);
        GlideUtils.clear(mContext, holder.mIconMenuItem);
    }

    public void setListMenu(List<MenuItemData> items) {
        mItems.clear();
        for (int i = 0; i < 200; i++) {
            for (MenuItemData item : items) {
                this.mItems.add(new DrawerMenuItem(item));
            }
        }
        notifyDataSetChanged();
    }

    class DrawerItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIconMenuItem;
        private TextView mTitle;

        public DrawerItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mIconMenuItem = itemView.findViewById(R.id.icon_item_menu);
            mTitle = itemView.findViewById(R.id.tv_menu_title);
        }

        void bind(DrawerMenuItem menuItem) {
            mTitle.setText(menuItem.getTitle());

            int drawableRes = ResourceUtils.convertNameToDrawableRes(mContext, menuItem.getIcon());
            RequestManager requestManager = GlideUtils.getRequestManager(mContext);
            if (requestManager != null) {
                requestManager.load(drawableRes).into(mIconMenuItem);
            }
        }

    }

}
