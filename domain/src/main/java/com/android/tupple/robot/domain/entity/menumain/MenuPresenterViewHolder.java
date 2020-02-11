package com.android.tupple.robot.domain.entity.menumain;

import com.android.tupple.robot.domain.log.CLog;

/**
 * Created by tung.ts on 1/15/2020.
 */

public class MenuPresenterViewHolder {

    public final String TAG = "MenuPresenterViewHolder";

    private EnglishBookPresenter mEnglishBookPresenter;
    private EnglishTopicPresenter mEnglishTopicPresenter;
    private EntertainmentPresenter mEntertainmentPresenter;
    public MenuPresenterViewHolder() {
    }

    public void setEnglishBookPresenter(EnglishBookPresenter englishBookPresenter) {
        this.mEnglishBookPresenter = englishBookPresenter;
    }

    public void setEnglishTopicPresenter(EnglishTopicPresenter englishTopicPresenter) {
        this.mEnglishTopicPresenter = englishTopicPresenter;
    }
    public void setEntertainmentPresenter(EntertainmentPresenter entertainmentPresenter) {
        this.mEntertainmentPresenter = entertainmentPresenter;
    }
    MenuPresenter get(MenuType menuType) {
        MenuPresenter presenter;
        switch (menuType) {
            case ENGLISH_BOOK:
                presenter = mEnglishBookPresenter;
                break;
            case ENGLISH_TOPIC:
                presenter = mEnglishTopicPresenter;
                break;
            case ALARM_CLOCK:
            case ENTERTAINMENT:
                presenter = mEntertainmentPresenter;
                break;
            case INVALID:
            default:
                presenter = null;
        }

        if (presenter == null) {
            CLog.printSecD(TAG, "get(), presenter is null, calendarType = " + menuType);
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

    }

}
