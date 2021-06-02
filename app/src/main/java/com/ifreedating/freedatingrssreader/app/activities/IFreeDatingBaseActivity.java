package com.ifreedating.freedatingrssreader.app.activities;

import android.app.Activity;

/**
 * Created by jason on 20/06/14.
 */
public class IFreeDatingBaseActivity extends Activity {

    private long token = 0L;


    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }
}
