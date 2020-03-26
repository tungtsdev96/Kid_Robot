package com.android.tupple.robot.domain.entity.smartqa;

/**
 * Created by tungts on 3/22/20.
 */

public class SmartQA {

    private SmartQAPresenter mSmartQAPresenter;

    public void setQAPresenter(SmartQAPresenter qAPresenter) {
        this.mSmartQAPresenter = qAPresenter;
    }

    public void init() {
        if (mSmartQAPresenter != null) {
            mSmartQAPresenter.init();
        }
    }

    public void stop() {
        if (mSmartQAPresenter != null) {
            mSmartQAPresenter.stop();
        }
    }

    public void finish() {
        if (mSmartQAPresenter != null) {
            mSmartQAPresenter.destroy();
        }
    }

}
