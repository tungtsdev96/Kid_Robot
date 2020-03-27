package com.android.tupple.robot.view.audiolist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.customview.snaprecycleview.SnapRecycleView;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import es.claucookie.miniequalizerlibrary.EqualizerView;

public class AudioListFragment extends Fragment implements AudioListView<Media> {
    public static final String TAG = "AudioListFragment";
    private Context mContext;
    private CleanObserver<AudioListView<Media>> mViewCreatedObserver;
    private SnapRecycleView mRecyclerViewAudio;
    private CleanObserver<Media> mItemAudioClickedObserver;
    private RecyclerViewAudioAdapter recyclerViewAudioAdapter;
    //
    private Activity mActivity;
    private SeekBar mSeekbar;
    private TextView txtCurrentTime, txtTotalTime, txtMediaTitle;
    private FloatingActionButton btnPrevious, btnNext, btnPlayStop;
    EqualizerView equalizer;
    private MediaPlayer mMediaPlayer;
    private Media mCurrentAudio;

    private CleanObserver mNextButtonClickedObserver;
    private CleanObserver mPreviousButtonClickedObserver;
    private CleanObserver mStopPlayButtonClickedObserver;

    //
    public void setViewCreatedObserver(CleanObserver<AudioListView<Media>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }

    public static AudioListFragment newInstance() {
        return new AudioListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_audio_list, container, false);
        initView(mRootView);
        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        btnPlayStop.setEnabled(false);
        return mRootView;
    }

    private void handleItemAudioClicked(int position) {
        if (mItemAudioClickedObserver != null) {
            mItemAudioClickedObserver.onNext(recyclerViewAudioAdapter.getAudioByPosition(position));
        }
       // recyclerViewAudioAdapter.set
    }

    private void initView(View rootView) {
        mRecyclerViewAudio = rootView.findViewById(R.id.recycler_audio);
        recyclerViewAudioAdapter = new RecyclerViewAudioAdapter(mContext);
        mRecyclerViewAudio.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        mRecyclerViewAudio.setAdapter(recyclerViewAudioAdapter);
        recyclerViewAudioAdapter.setOnItemAudioClickedListener(this::handleItemAudioClicked);
        mSeekbar = rootView.findViewById(R.id.seekbar);
        txtCurrentTime = rootView.findViewById(R.id.current_time);
        txtTotalTime = rootView.findViewById(R.id.total_time);
        btnPlayStop = rootView.findViewById(R.id.btn_play);
        btnPrevious = rootView.findViewById(R.id.btn_previous);
        btnNext = rootView.findViewById(R.id.btn_next);
        txtMediaTitle = rootView.findViewById(R.id.txt_title);
        equalizer = rootView.findViewById(R.id.equalizer_view);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AudioListPresenterImpl", "ahji");
                if (mPreviousButtonClickedObserver != null)
                    mPreviousButtonClickedObserver.onNext();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNextButtonClickedObserver != null)
                    mNextButtonClickedObserver.onNext();
            }
        });
        btnPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStopPlayButtonClickedObserver != null)
                    mStopPlayButtonClickedObserver.onNext();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void setListAudio(List<Media> listMedia) {
        recyclerViewAudioAdapter.setListData(listMedia);
    }



    @Override
    public void setCurrentAudio(Media media) {
        this.mCurrentAudio = media;
        txtMediaTitle.setText(media.getTitle());
        Log.d("Adapterr", media.getId()+" ") ;
        recyclerViewAudioAdapter.changeBackgroundItemClicked(media);
        //mRecyclerViewAudio.scrollToPosition(media.getId()+1);
    }

    @Override
    public void preparePlayer() {
        btnPlayStop.setEnabled(true);
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.reset();
        }
        //mMediaPlayer.reset();
        Log.d("AudioListPresenterImpl", mCurrentAudio.getMedia_url());
        Uri audioUri = Uri.parse(mCurrentAudio.getMedia_url());
        Log.d(TAG, mCurrentAudio.getMedia_url());
        mMediaPlayer = MediaPlayer.create(this.getActivity(), audioUri);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                String totalTime = createTimeLabel(mMediaPlayer.getDuration());
                txtTotalTime.setText(totalTime);
                mSeekbar.setMax(mMediaPlayer.getDuration());
                mMediaPlayer.start();
                equalizer.animateBars();
                btnPlayStop.setImageResource(R.drawable.stopbutton);

            }
        });
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser) {
                    mMediaPlayer.seekTo(progress);
                    mSeekbar.setProgress(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mMediaPlayer != null) {
                    try {
                        if (mMediaPlayer.isPlaying()) {
                            Message msg = new Message();
                            msg.what = mMediaPlayer.getCurrentPosition();
                            handler.sendMessage(msg);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @Override
    public void playAudio() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
            equalizer.animateBars();
            btnPlayStop.setImageResource(R.drawable.stopbutton);
        }
    }

    @Override
    public void changeIconStopPlay(boolean isPlay) {
        if (isPlay) {
            btnPlayStop.setImageResource(R.drawable.stopbutton);
        } else btnPlayStop.setImageResource(R.drawable.playbutton);
    }

    @Override
    public void pauseAudio() {
        pause();
    }

    private void pause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            btnPlayStop.setImageResource(R.drawable.playbutton);
            equalizer.stopBars();
        }

    }

    @Override
    public void stopAudio() {
        mMediaPlayer.stop();
    }

    @Override
    public void scrollToItem(int position) {
        mRecyclerViewAudio.getLayoutManager().scrollToPosition(position);
    }

    @Override
    public void stopPlayer() {
        mMediaPlayer.stop();
    }


    @Override
    public CleanObservable<Media> getItemAudioClickedObservable() {
        return CleanObservable.create(cleanObserver -> mItemAudioClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getNextButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mNextButtonClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getPreviousButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mPreviousButtonClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getStopPlayButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mStopPlayButtonClickedObserver = cleanObserver);

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int current_position = msg.what;
            mSeekbar.setProgress(current_position);
            String cTime = createTimeLabel(current_position);
            txtCurrentTime.setText(cTime);
        }
    };

    public String createTimeLabel(int duration) {
        String timeLabel = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        timeLabel += min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;

    }
}
