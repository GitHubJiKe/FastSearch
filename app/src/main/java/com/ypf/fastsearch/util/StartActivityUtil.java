package com.ypf.fastsearch.util;

import android.content.Context;
import android.content.Intent;

import com.ypf.fastsearch.activity.DetialActivity;
import com.ypf.fastsearch.activity.MainActivity;
import com.ypf.fastsearch.activity.RegisterActivity;
import com.ypf.fastsearch.bean.Record;

/**
 * Created by Administrator on 2016/4/21.
 */
public class StartActivityUtil {
    public static void gotoRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public static void gotoMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void gotoDetialActivity(Context context, Record record) {
        Intent intent = new Intent(context, DetialActivity.class);
        intent.putExtra("record", record);
        context.startActivity(intent);
    }
}
