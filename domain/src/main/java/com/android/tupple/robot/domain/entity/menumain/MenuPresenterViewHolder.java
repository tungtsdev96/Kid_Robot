package com.android.tupple.robot.domain.entity.menumain;

import com.android.tupple.robot.domain.log.CLog;

/**
 * Created by tung.ts on 1/15/2020.
 */

public class MenuPresenterViewHolder {

    public final String TAG = "MenuPresenterViewHolder";

    private EnglishBookPresenter mEnglishBookPresenter;
    private EnglishTopicPresenter mEnglishTopicPresenter;
    private AlarmPresenter mAlarmPresenter;
    private EntertainmentPresenter mEntertainmentPresenter;

    public MenuPresenterViewHolder() {
    }

    public void setEnglishBookPresenter(EnglishBookPresenter englishBookPresenter) {
        this.mEnglishBookPresenter = englishBookPresenter;
    }

    public void setEnglishTopicPresenter(EnglishTopicPresenter englishTopicPresenter) {
        this.mEnglishTopicPresenter = englishTopicPresenter;
    }

    public void setAlarmPresenter(AlarmPresenter alarmPresenter) {
        this.mAlarmPresenter = alarmPresenter;
    }

    public void setEntertainmentPresenter(EntertainmentPresenter entertainmentPresenter) {
        this.mEntertainmentPresenter = entertainmentPresenter;
    }

    MenuPresenter get(MenuType calendarType) {
        MenuPresenter presenter;
        switch (calendarType) {
            case ENGLISH_BOOK:
                presenter = mEnglishBookPresenter;
                break;
            case ENGLISH_TOPIC:
                presenter = mEnglishTopicPresenter;
                break;
            case ALARM_CLOCK:
                presenter = mAlarmPresenter;
                break;
            case ENTERTAINMENT:
                presenter = mEntertainmentPresenter;
                break;
            case INVALID:
            default:
                presenter = null;
        }

        if (presenter == null) {
            CLog.printSecD(TAG, "get(), presenter is null, calendarType = " + calendarType);
        }

        return presenter;
    }

    void clear() {

        if (mEnglishBookPresenter != null) {
            mEnglishBookPresenter.finish();
            mEnglishBookPresenter = null;
        }

        if (mEnglishTopicPresenter != null) {
            mEnglishTopicPresenter.finish();
            mEnglishTopicPresenter = null;
        }

        if (mAlarmPresenter != null) {
            mAlarmPresenter.finish();
            mAlarmPresenter = null;
        }

        if (mEntertainmentPresenter != null) {
            mEntertainmentPresenter.finish();
            mEntertainmentPresenter = null;
        }

    }

}
