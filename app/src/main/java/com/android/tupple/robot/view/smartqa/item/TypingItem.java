package com.android.tupple.robot.view.smartqa.item;

public class TypingItem extends TextQAItem {

    public TypingItem(String mTextContent) {
        super(mTextContent);
    }

    @Override
    public int getType() {
        return 2;
    }

}
