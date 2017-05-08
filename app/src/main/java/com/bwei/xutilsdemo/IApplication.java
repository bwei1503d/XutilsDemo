package com.bwei.xutilsdemo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by muhanxi on 17/5/8.
 */

public class IApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
    }
}
