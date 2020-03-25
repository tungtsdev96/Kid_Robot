package com.android.tupple.robot.view.smartqa.item;

public abstract class TextQAItem {

    private String mTextContent;

    public TextQAItem(String mTextContent) {
        this.mTextContent = mTextContent;
    }

    // left 0, right 1
    public abstract int getType();

    public String getText() {
        return mTextContent;
    }

}
