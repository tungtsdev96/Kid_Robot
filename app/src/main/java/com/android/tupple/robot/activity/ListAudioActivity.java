package com.android.tupple.robot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.model.mediaobject.AudioListModelFactory;
import com.android.tupple.robot.domain.entity.entertainment.Entertainment;
import com.android.tupple.robot.domain.entity.medialist.MediaList;
import com.android.tupple.robot.domain.presenter.listaudio.AudioListModel;
import com.android.tupple.robot.domain.presenter.listaudio.AudioListPresenterImpl;
import com.android.tupple.robot.domain.presenter.listaudio.AudioListView;
import com.android.tupple.robot.view.listaudio.ListAudioViewFactory;

public class ListAudioActivity extends BaseActivity {
    private ActivityLauncher activityLauncher;
    private MediaList mediaList;


    @Override
    protected int getLayoutContent() {
        return R.layout.activity_list_audio;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        Bundle bundle = null;
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getExtras();
        }
        injectFirstBatch();
        inject(bundle);
    }

    private void inject(Bundle bundle) {
        AudioListPresenterImpl<Media> audioListPresenter = new AudioListPresenterImpl<>();
        AudioListModel<Media> audioListModel = AudioListModelFactory.newAudioListModel(this);
        AudioListView<Media> audioListView = ListAudioViewFactory.newAudioListView(this,bundle);
        audioListPresenter.setAudioListModel(audioListModel);
        audioListPresenter.setmAudioListView(audioListView);
        mediaList.setMediaListPresenter(audioListPresenter);
        mediaList.init();
    }

    private void injectFirstBatch() {
        activityLauncher = new ActivityLauncher(this);
        mediaList = new MediaList();
    }

}
