package com.android.tupple.robot.utils;

import android.app.Activity;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ToolBarUtils {

    private ToolBarUtils() {
        throw new AssertionError();
    }

    public static void initToolBar(Activity activity, Toolbar toolbar, View.OnClickListener clickListener) {
        if (toolbar == null) {
            return;
        }

        toolbar.setNavigationOnClickListener(clickListener);

        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
        appCompatActivity.setSupportActionBar(toolbar);

        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public static void setToolBarTitle(Activity activity, String title) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public static void setToolBarBackgroundColor(Toolbar toolbar, int color) {
        if (toolbar == null) {
            return;
        }
        toolbar.setBackgroundColor(color);
    }

    public static void setToolBarDisplayShowTitleEnabled(Activity activity, boolean enabled) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(enabled);
        }
    }

//    public static void setCollapsingToolBarTitle(CollapsingToolbarLayout collapsingToolBar, CharSequence title) {
//        if (collapsingToolBar == null) {
//            return;
//        }
//
//        collapsingToolBar.setTitle(title);
//    }
}