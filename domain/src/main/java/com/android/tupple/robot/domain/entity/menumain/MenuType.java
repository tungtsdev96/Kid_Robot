package com.android.tupple.robot.domain.entity.menumain;

/**
 * Created by tungts on 2020-01-12.
 */

public enum MenuType {

    INVALID(-1),
    ENGLISH_BOOK(1),
    ENGLISH_TOPIC(2),
    ALARM_CLOCK(3),
    ENTERTAINMENT(4);

    public int mValue;

    MenuType(int value) {
        this.mValue = value;
    }

    public static MenuType fromValue(int value) {
        switch (value) {
            case -1:
                default:
                return INVALID;
            case 1:
                return ENGLISH_BOOK;
            case 2:
                return ENGLISH_TOPIC;
            case 3:
                return ALARM_CLOCK;
            case 4:
                return ENTERTAINMENT;
        }
    }

}
