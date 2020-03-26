package com.android.tupple.robot.data.remote;

import com.android.tupple.robot.network.ApiClient;

/**
 * Created by tungts on 2020-02-01.
 */

public class ApiFactory {

    public static SmartQAService getQAService() {
        return ApiClient.getClient().create(SmartQAService.class);
    }

}
