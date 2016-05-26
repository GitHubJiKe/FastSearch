package com.ypf.fastsearch;

import android.app.Application;
import android.content.Context;

import com.ypf.fastsearch.util.FrescoUtil;
import com.ypf.fastsearch.util.StorageConfig;

/**
 * Created by Administrator on 2016/4/22.
 */
public class TApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        StorageConfig.mkDirs();
        FrescoUtil.init(context, StorageConfig.defaultImagePath);
    }

    public static Context getContext() {
        return context;
    }
}
