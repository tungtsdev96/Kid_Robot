package com.android.tupple.robot.view.testvocab.level3.item;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level3.item.Level3ItemView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by tungts on 2020-02-22.
 */

public class Level3ItemFragment extends Fragment implements Level3ItemView<Vocabulary> {

    private Context mContext;

    private int mKeyView = -1;

    public static Level3ItemFragment newInstance(int keyView) {
        Level3ItemFragment fragment = new Level3ItemFragment();
        fragment.mKeyView = keyView;
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_check_prounce, container, false);
        initView(rootView);

        Log.d("tungts", "onCreateView " + mKeyView);
        EventBus.getDefault().post(new EventItemCreated(this, mKeyView));

        return rootView;
    }

    private void initView(View rootView) {

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
