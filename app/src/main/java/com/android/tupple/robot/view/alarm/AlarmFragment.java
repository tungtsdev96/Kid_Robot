package com.android.tupple.robot.view.alarm;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.tupple.robot.R;
import com.android.tupple.robot.domain.presenter.alarm.AlarmView;

/**
 * Created by tungts on 2/14/2020.
 */

public class AlarmFragment extends Fragment implements AlarmView {

    public static final String TAG = "AlarmFragment";

    private Context mContext;

    public static AlarmFragment newInstance() {
        return new AlarmFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_alarm, container, false);
        return rootView;
    }
}
