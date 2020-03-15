package com.android.tupple.robot.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.android.tupple.robot.R;
import com.android.tupple.robot.network.ApiConfig;
import com.android.tupple.robot.utils.SingleClickUtil;

public class ConfigActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        EditText editText = findViewById(R.id.edt_ip);

        SingleClickUtil.registerListener(findViewById(R.id.btn_next), v -> {
            String ip = editText.getText().toString();
            if (!ip.isEmpty()) {
                ApiConfig.BASE_URL = ip;
                startActivity(new Intent(ConfigActivity.this, FaceSmileActivity.class));
            }
        });
    }
}
