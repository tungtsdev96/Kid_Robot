package com.android.tupple.robot.utils.downloadutils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.tupple.robot.R;
import com.android.tupple.robot.utils.WindowManagerUtils;

public class DialogUtils {
    private Activity mActivity;
    private String message;
    private String title;
    int icon;

    public DialogUtils(Activity mActivity, String title, String message, int icon) {
        this.mActivity = mActivity;
        this.message = message;
        this.title = title;
        this.icon = icon;
    }

    public void showDialog() {

        WindowManagerUtils.setFullScreenMode(mActivity);
        Dialog dialog = new Dialog(mActivity);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.setContentView(R.layout.dialog_notify);
        final TextView txtTitle = dialog.findViewById(R.id.title_dialog);
        final TextView txtContent = dialog.findViewById(R.id.content_dialog);
        final ImageView iconDialog = dialog.findViewById(R.id.icon_dialog);
        final Button btnQuit = dialog.findViewById(R.id.btn_quit);
        txtTitle.setText(title);
        txtContent.setText(message);
        iconDialog.setImageDrawable(mActivity.getResources().getDrawable(icon));
        btnQuit.setOnClickListener((v -> dialog.dismiss()));
        dialog.show();

    }
}

